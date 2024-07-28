package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
@Data
public class Category {
    @Id
    private String id;
    private String Name;
    private String Description;
}
