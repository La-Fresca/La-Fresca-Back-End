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
    private String MenuItemId;
    private String MenuItemType;
    private Integer Quantity;
    private List<CartItemFeature> CustomFeatures;
    private Double ItemTotalPrice;
    private Double DiscountedPrice;
    private String Image;
    private String Name;
    private String Description;
}
