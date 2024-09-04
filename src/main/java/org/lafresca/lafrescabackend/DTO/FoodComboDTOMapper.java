package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FoodComboDTOMapper implements Function<FoodCombo, FoodComboDTO> {
    @Override
    public FoodComboDTO apply(FoodCombo foodCombo) {
        return new FoodComboDTO(
                foodCombo.getId(),
                foodCombo.getName(),
                foodCombo.getPrice(),
                foodCombo.getImage(),
                foodCombo.getAvailable(),
                foodCombo.getFoodIds(),
                foodCombo.getFoodNames(),
                foodCombo.getDiscountStatus(),
                foodCombo.getRating(),
                foodCombo.getRatingCount()
        );
    }
}
