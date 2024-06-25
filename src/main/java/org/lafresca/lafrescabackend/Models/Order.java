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
    private String OrderId;
    private OrderType OrderType;
    private String CustomerId;
    private Float TotalAmount;
    private OrderStatus OrderStatus;
    private String CafeId;
    private Date CreatedAt;
    private Date UpdatedAt;
    private List<Food> OrderItems;

    //for online orders
    private String location;
    private String contactNo;
    private String deliveryPersonId;

    //for offline orders
    private String cashierId;
    private String waiterId;


}
