package org.lafresca.lafrescabackend.DTO;

import lombok.Data;
import org.lafresca.lafrescabackend.Models.OrderFood;
import org.lafresca.lafrescabackend.Models.OrderStatus;

import java.util.List;

@Data
public class OrderStatusChangeRequest {
    private String id;
    private OrderStatus OrderStatus;
}
