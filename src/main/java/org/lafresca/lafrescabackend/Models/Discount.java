package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Discount {
    private String Name;
    private String Description;
    private String DiscountType;
    private Integer DiscountAmount;
    private LocalDateTime StartDate;
    private LocalDateTime EndDate;
    private String CafeId;
    private Integer IsActive; // When Discount is active 1 else 0
    private String MenuItemType;
    private String MenuItemId;
    private String OfferDetails;
}