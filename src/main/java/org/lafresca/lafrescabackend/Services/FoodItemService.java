package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.FoodItemDTO;
import org.lafresca.lafrescabackend.DTO.FoodItemDTOMapper;
import org.lafresca.lafrescabackend.DTO.Request.FoodItemRequestDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FoodItemDTOMapper foodItemDTOMapper;
    private final SystemLogService systemLogService;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemDTOMapper foodItemDTOMapper, SystemLogService systemLogService) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemDTOMapper = foodItemDTOMapper;
        this.systemLogService = systemLogService;
    }

    // Add new food item
    public FoodItemRequestDTO addNewFoodItem(@Valid FoodItemRequestDTO foodItem) {
        LocalDate now = LocalDate.now();

        FoodItem newFoodItem = foodItemRequestDTOToFoodItem(foodItem);

        newFoodItem.setStatus(2);
        newFoodItem.setRating(0.0);
        newFoodItem.setRatingCount(0);
        newFoodItem.setPostedDate(now);
        newFoodItem.setWeeklySellingCount(0);
        newFoodItem.setTotalSellingCount(0);
        newFoodItem.setDiscountStatus(0);

        FoodItem savedFood = foodItemRepository.save(newFoodItem);
//        log.info("Food item '{}' created successfully", foodItem.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        String message = now + " " + user + " " + "Created new food item (id - " + savedFood.getId() + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodItem;
    }

    // Retrieve all food items
    public List<FoodItemDTO> getFoodItems(String cafeId) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Retrieve all food items belongs to specific cafe id (id - " + cafeId + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodItemRepository.findByCafeId(cafeId)
                .stream()
                .map(foodItemDTOMapper)
                .collect(Collectors.toList());
    }

    // Retrieve all food items for top-level manager
    public List<FoodItem> getFoodItemsForTLM() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Retrive all food items for top level manager ";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodItemRepository.findByStatus();
    }

    // Update food item
    public FoodItemRequestDTO updateFoodItem(String id, @Valid FoodItemRequestDTO foodItem) {
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id " + id));

        existingFoodItem.setName(foodItem.getName());
        existingFoodItem.setDescription(foodItem.getDescription());
        existingFoodItem.setPrice(foodItem.getPrice());
        existingFoodItem.setImage(foodItem.getImage());
        existingFoodItem.setCafeId(foodItem.getCafeId());
        existingFoodItem.setCost(foodItem.getCost());
        existingFoodItem.setCategories(foodItem.getCategories());
        existingFoodItem.setFeatures(foodItem.getFeatures());

        foodItemRepository.save(existingFoodItem);
//        log.info("Food item '{}' updated successfully", foodItem.getName());
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Updated food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodItem;
    }

    // Delete food item by id
    public void deleteFoodItem(String id) {
        foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));
        foodItemRepository.deleteById(id);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Deleted food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // Search food item
    public Optional<FoodItem> getFoodItem(String id) {
        foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get specific food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return foodItemRepository.findById(id);
    }

    // Logical Delete
    public void logicallyDeleteFoodItem(String id) {
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFoodItem.setStatus(1);

        foodItemRepository.save(existingFoodItem);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Logically deleted food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // FoodItemRequestDTo to FoodItem mapping
    public FoodItem foodItemRequestDTOToFoodItem(FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemRequestDTO.getName());
        foodItem.setDescription(foodItemRequestDTO.getDescription());
        foodItem.setPrice(foodItemRequestDTO.getPrice());
        foodItem.setImage(foodItemRequestDTO.getImage());
        foodItem.setFeatures(foodItemRequestDTO.getFeatures());
        foodItem.setCategories(foodItemRequestDTO.getCategories());
        return foodItem;
    }

    // Change FoodItem availability
    public FoodItem changeAvailability(String id, Integer value) {
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFoodItem.setAvailable(value);

        foodItemRepository.save(existingFoodItem);
//        log.info("Food item '{}' availability changed successfully", existingFoodItem.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Change food item availability to " + value + " (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodItem;
    }

    // Approve FoodItem
    public FoodItem approveFoodItem(String id){
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFoodItem.setStatus(0);

        System.out.println(existingFoodItem);

        foodItemRepository.save(existingFoodItem);
//        log.info("Food item '{}' approved successfully", existingFoodItem.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Approved food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodItem;
    }

    // Reject FoodItem
    public FoodItem rejectFoodItem(String id){
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFoodItem.setStatus(3);

        System.out.println(existingFoodItem);

        foodItemRepository.save(existingFoodItem);
//        log.info("Food item '{}' rejected successfully", existingFoodItem.getName());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Reject food item (id - " + id + ")";
        systemLogService.writeToFile(message);
        log.info(message);

        return existingFoodItem;
    }
}
