package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Models.OrderStatus;
import org.lafresca.lafrescabackend.Models.OrderType;
import org.lafresca.lafrescabackend.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'CustomerId' : ?0 }")
    List<Order> findByCustomerId(String userId);

    @Query("{ 'WaiterId' : ?0 }")
    List<Order> findByWaiterId(String userId);

    @Query("{ 'CashierId' : ?0 }")
    List<Order> findByCashierId(String userId);

    @Query("{ 'CafeId' : ?0 }")
    List<Order> findOrdersByCafeId(String cafeId);

    @Query("{ 'DeliveryPersonId' : ?0 }")
    List<Order> findByDeliveryPersonId(String userId);

    @Query("{ 'WaiterId' : ?0, 'OrderStatus' : ?1 }")
    List<Order> findByWaiterIdAndOrderStatus(String userId, OrderStatus orderStatus);

    @Query("{ 'DeliveryPersonId' : ?0, 'OrderStatus' : ?1 }")
    List<Order> findByDeliveryPersonIdAndOrderStatus(String userId, OrderStatus orderStatus);

    List<User> findDeliveryPersonsByCafeId(String cafeId);

    @Query("{ 'CafeId' : ?0, 'OrderStatus' : ?1 }")
    List<Order> findByCafeIdAndOrderStatus(String cafeId, OrderStatus orderStatus);

    @Query("{ 'CreatedAt' : { $gte: ?0, $lt: ?1 } }")
    List<Order> findOrdersByDateRange(LocalDateTime start, LocalDateTime end);

    @Query("{'CafeId' : ?0, 'OrderStatus' : ?1, 'OrderType': ?2 }")
    List<Order> findByCafeIdAndOrderStatusAndType(String cafeId, OrderStatus orderStatus, OrderType orderType);
}