package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Integer Deleted;
}
