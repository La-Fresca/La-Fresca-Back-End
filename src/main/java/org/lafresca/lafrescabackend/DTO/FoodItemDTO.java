package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.CustomFeature;

import java.util.List;

public record FoodItemDTO(
        String id,
        String name,
        Double price,
        String image,
        Integer available,
        List<CustomFeature> features,
        String cafeId,
        String category,
        Double rating,
        Integer discountStatus,
        String discountID
) {
}
