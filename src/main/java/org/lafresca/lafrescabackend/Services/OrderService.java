package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Models.OrderStatus;
import org.lafresca.lafrescabackend.Repositories.OrderRepository;
import org.lafresca.lafrescabackend.Validations.FoodAmountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        //validate order items
        if(order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalStateException("Order items cannot be null or empty");
        }
        //validate cafeId
        if(order.getCafeId() == null || order.getCafeId().isEmpty()) {
            throw new IllegalStateException("CafeId cannot be null or empty");
        }
        //check cafeId is existing
//        if(cafeRepository.findById(order.getCafeId()).isEmpty()) {
//            throw new IllegalStateException("CafeId does not exist");
//        }
        //validate total amount
        if(order.getTotalAmount() == null || order.getTotalAmount() == 0){
            throw new IllegalStateException("Total amount cannot be null or empty");
        }
        if(!FoodAmountValidation.isValidFoodAmount(order.getTotalAmount(), order.getOrderItems())){
            throw new IllegalStateException("Invalid total amount");
        }

        //set order status to pending and set dates
        order.setOrderStatus(OrderStatus.valueOf("PENDING"));
        SimpleDateFormat DateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        order.setCreatedAt(DateTimeFormatter.format(date));
        order.setUpdatedAt(DateTimeFormatter.format(date));

        orderRepository.save(order);

    }


}
