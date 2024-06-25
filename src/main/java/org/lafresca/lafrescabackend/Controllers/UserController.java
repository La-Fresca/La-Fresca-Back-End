package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.DTO.UserDTO;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/lafresca/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/specificuserbyid/{userId}")
    public UserDTO getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(value = "/specificuserbyemail/{email}")
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/specificuserbycafeid/{cafeId}")
    public List<UserDTO> getUsersByCafeId(@PathVariable("cafeId") Long cafeId) {
        return userService.getUsersByCafeId(cafeId);
    }

    @GetMapping(value = "/specificuserbyrole/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable("role") String role) {
        return userService.getUsersByRole(role);
    }

    //get all users without filtering
    @GetMapping(value = "/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
