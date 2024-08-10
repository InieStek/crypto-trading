package pl.project.cryptotradingorder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.project.cryptotradingorder.model.Order;
import pl.project.cryptotradingorder.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    return ResponseEntity.ok(orderService.createOrder(order));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrder(@PathVariable String id) {
    return ResponseEntity.ok(orderService.getOrder(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> cancelOrder(@PathVariable String id) {
    orderService.cancelOrder(id);
    return ResponseEntity.ok().build();
  }
}