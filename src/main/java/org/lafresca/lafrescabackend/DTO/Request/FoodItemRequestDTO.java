package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotNull;
import org.lafresca.lafrescabackend.Models.CustomFeature;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.lafresca.lafrescabackend.Validations.ValidCost;

@Getter
@Setter
public class FoodItemRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Price cannot be empty")
    private Double price;

    @NotEmpty(message = "Description cannot be empty")
    private String Description;

    @NotEmpty(message = "CafeId cannot be empty")
    private String CafeId;

    @ValidCost
    private Double Cost;

    private String Image;
    private List<CustomFeature> features;
    private List<String> categories;
}
