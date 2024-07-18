package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Discount")
@Data
public class Discount {
    @Id
    private String id;
    private String Name;
    private String Description;
    private String DiscountType;
    private Integer DiscountAmount;
    private Date StartDate;
    private Date EndDate;
    private String CafeId;
    private Integer IsActive; // When Discount is active 1 else 0
    private String MenuItemType;
    private String MenuItemId;
    private String OfferDetails;
}
