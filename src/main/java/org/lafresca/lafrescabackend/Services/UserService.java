package org.lafresca.lafrescabackend.Services;

import org.bson.types.ObjectId;
import org.lafresca.lafrescabackend.DTO.UserDTO;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.lafresca.lafrescabackend.Validations.EmailValidation.isValidEmail;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        System.out.println("Getting all users");
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getUserId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setAddress(user.getAddress());
            userDTO.setRole(user.getRole());
            userDTO.setCafeId(user.getCafeId());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        else {
            user.setEmail(user.getEmail());
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalStateException("User first name cannot be null or empty");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalStateException("User last name cannot be null or empty");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty() || !isValidEmail(user.getEmail()) ) {
            throw new IllegalStateException("User email cannot be null or empty");
        }
        if(user.getPhoneNumber() == null || user.getPhoneNumber().length() != 10) {
            throw new IllegalStateException("User phone number cannot be null or empty");
        }
        if(user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalStateException("User role cannot be null or empty");
        }
        if(user.getRole()!="ADMIN" && user.getRole()!="CUSOTMER" && user.getRole()!="TOP_LEVEL_MANAGER" && user.getRole()!="CAFE_MANAGER" && user.getRole()!="CASHIER" && user.getRole()!="KITCHEN_MANAGER" && user.getRole()!="WAITER" && user.getRole()!="DELIVERY_PERSON" && user.getRole()!="STOCKKEEPER") {
            throw new IllegalStateException("User role can only be admin or user");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalStateException("User password cannot be null or empty");
        }
        if(user.getAddress()==null || user.getAddress().isEmpty()) {
            throw new IllegalStateException("User address cannot be null or empty");
        }
        if(user.getCafeId()==null || user.getCafeId() != 0) {
            throw new IllegalStateException("User cafe id cannot be null or empty");
        }
        //need to add
//        Optional<Cafe> cafe = cafeRepository.findById(user.getCafeId());
//        if(!cafe.isPresent()) {
//            throw new IllegalStateException("Cafe with id " + user.getCafeId() + " does not exist");
//        }
        userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new IllegalStateException("User with id " + user.getUserId() + " does not exist"));

        if (user.getFirstName() != null && !user.getFirstName().isEmpty() && !userToUpdate.getFirstName().equals(user.getFirstName())) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().isEmpty() && !userToUpdate.getLastName().equals(user.getLastName())) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !userToUpdate.getEmail().equals(user.getEmail()) && isValidEmail(user.getEmail())) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty() && !userToUpdate.getPhoneNumber().equals(user.getPhoneNumber()) && user.getPhoneNumber().length() == 10){
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getRole() != null && !user.getRole().isEmpty() && !userToUpdate.getRole().equals(user.getRole()) && ((user.getRole()=="ADMIN" || user.getRole()=="CUSOTMER" || user.getRole()=="TOP_LEVEL_MANAGER" || user.getRole()=="CAFE_MANAGER" || user.getRole()=="CASHIER" || user.getRole()=="KITCHEN_MANAGER" || user.getRole()=="WAITER" || user.getRole()=="DELIVERY_PERSON" || user.getRole()=="STOCKKEEPER"))) {
            userToUpdate.setRole(user.getRole());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty() && !userToUpdate.getPassword().equals(user.getPassword())) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty() && !userToUpdate.getAddress().equals(user.getAddress())) {
            userToUpdate.setAddress(user.getAddress());
        }
//        Optional<Cafe> cafe = cafeRepository.findById(user.getCafeId());
//        if (user.getCafeId() != null && user.getCafeId() != 0 && !userToUpdate.getCafeId().equals(user.getCafeId()) && cafe.isPresent()) {
//            userToUpdate.setCafeId(user.getCafeId());
//        }
        userRepository.save(userToUpdate);
    }
}
