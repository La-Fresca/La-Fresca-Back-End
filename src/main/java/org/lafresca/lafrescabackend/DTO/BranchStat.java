package org.lafresca.lafrescabackend.DTO;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.Order;

import java.util.List;

@Data
public class BranchStat {
    List<Order> Day01Orders;
    List<Order> Day02Orders;
    List<Order> Day03Orders;
    List<Order> Day04Orders;
    List<Order> Day05Orders;
    List<Order> Day06Orders;
    List<Order> Day07Orders;
    List<Order> Day08Orders;
    List<Order> Day09Orders;
    List<Order> Day10Orders;
    List<Order> Day11Orders;
    List<Order> Day12Orders;
    Float januarySales;
    Float februarySales;
    Float marchSales;
    Float aprilSales;
    Float juneSales;
    Float julySales;
    Float augustSales;
    Float septemberSales;
    Float octoberSales;
    Float decemberSales;
    Float novemberSales;
    Float allSales;

}
