package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void addOrder(Order order) {
        System.out.println("Adding order");
        if(order == null) {
            throw new IllegalStateException("Order cannot be null");
        }
        //handling offline orders
        if(order.getOrderType().equals("OFFLINE")) {

        }
        //Handling online orders
        else if(order.getOrderType().equals("ONLINE")) {

        }
        //invalid order type
        else {
            throw new IllegalStateException("Invalid order type");
        }

    }
}
