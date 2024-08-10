package pl.project.cryptotradingcommon.model;

import java.time.LocalDateTime;
import pl.project.cryptotradingcommon.enums.OrderStatus;
import pl.project.cryptotradingcommon.enums.OrderType;

public record Order(String id, String userId, String cryptocurrencySymbol, double amount,
                    double price, OrderType type, OrderStatus status, LocalDateTime createdAt) {
}
