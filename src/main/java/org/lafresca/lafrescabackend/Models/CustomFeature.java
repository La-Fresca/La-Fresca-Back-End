package org.lafresca.lafrescabackend.Models;

import lombok.Data;

import java.util.List;

@Data
public class CustomFeature {
    private String Name;
    private List<String> Levels;
    private List<Float> AdditionalPrices;
}
