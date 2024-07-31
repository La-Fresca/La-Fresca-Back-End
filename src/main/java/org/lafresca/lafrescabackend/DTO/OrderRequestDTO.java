package org.lafresca.lafrescabackend.DTO;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.OrderFood;
import org.lafresca.lafrescabackend.Models.OrderStatus;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String id;
    private String OrderType;
    private Float TotalAmount;
    private org.lafresca.lafrescabackend.Models.OrderStatus OrderStatus;
    private String CafeId;
    private List<OrderItemDTO> OrderItems;


    //for online orders
    private String CustomerId;
    private String Location;
    private String ContactNo;
    private String DeliveryPersonId;

    //for offline orders
    private String CashierId;
    private String WaiterId;
}


