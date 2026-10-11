package com.aarontechnology.nseterminal.domain

import com.aarontechnology.nseterminal.data.Company
import com.aarontechnology.nseterminal.data.CompanyImprover
import com.aarontechnology.nseterminal.data.CompanyRepository
import com.aarontechnology.nseterminal.data.CompanyScore
import com.aarontechnology.nseterminal.data.RatioDefinitions
import kotlin.math.pow
import kotlin.math.sqrt

object ScoringEngine {

    /**
     * Calculates Normal Distribution scores (-10 to +10) across valid ratios.
     * Ratios with missing metrics show 'nil' and are EXCLUDED from Total Score.
     * Companies with 0 points (average performance) ARE included.
     * Total score = Combined Ratio Points / (total valid ratios * 10) * 100.
     */
    fun calculateScores(repo: CompanyRepository, year: String, sector: String = "All Sectors"): List<CompanyScore> {
        val filtered = repo.getAllCompanies().filter { sector == "All Sectors" || it.sector == sector }
        val cohort = mutableMapOf<String, MutableList<Double>>()
        RatioDefinitions.ALL.forEach { cohort[it.key] = mutableListOf() }

        val companyRaws = mutableMapOf<String, Map<String, Double?>>()
        for (comp in filtered) {
            val raws = mutableMapOf<String, Double?>()
            for (r in RatioDefinitions.ALL) {
                val v = repo.getRawRatioValue(comp, r, year)
                raws[r.key] = v
                if (v != null) {
                    cohort[r.key]?.add(v)
                }
            }
            companyRaws[comp.symbol] = raws
        }

        val stats = mutableMapOf<String, Pair<Double, Double>>()
        for (r in RatioDefinitions.ALL) {
            val vals = cohort[r.key] ?: emptyList()
            if (vals.size > 1) {
                val mu = vals.sum() / vals.size
                val variance = vals.map { (it - mu).pow(2) }.sum() / (vals.size - 1)
                val sigma = if (variance > 0) sqrt(variance) else 1.0
                stats[r.key] = Pair(mu, sigma)
            } else if (vals.size == 1) {
                stats[r.key] = Pair(vals[0], 1.0)
            } else {
                stats[r.key] = Pair(0.0, 1.0)
            }
        }

        val results = mutableListOf<CompanyScore>()
        for (comp in filtered) {
            val raws = companyRaws[comp.symbol] ?: emptyMap()
            val scores = mutableMapOf<String, Double?>()
            val validPoints = mutableListOf<Double>()

            for (r in RatioDefinitions.ALL) {
                val v = raws[r.key]
                if (v == null) {
                    scores[r.key] = null // nil
                } else {
                    val (mu, sigma) = stats[r.key] ?: Pair(0.0, 1.0)
                    var z = if (sigma > 0) (v - mu) / sigma else 0.0
                    if (!r.higherIsBetter) z = -z
                    val pt = Math.max(-10.0, Math.min(10.0, z * (10.0 / 3.0)))
                    scores[r.key] = Math.round(pt * 100.0) / 100.0
                    validPoints.add(scores[r.key]!!)
                }
            }

            val nValid = validPoints.size
            val combined = validPoints.sum()
            val totalScorePct = if (nValid > 0) {
                Math.round((combined / (nValid * 10.0)) * 10000.0) / 100.0
            } else {
                0.0
            }

            results.add(
                CompanyScore(
                    symbol = comp.symbol,
                    name = comp.name,
                    sector = comp.sector,
                    layout = comp.layout,
                    scores = scores,
                    validRatiosCount = nValid,
                    totalRatiosCount = RatioDefinitions.ALL.size,
                    combinedPoints = Math.round(combined * 100.0) / 100.0,
                    totalScorePct = totalScorePct
                )
            )
        }

        return results.sortedByDescending { it.totalScorePct }
    }

    fun calculateImprovements(repo: CompanyRepository, finalYear: Int, horizon: Int, sector: String = "All Sectors"): List<CompanyImprover> {
        val startYear = finalYear - horizon
        val scStart = calculateScores(repo, startYear.toString(), sector).associate { it.symbol to it.totalScorePct }
        val scFinal = calculateScores(repo, finalYear.toString(), sector).associate { it.symbol to it.totalScorePct }

        val improvers = mutableListOf<CompanyImprover>()
        for (comp in repo.getAllCompanies()) {
            if (sector != "All Sectors" && comp.sector != sector) continue
            val sInit = scStart[comp.symbol] ?: continue
            val sEnd = scFinal[comp.symbol] ?: continue

            val delta = sEnd - sInit
            val shift = 100.0
            val normInit = sInit + shift
            val normEnd = sEnd + shift

            val rate = if (normInit > 0 && normEnd > 0) {
                ((normEnd / normInit).pow(1.0 / horizon) - 1.0) * 100.0
            } else {
                0.0
            }

            improvers.add(
                CompanyImprover(
                    symbol = comp.symbol,
                    name = comp.name,
                    sector = comp.sector,
                    startYear = startYear,
                    finalYear = finalYear,
                    startScorePct = sInit,
                    finalScorePct = sEnd,
                    deltaPct = Math.round(delta * 100.0) / 100.0,
                    geometricRate = Math.round(rate * 100.0) / 100.0
                )
            )
        }

        return improvers.sortedByDescending { it.geometricRate }
    }
}
