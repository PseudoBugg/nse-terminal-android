package com.aarontechnology.nseterminal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarontechnology.nseterminal.data.CompanyRepository
import com.aarontechnology.nseterminal.domain.ScoringEngine
import com.aarontechnology.nseterminal.ui.theme.*
import java.util.Locale

@Composable
fun Tab3TopImprovers(
    repo: CompanyRepository,
    modifier: Modifier = Modifier
) {
    var selectedSector by remember { mutableStateOf("All Sectors") }
    var finalYear by remember { mutableStateOf("2026") }
    var horizonStr by remember { mutableStateOf("3 Years") }
    var topN by remember { mutableStateOf("25") }

    val sectors = remember { repo.getSectors() }
    val years = listOf("2026", "2025", "2024", "2023", "2022")
    val horizons = listOf("1 Year", "3 Years", "5 Years")
    val topNOptions = listOf("10", "25", "50", "100", "All")

    val improvers = remember(selectedSector, finalYear, horizonStr, topN) {
        val h = horizonStr.split(" ")[0].toIntOrNull() ?: 3
        val fy = finalYear.toIntOrNull() ?: 2026
        val all = ScoringEngine.calculateImprovements(repo, fy, h, selectedSector)
        if (topN == "All") all else all.take(topN.toIntOrNull() ?: 25)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BgDark)
            .padding(8.dp)
    ) {
        // Controls Card
        Card(
            colors = CardDefaults.cardColors(containerColor = BgCard),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("GEOMETRIC IMPROVEMENT CONTROLS", color = AccentCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var secExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { secExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Industry: ${selectedSector.take(10)}", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(expanded = secExpanded, onDismissRequest = { secExpanded = false }) {
                            sectors.forEach { s ->
                                DropdownMenuItem(text = { Text(s, fontSize = 12.sp) }, onClick = { selectedSector = s; secExpanded = false })
                            }
                        }
                    }

                    var yrExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { yrExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Final: $finalYear", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(expanded = yrExpanded, onDismissRequest = { yrExpanded = false }) {
                            years.forEach { y ->
                                DropdownMenuItem(text = { Text(y, fontSize = 12.sp) }, onClick = { finalYear = y; yrExpanded = false })
                            }
                        }
                    }

                    var hExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { hExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Horizon: $horizonStr", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(expanded = hExpanded, onDismissRequest = { hExpanded = false }) {
                            horizons.forEach { h ->
                                DropdownMenuItem(text = { Text(h, fontSize = 12.sp) }, onClick = { horizonStr = h; hExpanded = false })
                            }
                        }
                    }
                }
            }
        }

        // Improvers Table
        Card(
            colors = CardDefaults.cardColors(containerColor = BgCard),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.background(BgHeader).padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Rank", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(50.dp).padding(start = 8.dp))
                    Text("Symbol", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(90.dp))
                    Text("Company Name", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(170.dp))
                    Text("Sector", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(95.dp))
                    Text("Start Yr", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(65.dp))
                    Text("Final Yr", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(65.dp))
                    Text("Initial Score", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                    Text("Final Score", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                    Text("Score Delta", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(95.dp))
                    Text("Geometric Improvement Rate", color = AccentGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(180.dp))
                }
                Divider(color = BorderColor)

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(improvers) { idx, imp ->
                        val bg = if (idx % 2 == 0) BgRowEven else BgRowOdd
                        Row(
                            modifier = Modifier.background(bg).padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${idx + 1}", color = FgMuted, fontSize = 12.sp, modifier = Modifier.width(50.dp).padding(start = 8.dp))
                            Text(imp.symbol, color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(90.dp))
                            Text(imp.name, color = FgPrimary, fontSize = 12.sp, modifier = Modifier.width(170.dp))
                            Text(imp.sector, color = FgMuted, fontSize = 11.sp, modifier = Modifier.width(95.dp))
                            Text("${imp.startYear}", color = FgMuted, fontSize = 12.sp, modifier = Modifier.width(65.dp))
                            Text("${imp.finalYear}", color = FgMuted, fontSize = 12.sp, modifier = Modifier.width(65.dp))
                            Text(String.format(Locale.US, "%+.2f%%", imp.startScorePct), color = FgPrimary, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                            Text(String.format(Locale.US, "%+.2f%%", imp.finalScorePct), color = FgPrimary, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                            Text(String.format(Locale.US, "%+.2f%%", imp.deltaPct), color = if (imp.deltaPct >= 0) AccentGreen else AccentRed, fontSize = 12.sp, modifier = Modifier.width(95.dp))
                            Text(
                                text = String.format(Locale.US, "%+.2f%%", imp.geometricRate),
                                color = AccentGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.width(180.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
