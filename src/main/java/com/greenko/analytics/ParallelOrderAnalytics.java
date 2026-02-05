package com.greenko.analytics;

import com.greenko.model.Order;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ParallelOrderAnalytics {

    private final OrderAnalytics analytics = new OrderAnalytics();

    public AnalyticsResult computeAll(List<Order> orders)
            throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {

            Future<Double> totalRevenue =
                    executor.submit(() -> analytics.calculateTotalDeliveredRevenue(orders));

            Future<Map<String, Double>> revenuePerCity =
                    executor.submit(() -> analytics.getRevenuePerCity(orders));

            Future<Map<String, Double>> topCategories =
                    executor.submit(() -> analytics.getTop3CategoriesByRevenue(orders));

            return new AnalyticsResult(
                    totalRevenue.get(),
                    revenuePerCity.get(),
                    topCategories.get()
            );

        } finally {
            executor.shutdown();
        }
    }
}
