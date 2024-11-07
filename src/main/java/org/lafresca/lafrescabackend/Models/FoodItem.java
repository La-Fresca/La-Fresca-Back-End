package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "FoodItem")
@Data
public class FoodItem {
    @Id
    private String id;
    private String Name;
    private String Description;
    private Double Price;
    private String Image;
    private Integer Available; // 1 for available, 0 for not available
    private List<CustomFeature> Features;
    private String CafeId;
    private Integer Status; // 2 for not approved, 1 for deleted, 0 for approved
    private List<String> Categories;
    private Double Cost;
    private Double Rating;
    private Integer RatingCount;
    private LocalDate PostedDate;
    private Integer WeeklySellingCount;
    private Integer TotalSellingCount;
    private Integer DiscountStatus;
    private Discount DiscountDetails;
}
