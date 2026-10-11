package com.aarontechnology.nseterminal.data

data class Company(
    val id: Long,
    val symbol: String,
    val name: String,
    val sector: String,
    val layout: String, // CORPORATE, BANKING, NBFC, INSURANCE
    val marketCapCr: Double,
    val currentPrice: Double,
    val isConsolidated: Boolean = true
)

data class StatementTable(
    val periods: List<String>, // First period is LATEST!
    val rows: List<StatementRow>
)

data class StatementRow(
    val itemName: String,
    val values: List<String> // Aligned with periods (first column is latest!)
)

data class MonthlyMarketCapPoint(
    val year: Int,
    val month: Int,
    val yearMonth: String,
    val monthName: String,
    val avgPrice: Double,
    val avgMarketCapCr: Double
)

data class RatioDefinition(
    val key: String,
    val name: String,
    val category: String,
    val formula: String,
    val higherIsBetter: Boolean,
    val layouts: List<String>
)

data class CompanyScore(
    val symbol: String,
    val name: String,
    val sector: String,
    val layout: String,
    val scores: Map<String, Double?>, // null represents nil
    val validRatiosCount: Int,
    val totalRatiosCount: Int,
    val combinedPoints: Double,
    val totalScorePct: Double // Bounded percentage
)

data class CompanyImprover(
    val symbol: String,
    val name: String,
    val sector: String,
    val startYear: Int,
    val finalYear: Int,
    val startScorePct: Double,
    val finalScorePct: Double,
    val deltaPct: Double,
    val geometricRate: Double
)
