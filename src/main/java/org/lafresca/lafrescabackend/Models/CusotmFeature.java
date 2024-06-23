package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CusotmFeature {
    private String Name;
    private List<String> Levels;
    private List<Float> AdditionalPrices;
}
