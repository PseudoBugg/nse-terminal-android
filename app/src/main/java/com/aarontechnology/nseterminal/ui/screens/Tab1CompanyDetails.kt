package com.aarontechnology.nseterminal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarontechnology.nseterminal.data.Company
import com.aarontechnology.nseterminal.data.CompanyRepository
import com.aarontechnology.nseterminal.data.RatioDefinitions
import com.aarontechnology.nseterminal.data.StatementTable
import com.aarontechnology.nseterminal.ui.components.MarketCapChart
import com.aarontechnology.nseterminal.ui.theme.*
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1CompanyDetails(
    repo: CompanyRepository,
    selectedCompany: Company,
    onSelectCompany: (Company) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedMinitab by remember { mutableStateOf(0) }
    var selectedRatioCategory by remember { mutableStateOf("All Categories") }

    val minitabs = listOf(
        "Quarterly Results",
        "Profit & Loss",
        "Balance Sheet",
        "Cash Flow",
        "Financial Ratios",
        "Market Cap Over Time"
    )

    val allCompanies = remember { repo.getAllCompanies() }
    val searchMatches = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else allCompanies.filter {
            it.symbol.contains(searchQuery, ignoreCase = true) ||
            it.name.contains(searchQuery, ignoreCase = true)
        }.take(6)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BgDark)
            .padding(horizontal = 8.dp)
    ) {
        // 1. Search Bar
        item {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search NSE Company (e.g. RELIANCE, HDFCBANK)") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = AccentCyan) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear", tint = FgMuted)
                        }
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentCyan,
                    unfocusedBorderColor = BorderColor,
                    focusedTextColor = FgPrimary,
                    unfocusedTextColor = FgPrimary,
                    focusedContainerColor = BgCard,
                    unfocusedContainerColor = BgCard
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Autocomplete suggestions
            if (searchMatches.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BgRowOdd),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                ) {
                    Column {
                        searchMatches.forEach { comp ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSelectCompany(comp)
                                        searchQuery = ""
                                    }
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${comp.symbol} — ${comp.name}",
                                    color = FgPrimary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = comp.sector,
                                    color = AccentCyan,
                                    fontSize = 12.sp
                                )
                            }
                            Divider(color = BorderColor)
                        }
                    }
                }
            }
        }

        // 2. Company Meta Header Card
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = BgCard),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = selectedCompany.symbol,
                                color = AccentCyan,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = selectedCompany.name,
                                color = FgPrimary,
                                fontSize = 14.sp
                            )
                        }

                        // Sector Badge
                        Surface(
                            color = AccentGreen.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = selectedCompany.sector.uppercase(Locale.US),
                                color = AccentGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Market Cap: ₹${String.format(Locale.US, "%,.0f", selectedCompany.marketCapCr)} Cr",
                            color = FgMuted,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Price: ₹${String.format(Locale.US, "%.2f", selectedCompany.currentPrice)}",
                            color = FgMuted,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // 3. Horizontal Scrollable Minitabs
        item {
            Spacer(modifier = Modifier.height(10.dp))
            ScrollableTabRow(
                selectedTabIndex = selectedMinitab,
                containerColor = BgCard,
                contentColor = AccentCyan,
                edgePadding = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                minitabs.forEachIndexed { idx, title ->
                    Tab(
                        selected = selectedMinitab == idx,
                        onClick = { selectedMinitab = idx },
                        text = {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = if (selectedMinitab == idx) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedMinitab == idx) AccentCyan else FgMuted
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 4. Minitab Content
        when (selectedMinitab) {
            0, 1, 2, 3 -> {
                item {
                    val stmts = remember(selectedCompany) { repo.getStatements(selectedCompany) }
                    val key = when (selectedMinitab) {
                        0 -> "QUARTERLY_PL"
                        1 -> "ANNUAL_PL"
                        2 -> "BALANCE_SHEET"
                        else -> "CASH_FLOW"
                    }
                    val table = stmts[key]
                    if (table != null) {
                        StatementTableView(table)
                    }
                }
            }
            4 -> {
                // Financial Ratios (100 Ratios, First Column LATEST, nil handling, Category Filter)
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Category Filter Chips
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        ) {
                            items(RatioDefinitions.CATEGORIES) { cat ->
                                FilterChip(
                                    selected = selectedRatioCategory == cat,
                                    onClick = { selectedRatioCategory = cat },
                                    label = { Text(cat, fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = AccentCyan,
                                        selectedLabelColor = BgDark,
                                        containerColor = BgCard,
                                        labelColor = FgMuted
                                    )
                                )
                            }
                        }

                        val (periods, ratioRows) = remember(selectedCompany, selectedRatioCategory) {
                            repo.getFinancialRatios(selectedCompany, selectedRatioCategory)
                        }

                        Card(
                            colors = CardDefaults.cardColors(containerColor = BgCard),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                // Header row (first column latest!)
                                Row(
                                    modifier = Modifier.background(BgHeader).padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = "Financial Ratio",
                                        color = AccentCyan,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.width(220.dp).padding(start = 12.dp)
                                    )
                                    periods.forEachIndexed { pIdx, p ->
                                        Text(
                                            text = p,
                                            color = if (pIdx == 0) AccentCyan else FgMuted,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            modifier = Modifier.width(85.dp)
                                        )
                                    }
                                }
                                Divider(color = BorderColor)

                                // Ratio data rows
                                ratioRows.forEachIndexed { rIdx, (rname, _, vals) ->
                                    val bg = if (rIdx % 2 == 0) BgRowEven else BgRowOdd
                                    Row(
                                        modifier = Modifier.background(bg).padding(vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = rname,
                                            color = FgPrimary,
                                            fontSize = 12.sp,
                                            modifier = Modifier.width(220.dp).padding(start = 12.dp)
                                        )
                                        vals.forEach { v ->
                                            Text(
                                                text = v,
                                                color = if (v == "nil") AccentRed else FgPrimary,
                                                fontWeight = if (v == "nil") FontWeight.Bold else FontWeight.Normal,
                                                fontSize = 12.sp,
                                                modifier = Modifier.width(85.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            5 -> {
                // Market Cap Over Time Interactive Chart
                item {
                    val mcapData = remember(selectedCompany) { repo.getMonthlyMarketCap(selectedCompany) }
                    MarketCapChart(data = mcapData)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Composable
fun StatementTableView(table: StatementTable) {
    Card(
        colors = CardDefaults.cardColors(containerColor = BgCard),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            // Header Row (First period is latest!)
            Row(
                modifier = Modifier.background(BgHeader).padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Line Item",
                    color = AccentCyan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.width(200.dp).padding(start = 12.dp)
                )
                table.periods.forEachIndexed { idx, p ->
                    Text(
                        text = p,
                        color = if (idx == 0) AccentCyan else FgMuted,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.width(95.dp)
                    )
                }
            }
            Divider(color = BorderColor)

            // Rows
            table.rows.forEachIndexed { rIdx, row ->
                val bg = if (rIdx % 2 == 0) BgRowEven else BgRowOdd
                Row(
                    modifier = Modifier.background(bg).padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = row.itemName,
                        color = FgPrimary,
                        fontSize = 12.sp,
                        modifier = Modifier.width(200.dp).padding(start = 12.dp)
                    )
                    row.values.forEach { valStr ->
                        Text(
                            text = valStr,
                            color = FgPrimary,
                            fontSize = 12.sp,
                            modifier = Modifier.width(95.dp)
                        )
                    }
                }
            }
        }
    }
}
