package org.lafresca.lafrescabackend.DTO;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Models.Order;

import java.util.List;

@Data
public class BranchStat {
    private int morningSessionCount = 0;
    private int afternoonSessionCount = 0;
    private int eveningSessionCount = 0;
    private List<FoodItem> topSellingItems;
    private float totalIncomeThisWeek = 0;
    private float totalIncomeLastWeek = 0;

}
