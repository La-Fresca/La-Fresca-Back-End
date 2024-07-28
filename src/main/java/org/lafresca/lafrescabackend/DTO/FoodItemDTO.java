package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.CustomFeature;
import org.lafresca.lafrescabackend.Models.Discount;

import java.util.List;

public record FoodItemDTO(
        String id,
        String name,
        Double price,
        String image,
        Integer available,
        List<CustomFeature> features,
        String cafeId,
        Integer discountStatus,
        List<String> categories,
        Double rating,
        Discount discountDetails
) {
}
