package org.lafresca.lafrescabackend.Validations;

import org.lafresca.lafrescabackend.Models.AddedFeature;
import org.lafresca.lafrescabackend.Models.OrderFood;

import java.util.List;

public class FoodAmountValidation {
    public static boolean isValidFoodAmount(Float TotalAmount, List<OrderFood> orderFoods) {
        Float total = 0.0f;
        if (TotalAmount == null || TotalAmount <= 0) {
            return false;
        }
        for (OrderFood orderFood : orderFoods) {
            if (orderFood.getQuantity() == null || orderFood.getQuantity() <= 0) {
                return false;
            }
            if(orderFood.getPrice() == null || orderFood.getPrice() <= 0){
                return false;
            }
            if(AdditionalFeatureValidation(orderFood.getAddedFeatures()) == -1){
                return false;
            }
            total += orderFood.getPrice() * orderFood.getQuantity() + AdditionalFeatureValidation(orderFood.getAddedFeatures());
        }

        if(total != TotalAmount){
            return false;
        }
        return true;
    }

    private static float AdditionalFeatureValidation(List<AddedFeature> addedFeatures) {
        if(addedFeatures == null){
            return 0;
        }
        float total = 0.0f;
        for (AddedFeature addedFeature : addedFeatures) {
            if (addedFeature.getAdditionalPrice() == null || addedFeature.getAdditionalPrice() <= 0) {
                return -1;
            }
            total += addedFeature.getAdditionalPrice();
        }
        return total;
    }
}
