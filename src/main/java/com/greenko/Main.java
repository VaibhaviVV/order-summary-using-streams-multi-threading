package com.greenko;

import com.greenko.analytics.AnalyticsResult;
import com.greenko.analytics.OrderAnalytics;
import com.greenko.analytics.ParallelOrderAnalytics;
import com.greenko.model.Order;
import com.greenko.util.OrderLoader;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Order> orders = OrderLoader.loadOrders("orders.csv");


        System.out.println("\nMILESTONE 1 : STREAMS ANALYTICS");

        OrderAnalytics analytics = new OrderAnalytics();

        System.out.println("Total Delivered Revenue: "
                + analytics.calculateTotalDeliveredRevenue(orders));

        System.out.println("Revenue per City: "
                + analytics.getRevenuePerCity(orders));

        System.out.println("Top 3 Categories by Revenue: "
                + analytics.getTop3CategoriesByRevenue(orders));



        System.out.println("\nMILESTONE 2 : PARALLEL ANALYTICS (ExecutorService)");

        ParallelOrderAnalytics parallelAnalytics = new ParallelOrderAnalytics();
        AnalyticsResult result = parallelAnalytics.computeAll(orders);

        System.out.println("Total Delivered Revenue: " + result.getTotalRevenue());
        System.out.println("Revenue per City: " + result.getRevenuePerCity());
        System.out.println("Top 3 Categories by Revenue: " + result.getTopCategories());
    }
}
