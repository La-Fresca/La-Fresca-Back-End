package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StockCollection")
@Data
public class StockCollection {
    @Id
    private String id;
    private String Name;
    private Double LowerLimit;
    private Double AvailableAmount;
    private Integer Deleted;
}
