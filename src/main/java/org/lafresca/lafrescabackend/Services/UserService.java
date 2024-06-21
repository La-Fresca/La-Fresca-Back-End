package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        System.out.println("Getting all users");
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            if(user.getFirstName() != null && user.getFirstName().length() > 0) {
                userRepository.save(user);
            } else {
                throw new IllegalStateException("User first name cannot be null or empty");
            }
        } else {
            throw new IllegalStateException("User with id " + user.getUserId() + " does not exist");
        }
    }
}
