package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String OrderId;
    private String CustomerId;

}
