package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.FoodComboDTO;
import org.lafresca.lafrescabackend.DTO.FoodComboDTOMapper;
import org.lafresca.lafrescabackend.DTO.Request.FoodComboRequestDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FoodComboService {
    private final FoodComboRepository foodComboRepository;
    private final FoodItemRepository foodItemRepository;
    private final FoodComboDTOMapper foodComboDTOMapper;
    private final SystemLogService systemLogService;

    @Autowired
    public FoodComboService(FoodComboRepository foodComboRepository, FoodItemRepository foodItemRepository, FoodComboDTOMapper foodComboDTOMapper, SystemLogService systemLogService) {
        this.foodComboRepository = foodComboRepository;
        this.foodItemRepository = foodItemRepository;
        this.foodComboDTOMapper = foodComboDTOMapper;
        this.systemLogService = systemLogService;
    }

    // Add new food combo
    public FoodComboRequestDTO addNewFoodCombo(@Valid FoodComboRequestDTO foodCombo) {
        FoodCombo newFoodCombo = new FoodCombo();

        newFoodCombo.setName(foodCombo.getName());
        newFoodCombo.setDescription(foodCombo.getDescription());
        newFoodCombo.setPrice(foodCombo.getPrice());
        newFoodCombo.setImage(foodCombo.getImage());
        newFoodCombo.setCafeId(foodCombo.getCafeId());
        newFoodCombo.setCost(foodCombo.getCost());
        newFoodCombo.setFoodIds(foodCombo.getFoodIds());

        newFoodCombo.setStatus(2);
        newFoodCombo.setFoodNames(null);
        newFoodCombo.setDiscountDetails(null);
        newFoodCombo.setDiscountStatus(0);
        newFoodCombo.setAvailable(1);
        newFoodCombo.setDeleted(0);
        newFoodCombo.setRating(0.0);
        newFoodCombo.setRatingCount(0);
        newFoodCombo.setPostedDate(LocalDate.now());
        newFoodCombo.setWeeklySellingCount(0);
        newFoodCombo.setTotalSellingCount(0);
        newFoodCombo.setFoodIds(foodCombo.getFoodIds());

        FoodCombo savedCombo = foodComboRepository.save(newFoodCombo);
//        log.info("Food combo '{}' created successfully", foodCombo.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Created new food combo (id - " + savedCombo.getId() + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodCombo;
    }

    // Retrieve all food combos
    public List<FoodComboDTO> getFoodCombos(String cafeId) {
        List<FoodCombo> foodComboList = foodComboRepository.findByCafeId(cafeId);

        for (FoodCombo foodCombo : foodComboList) {
            List<String> foodNames = new ArrayList<>();
            for (String foodId : foodCombo.getFoodIds()) {
                FoodItem foodItem = foodItemRepository.findOneById(foodId);
                foodNames.add(foodItem.getName());
            }

            foodCombo.setFoodNames(foodNames);
        }

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Retrived all food combos";
        systemLogService.writeToFile(message);
        log.info(message);

//        return foodComboList;
        return foodComboList
                .stream()
                .map(foodComboDTOMapper)
                .collect(Collectors.toList());
    }

    // Update food combo
    public void updateFoodCombo(String id, FoodCombo foodCombo) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Combo not found with id " + id));
        LocalDateTime now = LocalDateTime.now();

        if (foodCombo.getName() != null && !foodCombo.getName().isEmpty()) {
            existingFoodCombo.setName(foodCombo.getName());
        }
        if (foodCombo.getDescription() != null && !foodCombo.getDescription().isEmpty()) {
            existingFoodCombo.setDescription(foodCombo.getDescription());
        }
        if (foodCombo.getPrice() > 0) {
            existingFoodCombo.setPrice(foodCombo.getPrice());
        }
        if (foodCombo.getImage() != null & !foodCombo.getImage().isEmpty()) {
            existingFoodCombo.setImage(foodCombo.getImage());
        }
        if (foodCombo.getCafeId() != null && !foodCombo.getCafeId().isEmpty()) {
            existingFoodCombo.setCafeId(foodCombo.getCafeId());
        }
        if (foodCombo.getAvailable() == 0 || foodCombo.getAvailable() == 1) {
            existingFoodCombo.setAvailable(foodCombo.getAvailable());
        }
        if (foodCombo.getDiscountStatus() == 0 || foodCombo.getDiscountStatus() == 1) {
            existingFoodCombo.setDiscountStatus(foodCombo.getDiscountStatus());
        }
        if (foodCombo.getRating() != null) {
            existingFoodCombo.setRating(foodCombo.getRating());
        }

        foodComboRepository.save(existingFoodCombo);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        String message = now + " " + user + " " + "Updated food combo (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // Delete food combo item by id
    public void deleteFoodCombo(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food Combo not found with id " + id));
        foodComboRepository.deleteById(id);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Deleted food combo (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // Search food combo
    public Optional<FoodCombo> getFoodCombo(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food Combo not found with id " + id));

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get specific food combo by id (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodComboRepository.findById(id);
    }

    // Logical Delete
    public void logicallyDeleteFoodCombo(String id) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food Combo not found with id " + id));

        existingFoodCombo.setDeleted(1);

        foodComboRepository.save(existingFoodCombo);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Logically deleted food combo (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // Retrieve all food combos for top-level manager
    public List<FoodCombo> getFoodCombosForTLM() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get all food combos for top level manager";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodComboRepository.findByStatus();
    }

    // Change FoodCombo availability
    public FoodCombo changeAvailability(String id, Integer value) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Combo not found with id " + id));

        existingFoodCombo.setAvailable(value);

        foodComboRepository.save(existingFoodCombo);
//        log.info("Food combo '{}' availability changed successfully", existingFoodCombo.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Food combo availability change (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodCombo;
    }

    // Approve FoodCombo
    public FoodCombo approveFoodCombo(String id){
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodCombo not found with id " + id));

        existingFoodCombo.setStatus(0);

        System.out.println(existingFoodCombo);

        foodComboRepository.save(existingFoodCombo);
//        log.info("Food combo '{}' approved successfully", existingFoodCombo.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Food combo approved (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodCombo;
    }

    // Reject FoodCombo
    public FoodCombo rejectFoodCombo(String id){
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodCombo not found with id " + id));

        existingFoodCombo.setStatus(3);

        System.out.println(existingFoodCombo);

        foodComboRepository.save(existingFoodCombo);
//        log.info("Food combo '{}' rejected successfully", existingFoodCombo.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Food combo rejected" + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodCombo;
    }
}
