package org.lafresca.lafrescabackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lafresca.lafrescabackend.Models.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStatusChangeDTO {
    private String orderId;
    private String itemId;
    private OrderStatus status;
}
