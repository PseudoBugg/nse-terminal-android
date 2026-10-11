package com.aarontechnology.nseterminal.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarontechnology.nseterminal.data.Company
import com.aarontechnology.nseterminal.data.CompanyRepository
import com.aarontechnology.nseterminal.data.DatabaseManager
import com.aarontechnology.nseterminal.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTerminalScreen(
    onResetDatabase: () -> Unit
) {
    val context = LocalContext.current
    val repo = remember { CompanyRepository(context) }
    var selectedCompany by remember { mutableStateOf(repo.getAllCompanies().firstOrNull() ?: com.aarontechnology.nseterminal.data.DefaultUniverse.COMPANIES[0]) }
    var currentTab by remember { mutableStateOf(0) }

    val configuration = LocalConfiguration.current
    val isLandscapeOrTablet = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE || configuration.screenWidthDp >= 600

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "◆ NSE TERMINAL",
                            color = AccentCyan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "by Aaron Technology",
                            color = FgMuted,
                            fontSize = 11.sp
                        )
                    }
                },
                actions = {
                    TextButton(onClick = onResetDatabase) {
                        Icon(Icons.Default.ChangeCircle, contentDescription = "Change DB", tint = AccentAmber, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Change DB", color = AccentAmber, fontSize = 12.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgHeader)
            )
        },
        bottomBar = {
            if (!isLandscapeOrTablet) {
                NavigationBar(containerColor = BgHeader) {
                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0 },
                        icon = { Icon(Icons.Default.Assessment, contentDescription = "Search") },
                        label = { Text("1. Financials", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AccentCyan,
                            selectedTextColor = AccentCyan,
                            indicatorColor = BgCard,
                            unselectedIconColor = FgMuted,
                            unselectedTextColor = FgMuted
                        )
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1 },
                        icon = { Icon(Icons.Default.Leaderboard, contentDescription = "Ranked") },
                        label = { Text("2. Top Ranked", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AccentCyan,
                            selectedTextColor = AccentCyan,
                            indicatorColor = BgCard,
                            unselectedIconColor = FgMuted,
                            unselectedTextColor = FgMuted
                        )
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2 },
                        icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Improvers") },
                        label = { Text("3. Top Improvers", fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AccentCyan,
                            selectedTextColor = AccentCyan,
                            indicatorColor = BgCard,
                            unselectedIconColor = FgMuted,
                            unselectedTextColor = FgMuted
                        )
                    )
                }
            }
        }
    ) { padding ->
        Row(modifier = Modifier.fillMaxSize().padding(padding).background(BgDark)) {
            // Tablet / Landscape Navigation Rail
            if (isLandscapeOrTablet) {
                NavigationRail(containerColor = BgHeader) {
                    Spacer(modifier = Modifier.height(16.dp))
                    NavigationRailItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0 },
                        icon = { Icon(Icons.Default.Assessment, contentDescription = "Search") },
                        label = { Text("1. Financials", fontSize = 10.sp) }
                    )
                    NavigationRailItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1 },
                        icon = { Icon(Icons.Default.Leaderboard, contentDescription = "Ranked") },
                        label = { Text("2. Top Ranked", fontSize = 10.sp) }
                    )
                    NavigationRailItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2 },
                        icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Improvers") },
                        label = { Text("3. Improvers", fontSize = 10.sp) }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                when (currentTab) {
                    0 -> Tab1CompanyDetails(repo = repo, selectedCompany = selectedCompany, onSelectCompany = { selectedCompany = it })
                    1 -> Tab2TopRanked(repo = repo)
                    2 -> Tab3TopImprovers(repo = repo)
                }
            }
        }
    }
}
