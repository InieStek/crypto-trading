package pl.project.cryptotradingorder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.project.cryptotradingorder.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
