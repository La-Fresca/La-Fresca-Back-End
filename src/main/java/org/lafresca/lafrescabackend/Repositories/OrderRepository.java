package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Models.OrderStatus;
import org.lafresca.lafrescabackend.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'customerId' : ?0 }")
    List<Order> findByCustomerId(String userId);

    @Query("{ 'waiterId' : ?0 }")
    List<Order> findByWaiterId(String userId);

    @Query("{ 'cashierId' : ?0 }")
    List<Order> findByCashierId(String userId);

    @Query("{ 'cafeId' : ?0 }")
    List<Order> findByCafeId(Long cafeId);

    @Query("{ 'deliveryPersonId' : ?0 }")
    List<Order> findByDeliveryPersonId(String userId);

    @Query("{ 'waiterId' : ?0, 'orderStatus' : ?1 }")
    List<Order> findByWaiterIdAndOrderStatus(String userId, OrderStatus orderStatus);

    @Query("{ 'deliveryPersonId' : ?0, 'orderStatus' : ?1 }")
    List<Order> findByDeliveryPersonIdAndOrderStatus(String userId, OrderStatus orderStatus);

    List<User> findDeliveryPersonsByCafeId(String cafeId);

}
