package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lafresca.lafrescabackend.DTO.OrderStatusChangeRequest;
import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/lafresca/order")
@Tag(name = "Order Controller")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") String orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @GetMapping(value = "/specificorderbyid/{orderId}")
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping(value = "/specificorderbycustomerid/{userId}")
    public List<Order> getOrdersByCustomerId(@PathVariable("userId") String userId) {
        return orderService.getOrdersByCustomerId(userId);
    }

    @GetMapping(value = "/specificorderbywaiterid/{userId}")
    public List<Order> getOrdersByWaiterId(@PathVariable("userId") String userId) {
        return orderService.getOrdersByWaiterId(userId);
    }

    @GetMapping(value = "/specificorderbycashierid/{userId}")
    public List<Order> getOrdersByCashierId(@PathVariable("userId") String userId) {
        return orderService.getOrdersByCashierId(userId);
    }

    @GetMapping(value = "/specificorderbycafeid/{cafeId}")
    public List<Order> getOrdersByCafeId(@PathVariable("cafeId") Long cafeId) {
        return orderService.getOrdersByCafeId(cafeId);
    }

    @GetMapping(value = "/specificorderbydeliverypersonid/{userId}")
    public List<Order> getOrdersByDeliveryPersonId(@PathVariable("userId") String userId) {
        return orderService.getOrdersByDeliveryPersonId(userId);
    }

    @GetMapping(value = "/pendingordersbywaiterid/{userId}")
    public List<Order> getPendingOrdersByWaiterId(@PathVariable("userId") String userId) {
        return orderService.getPendingOrdersbywaiterId(userId);
    }

    @GetMapping(value = "/pendingordersbydeliverypersonid/{userId}")
    public List<Order> getPendingOrdersByDeliveryPersonId(@PathVariable("userId") String userId) {
        return orderService.getPendingOrdersbydeliveryPersonId(userId);
    }

    @PostMapping(value = "/changestatus")
    public void changeOrderStatus(@RequestBody OrderStatusChangeRequest orderStatusChangeRequest) {
        orderService.changeOrderStatus(orderStatusChangeRequest);
    }


}
