package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "FoodItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer Deleted;
    private String Category;
    private Double Rating;
}
