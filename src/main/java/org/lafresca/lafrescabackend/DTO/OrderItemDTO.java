package org.lafresca.lafrescabackend.DTO;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.AddedFeature;
import org.lafresca.lafrescabackend.Models.OrderStatus;

import java.util.List;

@Data
public class OrderItemDTO {
    private String FoodId;
    private String Name;
    private Float Price;
    private Float Discount;
    private Integer Quantity;
    private String Image;
    private List<AddedFeatureDTO> AddedFeatures;
    private Float TotalPrice;
    private org.lafresca.lafrescabackend.Models.OrderStatus OrderStatus;
    private String menuItemType;
}
