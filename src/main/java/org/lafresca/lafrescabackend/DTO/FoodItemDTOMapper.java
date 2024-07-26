package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FoodItemDTOMapper implements Function<FoodItem, FoodItemDTO> {
    @Override
    public FoodItemDTO apply(FoodItem foodItem) {
        return new FoodItemDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getPrice(),
                foodItem.getImage(),
                foodItem.getAvailable(),
                foodItem.getFeatures(),
                foodItem.getCafeId(),
                foodItem.getDiscountStatus(),
                foodItem.getCategories(),
                foodItem.getRating()
        );
    }
}
