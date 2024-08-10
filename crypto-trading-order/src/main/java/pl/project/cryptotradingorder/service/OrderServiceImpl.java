package pl.project.cryptotradingorder.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.project.cryptotradingorder.enums.OrderStatus;
import pl.project.cryptotradingorder.model.Order;
import pl.project.cryptotradingorder.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  @Override
  public Order createOrder(Order order) {
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(OrderStatus.PENDING);
    return orderRepository.save(order);
  }

  @Override
  public Order getOrder(String id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
  }

  @Override
  public void cancelOrder(String id) {
    Order order = getOrder(id);
    order.setStatus(OrderStatus.CANCELLED);
    orderRepository.save(order);
  }
}
