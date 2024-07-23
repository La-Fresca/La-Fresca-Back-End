package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Cart")
@Data
public class Cart {
    @Id
    private String id;
    private String UserId;
    private List<CartItem> Items;
    private Double TotalPrice;
}
