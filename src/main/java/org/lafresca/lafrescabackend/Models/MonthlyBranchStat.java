package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Models.Order;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "MonthlyBranchStat")
@Data
public class MonthlyBranchStat {
    private String id;
    private int morningSessionCount = 0;
    private int afternoonSessionCount = 0;
    private int eveningSessionCount = 0;
    private List<FoodItem> topSellingItems;
    private float totalIncomeThisMonth = 0;

    private int employeeCount;
    private int menuItemCount;
    private int stockCollectionCount;

    private String branchId;
    private String Month;
    private String Year;
    private Date createdAt;
}