package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
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
