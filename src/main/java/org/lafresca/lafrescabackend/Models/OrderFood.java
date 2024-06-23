package org.lafresca.lafrescabackend.Models;

import java.util.List;

public class OrderFood {
    private String FoodId;
    private String Name;
    private Float Price;
    private Integer Quantity;
    private List<AddedFeature> AddedFeatures;
    private Float TotalPrice;
}
