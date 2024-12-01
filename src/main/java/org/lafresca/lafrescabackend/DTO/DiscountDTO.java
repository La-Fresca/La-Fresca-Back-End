package org.lafresca.lafrescabackend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDTO {
    private String Name;
    private String Description;
    private String DiscountType;
    private Integer Amount;
    private Integer DiscountAmount;
    private LocalDateTime StartDate;
    private LocalDateTime EndDate;
    private String MenuItemType;
    private String MenuItemId;
    private String OfferDetails;
    private String CafeID;
}
