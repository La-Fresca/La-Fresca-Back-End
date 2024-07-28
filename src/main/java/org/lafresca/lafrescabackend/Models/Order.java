package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Order")
@Data
public class Order {
    private String id;
    private String OrderType;
    private Float TotalAmount;
    private OrderStatus OrderStatus;
    private String CafeId;
    private String CreatedAt;
    private String UpdatedAt;
    private List<OrderFood> OrderItems;

    //for online orders
    private String CustomerId;
    private String Location;
    private String ContactNo;
    private String DeliveryPersonId;

    //for offline orders
    private String CashierId;
    private String WaiterId;
}
