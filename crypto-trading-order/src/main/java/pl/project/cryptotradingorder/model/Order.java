package pl.project.cryptotradingorder.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.project.cryptotradingorder.enums.OrderStatus;
import pl.project.cryptotradingorder.enums.OrderType;

@Data
@Document(collection = "orders")
public class Order {
  @Id
  private String id;

  private String userId;
  private String symbol;
  private BigDecimal amount;
  private BigDecimal price;
  private OrderType type;
  private OrderStatus status;
  private LocalDateTime createdAt;

}
