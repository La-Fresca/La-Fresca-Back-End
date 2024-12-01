package org.lafresca.lafrescabackend.Validations;

import org.lafresca.lafrescabackend.Models.AddedFeature;
import org.lafresca.lafrescabackend.Models.OrderFood;

import java.util.List;

public class FoodAmountValidation {
    public static boolean isValidFoodAmount(Float TotalAmount, List<OrderFood> orderFoods) {
        System.out.println("Checking total amount validity - " + orderFoods.size() + "total amount - "+ TotalAmount);
        for(int i = 0; i< orderFoods.size(); i++){
            OrderFood orderFood = orderFoods.get(i);
            System.out.println("item - " + orderFood.getName() + " added feature count - " + orderFood.getAddedFeatures().size());
            for(int j = 0; j < orderFood.getAddedFeatures().size(); i++){
                AddedFeature addedFeature = orderFood.getAddedFeatures().get(i);
                System.out.println("feature - " + addedFeature.getName());
            }
        }
        Float total = 0.0f;
//        if (TotalAmount == null || TotalAmount <= 0) {
//            System.out.println("Total amount is null or less than 0");
//            return false;
//        }
        for (OrderFood orderFood : orderFoods) {
            System.out.println("Item name - " + orderFood.getName());
            if (orderFood.getQuantity() == null || orderFood.getQuantity() <= 0) {
                System.out.println("Order quantity is null - " + orderFood.getName());
                return false;
            }
            if(orderFood.getPrice() == null || orderFood.getPrice() <= 0){
                System.out.println("Order price is null - " + orderFood.getName());
                return false;
            }
            if(AdditionalFeatureValidation(orderFood.getAddedFeatures()) == -1){
                total += orderFood.getPrice() * orderFood.getQuantity();
            }
            else {
                total += (orderFood.getPrice()  + AdditionalFeatureValidation(orderFood.getAddedFeatures()))* orderFood.getQuantity();
            }
        }

//        System.out.println("Total: " + total);
//        System.out.println("TotalAmount: " + TotalAmount);

        if(!total.equals(TotalAmount)){
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
