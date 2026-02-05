package com.greenko.analytics;

import com.greenko.model.Order;
import com.greenko.model.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;

public class OrderAnalytics {


    public double calculateTotalDeliveredRevenue(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return 0.0;
        }

        return orders.stream()
                .filter(this::isDelivered)
                .mapToDouble(Order::getAmount)
                .sum();
    }


    public Map<String, Double> getRevenuePerCity(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return Collections.emptyMap();
        }

        return orders.stream()
                .filter(this::isDelivered)
                .collect(Collectors.groupingBy(
                        Order::getCity,
                        Collectors.summingDouble(Order::getAmount)
                ));
    }


    public Map<String, Double> getTop3CategoriesByRevenue(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return Collections.emptyMap();
        }

        return orders.stream()
                .filter(this::isDelivered)
                .collect(Collectors.groupingBy(
                        Order::getCategory,
                        Collectors.summingDouble(Order::getAmount)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }


    private boolean isDelivered(Order order) {
        return order != null && order.getStatus() == OrderStatus.DELIVERED;
    }
}
