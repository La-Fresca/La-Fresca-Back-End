package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "FoodCombo")
@Data
public class FoodCombo {
    private String id;
    private String Name;
    private String Description;
    private String Image;
    private String CafeId;
    private Integer Available; // 1 for available, 0 for not available
    private Double Price;
    private List<String> FoodIds;
    private List<String> FoodNames;
    private Integer Deleted; // 1 for deleted, 0 for not deleted
    private Double Cost;
    private Double Rating;
    private Integer RatingCount;
    private LocalDate PostedDate;
    private Integer WeeklySellingCount;
    private Integer TotalSellingCount;
    private Integer DiscountStatus;
    private Discount DiscountDetails;
}
