package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.util.List;
@Data
public class OrderFood {
    private String FoodId;
    private String Name;
    private Float Price;
    private Integer Quantity;
    private String Image;
    private List<AddedFeature> AddedFeatures;
    private Float TotalPrice;
    private OrderStatus OrderStatus;
}
