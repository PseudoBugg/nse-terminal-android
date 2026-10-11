# NSE Financial Terminal (Android Edition)

**Publisher:** Aaron Technology  
**Lead Systems Architect:** Astra  
**Executive Orchestrator:** Iris  
**Target Platform:** Android 7.0+ (API Level 24 to 34)  
**Architecture:** Kotlin 1.9, Jetpack Compose, Material 3, SQLite / Storage Access Framework (SAF)

---

## 1. Executive Overview

The **NSE Financial Terminal** is an offline-first quantitative financial analytical platform engineered for smartphones and tablets. It analyzes historical financial metrics across the National Stock Exchange (NSE) large-cap universe, evaluating an exhaustive suite of **100 Financial Ratios** with sector-specific taxonomy models, normal distribution scoring, and 10-year valuation trajectory tracking.

### Core Architectural Features

- **Least-Permission Security Principle:** The application requires **zero permissions** in `AndroidManifest.xml` (No `READ_EXTERNAL_STORAGE` or `MANAGE_EXTERNAL_STORAGE`). Using the modern Android **Storage Access Framework (SAF)** (`ActivityResultContracts.OpenDocument`), the user securely grants persistent read access to their chosen SQLite database file (`screener_nse100.db`).
- **Adaptive Multi-Device Layout (Phones & Tablets):** Configured with full sensor orientation (`android:screenOrientation="fullSensor"`). The user interface smoothly adapts between portrait and landscape modes across both smartphones and tablets:
  - *Compact / Phone Portrait:* Ergonomic Bottom Navigation bar across the 3 main tabs.
  - *Expanded / Tablet / Landscape:* Adaptive Side Navigation Rail for wide-screen data viewing.
- **100 Financial Ratios Engine:** Full coverage across 10 distinct categories:
  1. Liquidity Ratios (11)
  2. Profitability Ratios (12)
  3. Solvency & Leverage Ratios (11)
  4. Efficiency & Asset Turnover Ratios (9)
  5. Market & Valuation Ratios (14)
  6. Cash Flow Ratios (9)
  7. Risk-Adjusted Metrics (10)
  8. Industry-Specific: SaaS & Recurring Revenue (11)
  9. Industry-Specific: Banking & Financial Services (7)
  10. Industry-Specific: Real Estate & REITs (6)
- **Robust `nil` Handling:** Ratios that cannot be calculated due to missing parameters or layout mismatches (e.g., Banking metrics evaluated on Corporate issuers, or SaaS metrics on non-subscription firms) evaluate strictly to `"nil"` and are excluded from score calculations.
- **Percentage Total Score Engine:** Total score is formulated as a bounded percentage:
  $$\text{Total Score (\%)} = \left( \frac{\text{Combined Ratio Points}}{\text{Total Valid Ratios} \times 10} \right) \times 100$$
  - Ratio points range from $-10.0$ to $+10.0$ derived from normal distribution cohort Z-scores.
  - Average performers receiving $0$ points remain included in the score calculation; `"nil"` entries are omitted.
- **Reverse Chronological Financial Statements:** All financial statement minitabs (**Quarterly Results**, **Profit & Loss**, **Balance Sheet**, **Cash Flow**, and **Financial Ratios**) display data with the **latest reporting period in the first data column**, followed by preceding historical periods.
- **Interactive Market Cap Over Time Chart:** High-performance Jetpack Compose Canvas area chart displaying a 10-year (120-month) monthly average market capitalization curve. Dragging or tapping along the curve triggers a vertical tracking crosshair, pulsing node illumination, and a floating HUD displaying the **Month Name**, **Average Market Cap (₹ Cr)**, and **Share Price**.

---

## 2. 3-Tab Terminal Workspace

1. **Tab 1: Company Search & Financials**
   - Top predictive search bar with live autocomplete.
   - Company metadata card (Symbol, Sector, Industry Layout, Market Cap, Current Share Price).
   - Six interactive statement minitabs:
     - *Quarterly Results*
     - *Profit & Loss*
     - *Balance Sheet*
     - *Cash Flow*
     - *Financial Ratios* (featuring interactive Category Filter Chips)
     - *Market Cap Over Time* (interactive Canvas chart)
2. **Tab 2: Top Ranked Companies**
   - Ranks companies by **Total Score (%)**.
   - Sector, Year, and Top N filter controls.
   - Transparent table displaying individual ratio points and `nil` badges.
3. **Tab 3: Top Improvers**
   - Geometric Growth Rate Engine evaluating multi-year score trajectories over 1-year, 3-year, and 5-year horizons.
   - Filters by Industry, Final Year, and Horizon.

---

## 3. Database Ingestion & First-Launch Setup

Because the database is decoupled from the application binary:
1. Upon initial launch, the app displays the **Database Setup** onboarding card.
2. Click **Select Database File (.db)** to invoke the Android Document Picker.
3. Choose your SQLite database file (`screener_nse100.db`).
4. The database is securely copied into the application's private internal storage sandbox (`context.getDatabasePath("screener_nse100.db")`).
5. The configuration is persisted in `SharedPreferences`. The app remembers the database and opens directly on subsequent launches.
6. A **Change DB** button in the top app bar allows swapping or updating the database at any time.

---

## 4. Build & Compilation Instructions

### Option A: Local Build via Android Studio
1. Open Android Studio (Hedgehog 2023.1.1 or newer).
2. Select **File > Open...** and navigate to this repository root.
3. Allow Gradle to synchronize dependencies.
4. Select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. The output APK will be generated at:
   `app/build/outputs/apk/debug/app-debug.apk`

### Option B: Local CLI Build (Gradle Wrapper)
```bash
# On Linux / macOS:
chmod +x gradlew
./gradlew assembleDebug

# On Windows:
gradlew.bat assembleDebug
```

### Option C: Automated Cloud Build (GitHub Actions)
This repository includes a pre-configured CI pipeline in `.github/workflows/build-apk.yml`.
1. Push this repository to GitHub on `main` or `master`.
2. Navigate to the **Actions** tab on GitHub.
3. The **Build Android APK** workflow will execute automatically on GitHub's cloud runners.
4. Once completed, download the signed/unsigned APK artifact directly from the workflow summary page.

---

## 5. License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
