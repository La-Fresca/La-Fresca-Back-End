package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.lafresca.lafrescabackend.Validations.ValidCost;
import org.lafresca.lafrescabackend.Validations.ValidPrice;

@Getter
@Setter
public class FoodComboRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String Name;

    @NotEmpty(message = "Description cannot be empty")
    private String Description;

    @NotEmpty(message = "Image cannot be empty")
    private String Image;

    @NotEmpty(message = "CafeId cannot be empty")
    private String CafeId;

    @ValidPrice
    private Double Price;

    @NotNull(message = "Available cannot be empty")
    private List<String> FoodIds;

    @ValidCost
    private Double Cost;
}
