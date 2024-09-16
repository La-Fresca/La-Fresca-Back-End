package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "StockCollection")
@Data
public class StockCollection {
    @Id
    private String id;
    private String Name;
    private String Unit;
    private Double LowerLimit;
    private Double AvailableAmount;
    private LocalDate PredictedStockoutDate;
    private String CafeId;
    private String Status;
    private Integer Deleted;
    private String Image;
}
