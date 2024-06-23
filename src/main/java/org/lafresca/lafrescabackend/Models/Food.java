package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.List;

@Document(collection = "Food")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private String FoodId;
    private String Name;
    private String Description;
    private Double Price;
    private String Image;
    private Integer Available; // 1 for available, 0 for not available
    private List<CusotmFeature> Features;
    private String CafeId;
    private Integer Deleted;
}
