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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab2TopRanked(
    repo: CompanyRepository,
    modifier: Modifier = Modifier
) {
    var selectedSector by remember { mutableStateOf("All Sectors") }
    var selectedYear by remember { mutableStateOf("2024") }
    var topN by remember { mutableStateOf("25") }

    val sectors = remember { repo.getSectors() }
    val years = listOf("2025", "2024", "2023", "2022", "2021")
    val topNOptions = listOf("10", "25", "50", "100", "All")

    val scores = remember(selectedSector, selectedYear, topN) {
        val all = ScoringEngine.calculateScores(repo, selectedYear, selectedSector)
        if (topN == "All") all else all.take(topN.toIntOrNull() ?: 25)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BgDark)
            .padding(8.dp)
    ) {
        // Filter Controls Card
        Card(
            colors = CardDefaults.cardColors(containerColor = BgCard),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("FILTERS & RANKING CONTROLS", color = AccentCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Sector Dropdown
                    var sectorExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { sectorExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Sector: ${selectedSector.take(12)}", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(
                            expanded = sectorExpanded,
                            onDismissRequest = { sectorExpanded = false },
                            modifier = Modifier.background(BgCard)
                        ) {
                            sectors.forEach { sec ->
                                DropdownMenuItem(
                                    text = { Text(sec, color = FgPrimary, fontSize = 12.sp) },
                                    onClick = {
                                        selectedSector = sec
                                        sectorExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Year Dropdown
                    var yearExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { yearExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Year: $selectedYear", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(
                            expanded = yearExpanded,
                            onDismissRequest = { yearExpanded = false },
                            modifier = Modifier.background(BgCard)
                        ) {
                            years.forEach { yr ->
                                DropdownMenuItem(
                                    text = { Text(yr, color = FgPrimary, fontSize = 12.sp) },
                                    onClick = {
                                        selectedYear = yr
                                        yearExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Top N Dropdown
                    var nExpanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(
                            onClick = { nExpanded = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Top: $topN", fontSize = 11.sp, color = FgPrimary)
                        }
                        DropdownMenu(
                            expanded = nExpanded,
                            onDismissRequest = { nExpanded = false },
                            modifier = Modifier.background(BgCard)
                        ) {
                            topNOptions.forEach { opt ->
                                DropdownMenuItem(
                                    text = { Text(opt, color = FgPrimary, fontSize = 12.sp) },
                                    onClick = {
                                        topN = opt
                                        nExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Table
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
                // Table Header
                Row(
                    modifier = Modifier.background(BgHeader).padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Rank", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(50.dp).padding(start = 8.dp))
                    Text("Symbol", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(90.dp))
                    Text("Company Name", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(170.dp))
                    Text("Sector", color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                    Text("Total Score (%)", color = AccentGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(110.dp))
                    Text("OPM", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("NPM", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("ROE", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("ROCE", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("ROA", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("D/E", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(70.dp))
                    Text("Valid Ratios", color = FgMuted, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(90.dp))
                }
                Divider(color = BorderColor)

                // Table Rows
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(scores) { idx, sc ->
                        val bg = if (idx % 2 == 0) BgRowEven else BgRowOdd
                        val sMap = sc.scores
                        fun fmtPt(k: String): String {
                            val v = sMap[k]
                            return if (v == null) "nil" else String.format(Locale.US, "%+.1f", v)
                        }

                        Row(
                            modifier = Modifier.background(bg).padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${idx + 1}", color = FgMuted, fontSize = 12.sp, modifier = Modifier.width(50.dp).padding(start = 8.dp))
                            Text(sc.symbol, color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(90.dp))
                            Text(sc.name, color = FgPrimary, fontSize = 12.sp, modifier = Modifier.width(170.dp))
                            Text(sc.sector, color = FgMuted, fontSize = 11.sp, modifier = Modifier.width(100.dp))
                            
                            val scoreColor = if (sc.totalScorePct >= 0) AccentGreen else AccentRed
                            Text(
                                text = String.format(Locale.US, "%+.2f%%", sc.totalScorePct),
                                color = scoreColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.width(110.dp)
                            )

                            listOf(
                                "operating_profit_margin",
                                "net_profit_margin",
                                "return_on_equity_roe",
                                "return_on_capital_employed_roce",
                                "return_on_assets_roa",
                                "debt_to_equity_ratio"
                            ).forEach { k ->
                                val ptStr = fmtPt(k)
                                Text(
                                    text = ptStr,
                                    color = if (ptStr == "nil") AccentRed else FgPrimary,
                                    fontSize = 12.sp,
                                    modifier = Modifier.width(70.dp)
                                )
                            }

                            Text(
                                text = "${sc.validRatiosCount}/${sc.totalRatiosCount}",
                                color = FgMuted,
                                fontSize = 11.sp,
                                modifier = Modifier.width(90.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
