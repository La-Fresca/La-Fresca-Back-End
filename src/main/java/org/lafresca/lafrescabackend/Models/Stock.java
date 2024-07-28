package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Stock")
@Data
public class Stock {
    @Id
    private String id;
    private String StockCollectionName;
    private String BatchId;
    private Double InitialAmount;
    private String SupplierName;
    private LocalDate ExpiryDate;
    private Integer Deleted;
}
