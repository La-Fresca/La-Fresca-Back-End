package org.lafresca.lafrescabackend.Models;

import lombok.Data;

@Data
public class EmailStructure {
    private String To;
    private String Subject;
    private String Body;
}
