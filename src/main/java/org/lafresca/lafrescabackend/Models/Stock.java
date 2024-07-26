package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Stock")
@Data
public class Stock {
    private String id;
    private String Name;
    private String BatchId;
    private double AvailableAmount;
    private String SupplierName;
    private LocalDate ExpiryDate;
}
