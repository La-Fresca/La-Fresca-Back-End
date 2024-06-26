package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFood {
    private String FoodId;
    private String Name;
    private Float Price;
    private Integer Quantity;
    private List<AddedFeature> AddedFeatures;
    private Float TotalPrice;
}
