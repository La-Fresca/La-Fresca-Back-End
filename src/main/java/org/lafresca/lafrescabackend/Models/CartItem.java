package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.util.List;

@Data
public class CartItem {
    private String foodItemId;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private List<CustomFeature> customFeatures;
    private Double itemTotalPrice;
}
