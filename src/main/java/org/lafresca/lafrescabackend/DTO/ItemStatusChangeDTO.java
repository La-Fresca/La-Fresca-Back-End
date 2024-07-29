package org.lafresca.lafrescabackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStatusChangeDTO {
    private String orderId;
    private String itemId;
    private String status;
}
