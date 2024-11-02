package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotNull;
import org.lafresca.lafrescabackend.Models.CustomFeature;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.lafresca.lafrescabackend.Validations.ValidCost;
import org.lafresca.lafrescabackend.Validations.ValidPrice;

@Getter
@Setter
public class FoodItemRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @ValidPrice
    private Double price;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    private String Description;

    @NotEmpty(message = "CafeId cannot be empty")
    @NotNull(message = "CafeId cannot be null")
    private String CafeId;

    @ValidCost
    private Double Cost;

    @NotEmpty(message = "Image cannot be empty")
    @NotNull(message = "Image cannot be null")
    private String Image;

    private List<CustomFeature> features;
    private List<String> categories;
}
