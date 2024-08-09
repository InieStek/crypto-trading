package pl.project.cryptotradingcommon.model;

import java.time.LocalDateTime;

public record Order(String id, String userId, String cryptocurrencySymbol, double amount,
                    double price, OrderType type, OrderStatus status, LocalDateTime createdAt) {
}
