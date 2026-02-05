package com.greenko.analytics;

import java.util.Map;

public class AnalyticsResult {

    private double totalRevenue;
    private Map<String, Double> revenuePerCity;
    private Map<String, Double> topCategories;

    public AnalyticsResult(double totalRevenue,
                           Map<String, Double> revenuePerCity,
                           Map<String, Double> topCategories) {
        this.totalRevenue = totalRevenue;
        this.revenuePerCity = revenuePerCity;
        this.topCategories = topCategories;
    }

    public double getTotalRevenue() { return totalRevenue; }
    public Map<String, Double> getRevenuePerCity() { return revenuePerCity; }
    public Map<String, Double> getTopCategories() { return topCategories; }
}
