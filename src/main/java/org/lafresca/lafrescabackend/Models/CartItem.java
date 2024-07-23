package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.util.List;

@Data
public class CartItem {
    private String MenuItemId;
    private String Name;
    private Integer Quantity;
    private Double UnitPrice;
    private List<CustomFeature> CustomFeatures;
    private Double ItemTotalPrice;
}
