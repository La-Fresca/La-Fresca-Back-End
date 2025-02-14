package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lafresca.lafrescabackend.DTO.DeliveryPersonDashbordDTO;
import org.lafresca.lafrescabackend.DTO.ItemStatusChangeDTO;
import org.lafresca.lafrescabackend.DTO.OrderRequestDTO;
import org.lafresca.lafrescabackend.DTO.OrderStatusChangeRequest;
import org.lafresca.lafrescabackend.Models.Order;
import org.lafresca.lafrescabackend.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/order")
@Tag(name = "Order Controller")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.addOrder(orderRequestDTO);
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
    public List<Order> getOrdersByCafeId(@PathVariable("cafeId") String cafeId) {
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
        System.out.println("Check");
        return orderService.getPendingOrdersbydeliveryPersonId(userId);
    }

    @GetMapping(value = "/completedordersbydeliverypersonid/{userId}")
    public List<Order> getCompletedOrdersByDeliveryPersonId(@PathVariable("userId") String userId) {
        return orderService.getCompletedOrdersByDeliveryPersonId(userId);
    }

    @GetMapping(value = "/ondeliveryordersbydeliverypersonid/{userId}")
    public Order ondeliveryordersbydeliverypersonid(@PathVariable("userId") String userId) {
        System.out.println("userId: " + userId);
        List<Order> orders = orderService.ondeliveryordersbydeliverypersonid(userId);
        System.out.println(orders);
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
//        return orderService.ondeliveryordersbydeliverypersonid(userId).get(0);
    }

    @PostMapping(value = "/changestatus")
    public void changeOrderStatus(@RequestBody OrderStatusChangeRequest orderStatusChangeRequest) {
        orderService.changeOrderStatus(orderStatusChangeRequest);
    }

    @PutMapping(value = "/updateOrderItemStatus")
    public void updateOrderItemStatus(@RequestBody ItemStatusChangeDTO itemStatusChangeDTO) {
        orderService.updateOrderStatus(itemStatusChangeDTO);
    }

    @GetMapping(value = "/queueItems/{cafeId}")
    public List<Order> getQueueItems(@PathVariable("cafeId") String cafeId) {
        return orderService.getQueueItems(cafeId);
    }

    @GetMapping(value = "/preparingItems/{cafeId}")
    public List<Order> getPreparingItems(@PathVariable("cafeId") String cafeId) {
        return orderService.getPreparingItems(cafeId);
    }

    @GetMapping(value = "/readyItems/{cafeId}")
    public List<Order> getReadyItems(@PathVariable("cafeId") String cafeId) {
        return orderService.getReadyItems(cafeId);
    }

    //change status after ready (after kitchen manager process)
    @GetMapping(value = "/deliveryOrderStatus/{cafeId}")
    public void getDeliveryOrderStatus(@RequestBody ItemStatusChangeDTO itemStatusChangeDTO) {
        orderService.deliveryOrderStatus(itemStatusChangeDTO);
    }

    @GetMapping(value = "/getSalesInThisWeek/{cafeId}")
    public List<Order> getSalesInThisWeek(@PathVariable("cafeId") String cafeId) {
        return orderService.getSalesInThisWeek(cafeId);
    }

    @GetMapping(value = "/getAssignedToWaiterOrders/{cafeId}")
    public List<Order> getAssignedToWaiterOrders(@PathVariable("cafeId") String cafeId) {
        return orderService.getAssignedToWaiterOrders(cafeId);
    }

    @GetMapping(value = "/getServedToWaiterOrders/{cafeId}")
    public List<Order> getServedToWaiterOrders(@PathVariable("cafeId") String cafeId) {
        return orderService.getServedToWaiterOrders(cafeId);
    }

    @GetMapping(value = "/deliveryPersonDashboard/{userId}")
    public DeliveryPersonDashbordDTO deliveryPersonDashboard(@PathVariable("userId") String userId){
        System.out.println("Check1111");
        DeliveryPersonDashbordDTO deliveryPersonDashbordDTO = new DeliveryPersonDashbordDTO();
        deliveryPersonDashbordDTO.setHistory(orderService.getCompletedOrdersByDeliveryPersonId(userId).size());
        deliveryPersonDashbordDTO.setQueue(orderService.getPendingOrdersbydeliveryPersonId(userId).size());
        System.out.println("check22" + deliveryPersonDashbordDTO.getHistory() + deliveryPersonDashbordDTO.getQueue());
        return deliveryPersonDashbordDTO;
    }
}