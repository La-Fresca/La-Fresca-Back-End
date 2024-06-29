package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String location;
    private String contactNo;
    private String deliveryPersonId;

    //for offline orders
    private String cashierId;
    private String waiterId;


}
