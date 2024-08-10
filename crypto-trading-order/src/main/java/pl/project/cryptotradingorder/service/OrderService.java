package pl.project.cryptotradingorder.service;

import pl.project.cryptotradingorder.model.Order;

public interface OrderService {
  Order createOrder(Order order);
  Order getOrder(String id);
  void cancelOrder(String id);

}
