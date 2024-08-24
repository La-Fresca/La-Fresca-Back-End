package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.util.List;

@Data
public class OrderCombo {
    private String id;
    private String Name;
    private String Image;
    private Double Price;
    private List<OrderFood> FoodItems;
}
