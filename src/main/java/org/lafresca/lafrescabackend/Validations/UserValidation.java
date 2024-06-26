package org.lafresca.lafrescabackend.Validations;
import org.lafresca.lafrescabackend.DTO.UserDTO;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.lafresca.lafrescabackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserValidation {

    // Regular expression for validating an email address
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    private static UserService userService;

    public UserValidation(UserService userService) {
        this.userService = userService;
    }


    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidCustomerId(String customerId) {
        if (customerId == null || customerId.isEmpty()){
            return false;
        }
        UserDTO userById = userService.getUserById(customerId);
        if (userById == null) {
            return false;
        }
        return userById.getRole().equals("CUSTOMER");
    }

    public static boolean isValidCashierId(String cashierId) {
        if (cashierId == null || cashierId.isEmpty()){
            return false;
        }
        UserDTO userById = userService.getUserById(cashierId);
        if (userById == null) {
            return false;
        }
        return userById.getRole().equals("CASHIER");
    }

    public static boolean isValidDeliveryPersonId(String deliveryPersonId) {
        if (deliveryPersonId == null || deliveryPersonId.isEmpty()){
            return false;
        }
        UserDTO userById = userService.getUserById(deliveryPersonId);
        if (userById == null) {
            return false;
        }
        return userById.getRole().equals("DELIVERY_PERSON");
    }

    public static boolean isValidWaiterId(String waiterId) {
        if (waiterId == null || waiterId.isEmpty()){
            return false;
        }
        UserDTO userById = userService.getUserById(waiterId);
        if (userById == null) {
            return false;
        }
        return userById.getRole().equals("WAITER");
    }

}
