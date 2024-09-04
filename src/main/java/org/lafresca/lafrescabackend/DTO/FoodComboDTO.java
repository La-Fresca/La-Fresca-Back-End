package org.lafresca.lafrescabackend.DTO;

import java.util.List;

public record FoodComboDTO(
        String id,
        String name,
        Double price,
        String image,
        Integer available,
        List<String> foodIds,
        List<String> foodNames,
        Integer discountStatus,
        Double rating,
        Integer ratingCount
) {
}
