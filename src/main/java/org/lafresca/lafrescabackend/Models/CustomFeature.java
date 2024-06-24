package org.lafresca.lafrescabackend.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CustomFeature {
    private String Name;
    private List<String> Levels;
    private List<Float> AdditionalPrices;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getLevels() {
        return Levels;
    }

    public void setLevels(List<String> levels) {
        Levels = levels;
    }

    public List<Float> getAdditionalPrices() {
        return AdditionalPrices;
    }

    public void setAdditionalPrices(List<Float> additionalPrices) {
        AdditionalPrices = additionalPrices;
    }

    @Override
    public String toString() {
        return "CustomFeature{" +
                "Name='" + Name + '\'' +
                ", Levels=" + Levels +
                ", AdditionalPrices=" + AdditionalPrices +
                '}';
    }
}
