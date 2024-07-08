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
    private String DiscountAmount;
    private Date StartDate;
    private Date EndDate;
    private String CafeId;
    private Integer IsActive;
    private String MenuItemType;
    private String MenuItemId;
    private String OfferDetails;
}
