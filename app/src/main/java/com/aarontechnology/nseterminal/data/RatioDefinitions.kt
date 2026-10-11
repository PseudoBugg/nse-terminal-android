package com.aarontechnology.nseterminal.data

object RatioDefinitions {
    val ALL: List<RatioDefinition> = listOf(
        RatioDefinition(
            key = "current_ratio",
            name = "Current Ratio",
            category = "Liquidity Ratios",
            formula = "Current Assets ÷ Current Liabilities",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "quick_ratio",
            name = "Quick Ratio (Acid-Test)",
            category = "Liquidity Ratios",
            formula = "Cash + Marketable Securities + Receivables ÷ Current Liabilities",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_ratio",
            name = "Cash Ratio",
            category = "Liquidity Ratios",
            formula = "Cash & Cash Equivalents ÷ Current Liabilities",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "operating_cash_flow_ratio",
            name = "Operating Cash Flow Ratio",
            category = "Liquidity Ratios",
            formula = "Operating Cash Flow ÷ Current Liabilities",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "defensive_interval_ratio",
            name = "Defensive Interval Ratio",
            category = "Liquidity Ratios",
            formula = "Current Liquid Assets ÷ Daily Operational Expenses",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "absolute_liquidity_ratio",
            name = "Absolute Liquidity Ratio",
            category = "Liquidity Ratios",
            formula = "Cash + Marketable Securities ÷ Current Liabilities",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_conversion_cycle",
            name = "Cash Conversion Cycle (CCC)",
            category = "Liquidity Ratios",
            formula = "Days Inventory Outstanding + Days Sales Outstanding - Days Payable Outstanding",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "net_working_capital_ratio",
            name = "Net Working Capital Ratio",
            category = "Liquidity Ratios",
            formula = "Current Assets - Current Liabilities ÷ Total Assets",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "days_sales_outstanding",
            name = "Days Sales Outstanding (DSO)",
            category = "Liquidity Ratios",
            formula = "Accounts Receivable ÷ Total Credit Sales × 365",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "days_inventory_outstanding",
            name = "Days Inventory Outstanding (DIO)",
            category = "Liquidity Ratios",
            formula = "Average Inventory ÷ Cost of Goods Sold × 365",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "days_payable_outstanding",
            name = "Days Payable Outstanding (DPO)",
            category = "Liquidity Ratios",
            formula = "Accounts Payable ÷ Cost of Goods Sold × 365",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "gross_profit_margin",
            name = "Gross Profit Margin",
            category = "Profitability Ratios",
            formula = "Gross Profit ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("CORPORATE_INVENTORY")
        ),
        RatioDefinition(
            key = "operating_profit_margin",
            name = "Operating Profit Margin",
            category = "Profitability Ratios",
            formula = "Operating Income ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "net_profit_margin",
            name = "Net Profit Margin",
            category = "Profitability Ratios",
            formula = "Net Income ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "ebitda_margin",
            name = "EBITDA Margin",
            category = "Profitability Ratios",
            formula = "EBITDA ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "return_on_assets",
            name = "Return on Assets (ROA)",
            category = "Profitability Ratios",
            formula = "Net Income ÷ Total Assets",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "return_on_equity",
            name = "Return on Equity (ROE)",
            category = "Profitability Ratios",
            formula = "Net Income ÷ Shareholder's Equity",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "return_on_invested_capital",
            name = "Return on Invested Capital (ROIC)",
            category = "Profitability Ratios",
            formula = "NOPAT ÷ Total Invested Capital",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "return_on_capital_employed",
            name = "Return on Capital Employed (ROCE)",
            category = "Profitability Ratios",
            formula = "EBIT ÷ Capital Employed",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "return_on_net_assets",
            name = "Return on Net Assets (RONA)",
            category = "Profitability Ratios",
            formula = "Net Income ÷ Fixed Assets + Net Working Capital",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "effective_tax_rate",
            name = "Effective Tax Rate",
            category = "Profitability Ratios",
            formula = "Income Tax Expense ÷ Pre-Tax Income",
            higherIsBetter = false,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "pre_tax_profit_margin",
            name = "Pre-Tax Profit Margin",
            category = "Profitability Ratios",
            formula = "Pre-Tax Operating Profit ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "cash_flow_margin",
            name = "Cash Flow Margin",
            category = "Profitability Ratios",
            formula = "Operating Cash Flow ÷ Revenue",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "debt_to_equity_ratio",
            name = "Debt-to-Equity Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Total Debt ÷ Shareholders' Equity",
            higherIsBetter = false,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "debt_to_assets_ratio",
            name = "Debt-to-Assets Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Total Debt ÷ Total Assets",
            higherIsBetter = false,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "interest_coverage_ratio",
            name = "Interest Coverage Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "EBIT ÷ Interest Expense",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "debt_service_coverage_ratio",
            name = "Debt Service Coverage Ratio (DSCR)",
            category = "Solvency & Leverage Ratios",
            formula = "Net Operating Income ÷ Total Debt Service",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "equity_ratio",
            name = "Equity Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Shareholders' Equity ÷ Total Assets",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "debt_to_ebitda_ratio",
            name = "Debt-to-EBITDA Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Total Debt ÷ EBITDA",
            higherIsBetter = false,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "fixed_charge_coverage_ratio",
            name = "Fixed Charge Coverage Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "EBIT + Fixed Charges ÷ Fixed Charges + Interest Expense",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "capitalization_ratio",
            name = "Capitalization Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Long-Term Debt ÷ Long-Term Debt + Shareholders' Equity",
            higherIsBetter = true,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "assets_to_equity_ratio",
            name = "Assets-to-Equity Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Total Assets ÷ Shareholders' Equity",
            higherIsBetter = true,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "gearing_ratio",
            name = "Gearing Ratio",
            category = "Solvency & Leverage Ratios",
            formula = "Long-Term Debt ÷ Equity",
            higherIsBetter = false,
            layouts = listOf("CORPORATE", "NBFC")
        ),
        RatioDefinition(
            key = "degree_of_financial_leverage",
            name = "Degree of Financial Leverage (DFL)",
            category = "Solvency & Leverage Ratios",
            formula = "Percentage Change in EPS ÷ Percentage Change in EBIT",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "asset_turnover_ratio",
            name = "Asset Turnover Ratio",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Revenue ÷ Total Assets",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "inventory_turnover_ratio",
            name = "Inventory Turnover Ratio",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Cost of Goods Sold ÷ Average Inventory",
            higherIsBetter = true,
            layouts = listOf("CORPORATE_INVENTORY")
        ),
        RatioDefinition(
            key = "accounts_receivable_turnover",
            name = "Accounts Receivable Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Net Credit Sales ÷ Average Accounts Receivable",
            higherIsBetter = true,
            layouts = listOf("CORPORATE_INVENTORY")
        ),
        RatioDefinition(
            key = "accounts_payable_turnover",
            name = "Accounts Payable Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Cost of Goods Sold ÷ Average Accounts Payable",
            higherIsBetter = true,
            layouts = listOf("CORPORATE_INVENTORY")
        ),
        RatioDefinition(
            key = "fixed_asset_turnover",
            name = "Fixed Asset Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Revenue ÷ Net Fixed Assets",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "working_capital_turnover",
            name = "Working Capital Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Revenue ÷ Net Working Capital",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "capital_turnover",
            name = "Capital Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Revenue ÷ Total Capital Employed",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "equity_turnover",
            name = "Equity Turnover",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Revenue ÷ Average Shareholders' Equity",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "operating_expense_ratio",
            name = "Operating Expense Ratio",
            category = "Efficiency & Asset Turnover Ratios",
            formula = "Operating Expenses ÷ Revenue",
            higherIsBetter = false,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "price_to_earnings_ratio",
            name = "Price-to-Earnings (P/E) Ratio",
            category = "Market & Valuation Ratios",
            formula = "Market Price per Share ÷ Earnings per Share",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "price_to_book_ratio",
            name = "Price-to-Book (P/B) Ratio",
            category = "Market & Valuation Ratios",
            formula = "Market Price per Share ÷ Book Value per Share",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "price_to_sales_ratio",
            name = "Price-to-Sales (P/S) Ratio",
            category = "Market & Valuation Ratios",
            formula = "Market Capitalization ÷ Total Revenue",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "price_to_free_cash_flow",
            name = "Price-to-Free Cash Flow (P/FCF)",
            category = "Market & Valuation Ratios",
            formula = "Market Capitalization ÷ Free Cash Flow",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "enterprise_value_to_ebitda",
            name = "Enterprise Value-to-EBITDA (EV/EBITDA)",
            category = "Market & Valuation Ratios",
            formula = "Enterprise Value ÷ EBITDA",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "peg_ratio",
            name = "PEG Ratio",
            category = "Market & Valuation Ratios",
            formula = "P/E Ratio ÷ Annual EPS Growth Rate",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "dividend_yield",
            name = "Dividend Yield",
            category = "Market & Valuation Ratios",
            formula = "Dividend per Share ÷ Price per Share",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "dividend_payout_ratio",
            name = "Dividend Payout Ratio",
            category = "Market & Valuation Ratios",
            formula = "Dividends Paid ÷ Net Income",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "earnings_per_share",
            name = "Earnings Per Share (EPS)",
            category = "Market & Valuation Ratios",
            formula = "Net Income - Preferred Dividends ÷ Outstanding Shares",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "book_value_per_share",
            name = "Book Value Per Share (BVPS)",
            category = "Market & Valuation Ratios",
            formula = "Shareholders' Equity - Preferred Equity ÷ Outstanding Shares",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "retention_ratio",
            name = "Retention Ratio",
            category = "Market & Valuation Ratios",
            formula = "Net Income - Dividends Paid ÷ Net Income",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "earnings_yield",
            name = "Earnings Yield",
            category = "Market & Valuation Ratios",
            formula = "Earnings per Share ÷ Market Price per Share",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "tobin_s_q_ratio",
            name = "Tobin's Q Ratio",
            category = "Market & Valuation Ratios",
            formula = "Market Value of Assets ÷ Replacement Cost of Assets",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "ev_to_sales",
            name = "EV-to-Sales",
            category = "Market & Valuation Ratios",
            formula = "Enterprise Value ÷ Total Revenue",
            higherIsBetter = false,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "free_cash_flow_to_equity",
            name = "Free Cash Flow to Equity (FCFE)",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow - Capital Expenditures + Net Debt Issued",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "free_cash_flow_to_firm",
            name = "Free Cash Flow to Firm (FCFF)",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow - Capital Expenditures + Interest Paid × 1 - Tax Rate",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_reinvestment_ratio",
            name = "Cash Reinvestment Ratio",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow - Dividends ÷ Gross Capital Assets + Working Capital",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_flow_coverage_ratio",
            name = "Cash Flow Coverage Ratio",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow ÷ Total Debt",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_flow_per_share",
            name = "Cash Flow per Share",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow - Preferred Dividends ÷ Common Shares Outstanding",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "capex_to_operating_cash_flow",
            name = "CapEx to Operating Cash Flow",
            category = "Cash Flow Ratios",
            formula = "Capital Expenditures ÷ Operating Cash Flow",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "free_cash_flow_conversion",
            name = "Free Cash Flow Conversion",
            category = "Cash Flow Ratios",
            formula = "Free Cash Flow ÷ EBITDA",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_return_on_assets",
            name = "Cash Return on Assets",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow ÷ Total Assets",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "cash_flow_to_net_income",
            name = "Cash Flow to Net Income",
            category = "Cash Flow Ratios",
            formula = "Operating Cash Flow ÷ Net Income",
            higherIsBetter = true,
            layouts = listOf("CORPORATE")
        ),
        RatioDefinition(
            key = "sharpe_ratio",
            name = "Sharpe Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Portfolio Return - Risk-Free Rate ÷ Standard Deviation",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "sortino_ratio",
            name = "Sortino Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Portfolio Return - Risk-Free Rate ÷ Downside Deviation",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "treynor_ratio",
            name = "Treynor Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Portfolio Return - Risk-Free Rate ÷ Beta",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "beta",
            name = "Beta",
            category = "Risk-Adjusted Metrics",
            formula = "Covariance of Stock and Market ÷ Variance of Market",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "alpha",
            name = "Alpha",
            category = "Risk-Adjusted Metrics",
            formula = "Actual Return - Expected Benchmark Return",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "information_ratio",
            name = "Information Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Active Return ÷ Tracking Error",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "calmar_ratio",
            name = "Calmar Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Annual Return ÷ Maximum Drawdown",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "value_at_risk",
            name = "Value at Risk (VaR)",
            category = "Risk-Adjusted Metrics",
            formula = "Portfolio Value × Volatility × Z-Score",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "conditional_value_at_risk",
            name = "Conditional Value at Risk (CVaR)",
            category = "Risk-Adjusted Metrics",
            formula = "Average Portfolio Loss exceeding VaR",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "omega_ratio",
            name = "Omega Ratio",
            category = "Risk-Adjusted Metrics",
            formula = "Probability-weighted Gains ÷ Probability-weighted Losses",
            higherIsBetter = true,
            layouts = listOf("ALL")
        ),
        RatioDefinition(
            key = "customer_acquisition_cost",
            name = "Customer Acquisition Cost (CAC)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Total Sales & Marketing Expenses ÷ New Customers Acquired",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "lifetime_value",
            name = "Lifetime Value (LTV)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "ARPU × Gross Margin ÷ Churn Rate",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "ltv_to_cac_ratio",
            name = "LTV to CAC Ratio",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Lifetime Value ÷ Customer Acquisition Cost",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "cac_payback_period",
            name = "CAC Payback Period",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Customer Acquisition Cost ÷ ARPU × Gross Margin",
            higherIsBetter = false,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "annual_recurring_revenue",
            name = "Annual Recurring Revenue (ARR)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Total Contracted Monthly Recurring Revenue × 12",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "monthly_recurring_revenue",
            name = "Monthly Recurring Revenue (MRR)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Average Revenue per Account × Total Subscribers",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "net_revenue_retention",
            name = "Net Revenue Retention (NRR)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Starting MRR + Expansion MRR - Contraction MRR - Churn MRR ÷ Starting MRR",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "gross_revenue_retention",
            name = "Gross Revenue Retention (GRR)",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Starting MRR - Contraction MRR - Churn MRR ÷ Starting MRR",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "customer_churn_rate",
            name = "Customer Churn Rate",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Lost Customers ÷ Total Customers at Start",
            higherIsBetter = false,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "burn_multiple",
            name = "Burn Multiple",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Net Cash Burn ÷ Net New ARR",
            higherIsBetter = false,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "magic_number",
            name = "Magic Number",
            category = "Industry-Specific: SaaS & Recurring Revenue",
            formula = "Quarterly ARR Growth × 4 ÷ Previous Quarter Sales & Marketing Spend",
            higherIsBetter = true,
            layouts = listOf("IT", "CONSUMER_TECH")
        ),
        RatioDefinition(
            key = "net_interest_margin",
            name = "Net Interest Margin (NIM)",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Net Interest Income ÷ Average Earnings Assets",
            higherIsBetter = true,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "efficiency_ratio",
            name = "Efficiency Ratio",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Non-Interest Expenses ÷ Net Interest Income + Non-Interest Income",
            higherIsBetter = false,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "tier_1_capital_ratio",
            name = "Tier 1 Capital Ratio",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Tier 1 Capital ÷ Risk-Weighted Assets",
            higherIsBetter = true,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "non_performing_loan_ratio",
            name = "Non-Performing Loan (NPL) Ratio",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Non-Performing Loans ÷ Total Gross Loans",
            higherIsBetter = true,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "loan_to_deposit_ratio",
            name = "Loan-to-Deposit Ratio (LDR)",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Total Loans ÷ Total Customer Deposits",
            higherIsBetter = true,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "provision_for_credit_loss_ratio",
            name = "Provision for Credit Loss Ratio",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "Loan Loss Provision ÷ Total Gross Loans",
            higherIsBetter = false,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "common_equity_tier_1_ratio",
            name = "Common Equity Tier 1 (CET1) Ratio",
            category = "Industry-Specific: Banking & Financial Services",
            formula = "CET1 Capital ÷ Risk-Weighted Assets",
            higherIsBetter = true,
            layouts = listOf("BANKING", "NBFC")
        ),
        RatioDefinition(
            key = "capitalization_rate",
            name = "Capitalization Rate (Cap Rate)",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "Net Operating Income ÷ Current Market Property Value",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
        RatioDefinition(
            key = "funds_from_operations",
            name = "Funds From Operations (FFO)",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "Net Income + Depreciation + Amortization - Gains on Property Sales",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
        RatioDefinition(
            key = "adjusted_funds_from_operations",
            name = "Adjusted Funds From Operations (AFFO)",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "FFO - Recurring Capital Expenditures - Leasing Costs",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
        RatioDefinition(
            key = "loan_to_value",
            name = "Loan-to-Value (LTV)",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "Principal Mortgage Loan Balance ÷ Appraised Property Value",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
        RatioDefinition(
            key = "net_asset_value_per_share",
            name = "Net Asset Value (NAV) per Share",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "Total Real Estate Assets - Total Liabilities ÷ Shares Outstanding",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
        RatioDefinition(
            key = "occupancy_rate",
            name = "Occupancy Rate",
            category = "Industry-Specific: Real Estate & REITs",
            formula = "Occupied Units or Square Footage ÷ Total Available Units or Square Footage",
            higherIsBetter = true,
            layouts = listOf("REAL_ESTATE", "INFRASTRUCTURE")
        ),
    )

    val CATEGORIES: List<String> = listOf(
        "All Categories",
        "Liquidity Ratios",
        "Profitability Ratios",
        "Solvency & Leverage Ratios",
        "Efficiency & Asset Turnover Ratios",
        "Market & Valuation Ratios",
        "Cash Flow Ratios",
        "Risk-Adjusted Metrics",
        "Industry-Specific: SaaS & Recurring Revenue",
        "Industry-Specific: Banking & Financial Services",
        "Industry-Specific: Real Estate & REITs"
    )

    fun isApplicable(company: Company, definition: RatioDefinition): Boolean {
        val layouts = definition.layouts
        val layout = company.layout
        val sector = company.sector

        if (layouts.contains("ALL")) return true
        if (layouts.contains("BANKING") && (layout == "BANKING" || layout == "NBFC")) return true
        if (layouts.contains("IT") && (sector == "IT" || sector == "Consumer Tech")) return true
        if (layouts.contains("REAL_ESTATE") && (sector == "Infrastructure" || sector == "Real Estate")) return true
        if (layouts.contains("CORPORATE") && layout == "CORPORATE") return true
        if (layouts.contains("CORPORATE_INVENTORY") && layout == "CORPORATE" && sector != "IT" && sector != "Consumer Tech" && sector != "Telecom") return true
        return false
    }
}
