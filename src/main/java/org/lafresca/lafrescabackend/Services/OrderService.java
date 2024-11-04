package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.DTO.*;
import org.lafresca.lafrescabackend.Models.*;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.lafresca.lafrescabackend.Repositories.OrderRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.lafresca.lafrescabackend.Validations.FoodAmountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodItemRepository foodItemRepository;
//    private final UserValidation userValidation;
    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, FoodItemRepository foodItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.foodItemRepository = foodItemRepository;
    }


    public void addOrder(OrderRequestDTO orderRequestDTO) {
        System.out.println("Adding order");
        System.out.println(orderRequestDTO);
        if(orderRequestDTO == null) {
            throw new IllegalStateException("Order cannot be null");
        }

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(orderRequestDTO.getTotalAmount());
        order.setOrderType(orderRequestDTO.getOrderType());
        order.setCafeId(orderRequestDTO.getCafeId());
        order.setCreatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        order.setUpdatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

        List<OrderFood> orderItems = new ArrayList<>();
        orderRequestDTO.getOrderItems().forEach(item -> {
            OrderFood orderFood = new OrderFood();
            orderFood.setFoodId(item.getFoodId());
            orderFood.setQuantity(item.getQuantity());
            orderFood.setTotalPrice(item.getTotalPrice());

            FoodItem foodItem = foodItemRepository.findById(item.getFoodId())
                    .orElseThrow(() -> new IllegalStateException("Food item with id " + item.getFoodId() + " does not exist"));
            orderFood.setName(foodItem.getName());
            orderFood.setPrice(foodItem.getPrice().floatValue());
            orderFood.setOrderStatus(OrderStatus.PENDING);
            orderFood.setImage(foodItem.getImage());

            List<AddedFeatureDTO> addedFeatureDTOList = item.getAddedFeatures();
            List<AddedFeature> addedFeatureList = new ArrayList<>();

            Integer count = 0;
            if (Objects.equals(item.getMenuItemType(), "Food Item")) {
//                FoodItem foodItemO = foodItemRepository.findOneById(item.getFoodId());
                for (AddedFeatureDTO addedFeatureDTO : addedFeatureDTOList) {
                    CustomFeature customFeature = foodItem.getFeatures().get(count);
                    if (addedFeatureDTO.getLevel() != -1) {
                        AddedFeature addedFeature = new AddedFeature();
                        addedFeature.setLevel(foodItem.getFeatures().get(count).getLevels().get(addedFeatureDTO.getLevel()));
                        addedFeature.setAdditionalPrice(foodItem.getFeatures().get(count).getAdditionalPrices().get(addedFeatureDTO.getLevel()).floatValue());
                        addedFeature.setName(foodItem.getFeatures().get(count).getName());
                        addedFeatureList.add(addedFeature);
                    }
                    count++;
                }
                orderFood.setAddedFeatures(addedFeatureList);
            }


//            item.getAddedFeatures().forEach(itemfeature -> {
//                if(itemfeature.getLevel() >= 0) {
//                    CustomFeature customFeature = foodItem.getFeatures().get(itemfeature.getLevel());
//                    AddedFeature addedFeature = new AddedFeature();
//                    addedFeature.setLevel(itemfeature.getLevel());
//                    addedFeature.setName(customFeature.getName());
//
//
//                }
//            });



            orderItems.add(orderFood);
        });

        order.setOrderItems(orderItems);
        order.setCustomerId(orderRequestDTO.getCustomerId());
        order.setLocation(orderRequestDTO.getLocation());
        order.setContactNo(orderRequestDTO.getContactNo());
        order.setDeliveryPersonId(orderRequestDTO.getDeliveryPersonId());
        order.setWaiterId(orderRequestDTO.getWaiterId());
        order.setCashierId(orderRequestDTO.getCashierId());





//        ==============================================


//        order.setOrderStatus(OrderStatus.PENDING);
//        order.setTotalAmount(orderRequestDTO.getTotalAmount());
//        order.setOrderType(orderRequestDTO.getOrderType());
//        order.setCafeId(orderRequestDTO.getCafeId());
//        order.setCreatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
//        order.setUpdatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
//
//        List<OrderFood> orderItems = new ArrayList<>();
//        for (OrderItemDTO orderItemsDTO : orderRequestDTO.getOrderItems()) {
//        orderRequestDTO.getOrderItems().forEach(item -> {
//            OrderFood orderFood = new OrderFood();
//            List <CustomFeature> featureList = new ArrayList<>();
//            List<AddedFeatureDTO> addedFeatureDTOList = item.getAddedFeatures();
//            List<AddedFeature> addedFeatureList = new ArrayList<>();
//
//                    Integer count = 0;
//                    if (Objects.equals(item.getMenuItemType(), "Food Item")){
//                        FoodItem foodItem = foodItemRepository.findOneById(item.getFoodId());
//                        for (AddedFeatureDTO addedFeatureDTO : addedFeatureDTOList) {
//                            CustomFeature customFeature = foodItem.getFeatures().get(count);
//                            if (addedFeatureDTO.getLevel() != -1){
//                                AddedFeature addedFeature = new AddedFeature();
//                                addedFeature.setLevel(foodItem.getFeatures().get(count).getLevels().get(addedFeatureDTO.getLevel()));
//                                addedFeature.setAdditionalPrice(foodItem.getFeatures().get(count).getAdditionalPrices().get(addedFeatureDTO.getLevel()).floatValue());
//                                addedFeature.setName(foodItem.getFeatures().get(count).getName());
//                                addedFeatureList.add(addedFeature);
//                            }
//                            count++;
//                        }
//
//
//                        orderItemsDTO.setAddedFeatures(addedFeatureList);
//                    }
//                });

        //handling offline orders
        if(order.getOrderType().equals("OFFLINE")) {
            //validate cashier id
            if(order.getCashierId() == null || order.getCashierId().isEmpty()) {
                throw new IllegalStateException("CashierId cannot be null or empty");
            }
//            if(!UserValidation.isValidCashierId(order.getCashierId())) {
//                throw new IllegalStateException("Invalid cashier id");
//            }
            //validate waiter id
            if(!(order.getWaiterId() == null || order.getWaiterId().isEmpty())) {
                order.setWaiterId(null);
            }

        }
        //Handling online orders
        else if(order.getOrderType().equals("ONLINE")) {

            //validate customer id
            if(order.getCustomerId() == null || order.getCustomerId().isEmpty()) {
                throw new IllegalStateException("CustomerId cannot be null or empty");
            }
//            if(!UserValidation.isValidCustomerId(order.getCustomerId())) {
//                throw new IllegalStateException("Invalid customer id");
//            }
//            Optional<User> user = userRepository.findById(order.getCustomerId());
            //validate location
            if(order.getLocation() == null || order.getLocation().isEmpty()) {
                throw new IllegalStateException("Location cannot be null or empty");
            }
            //validate contact number
            if(order.getContactNo() == null || order.getContactNo().isEmpty()) {
                throw new IllegalStateException("Contact number cannot be null or empty");
            } else if (order.getContactNo().length() != 10){
                throw new IllegalStateException("Invalid contact number");
            }
            //validate delivery person id
            if(!(order.getDeliveryPersonId() == null || order.getDeliveryPersonId().isEmpty())) {
                order.setDeliveryPersonId(null);

//                if(!UserValidation.isValidDeliveryPersonId(order.getDeliveryPersonId())) {
//                    throw new IllegalStateException("Invalid delivery person id");
//                }
            }
        }
        //invalid order type
        else {
            throw new IllegalStateException("Invalid order type");
        }
        //validate order items
        if(order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalStateException("Order items cannot be null or empty");
        }
        //validate cafeId
        if(order.getCafeId() == null || order.getCafeId().isEmpty()) {
            throw new IllegalStateException("CafeId cannot be null or empty");
        }
        //check cafeId is existing
//        if(cafeRepository.findById(order.getCafeId()).isEmpty()) {
//            throw new IllegalStateException("CafeId does not exist");
//        }
        //validate total amount
        if(order.getTotalAmount() == null || order.getTotalAmount() == 0){
            throw new IllegalStateException("Total amount cannot be null or empty");
        }
//        -----------------------------------------------------------------------------
        if(!FoodAmountValidation.isValidFoodAmount(order.getTotalAmount(), order.getOrderItems())){
            throw new IllegalStateException("Invalid total amount");
        }
//        ------------------------------------------------------------------------------
        //validate order status
        if(order.getDiscount() == null || order.getDiscount() == 0) {
            order.setDiscount(0.0f);
        }

        //set order status to pending and set dates
        order.setOrderStatus(OrderStatus.valueOf("PENDING"));
        SimpleDateFormat DateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        order.setCreatedAt(DateTimeFormatter.format(date));
        order.setUpdatedAt(DateTimeFormatter.format(date));

        //set order status in items
        order.getOrderItems().forEach(item -> item.setOrderStatus(OrderStatus.PENDING));

        orderRepository.save(order);

    }


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(String orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order with id " + orderId + " does not exists");
        }
        orderRepository.deleteById(orderId);
    }

    public void updateOrder(Order order) {
        Order orderToUpdate = orderRepository.findById(order.getId())
                .orElseThrow(() -> new IllegalStateException("Order with id " + order.getId() + " does not exist"));

        if (order.getTotalAmount() != null && order.getTotalAmount() != 0 && !orderToUpdate.getTotalAmount().equals(order.getTotalAmount())) {
            orderToUpdate.setTotalAmount(order.getTotalAmount());
        }
        if (order.getOrderStatus() != null && !orderToUpdate.getOrderStatus().equals(order.getOrderStatus())) {
            orderToUpdate.setOrderStatus(order.getOrderStatus());
        }
        if (order.getCafeId() != null && !order.getCafeId().isEmpty() && !orderToUpdate.getCafeId().equals(order.getCafeId())) {
            orderToUpdate.setCafeId(order.getCafeId());
        }
        if (order.getCreatedAt() != null && !order.getCreatedAt().isEmpty() && !orderToUpdate.getCreatedAt().equals(order.getCreatedAt())) {
            orderToUpdate.setCreatedAt(order.getCreatedAt());
        }
        if (order.getUpdatedAt() != null && !order.getUpdatedAt().isEmpty() && !orderToUpdate.getUpdatedAt().equals(order.getUpdatedAt())) {
            orderToUpdate.setUpdatedAt(order.getUpdatedAt());
        }
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty() && !orderToUpdate.getOrderItems().equals(order.getOrderItems())) {
            orderToUpdate.setOrderItems(order.getOrderItems());
        }
        if (order.getCustomerId() != null && !order.getCustomerId().isEmpty() && !orderToUpdate.getCustomerId().equals(order.getCustomerId())) {
            orderToUpdate.setCustomerId(order.getCustomerId());
        }
        if (order.getLocation() != null && !order.getLocation().isEmpty() && !orderToUpdate.getLocation().equals(order.getLocation())) {
            orderToUpdate.setLocation(order.getLocation());
        }
        if (order.getContactNo() != null && !order.getContactNo().isEmpty() && !orderToUpdate.getContactNo().equals(order.getContactNo())) {
            orderToUpdate.setContactNo(order.getContactNo());
        }
        if (order.getDeliveryPersonId() != null && !order.getDeliveryPersonId().isEmpty() && !orderToUpdate.getDeliveryPersonId().equals(order.getDeliveryPersonId())) {
            orderToUpdate.setDeliveryPersonId(order.getDeliveryPersonId());
        }
        if (order.getDiscount() != null && order.getDiscount() != 0 && !orderToUpdate.getDiscount().equals(order.getDiscount())) {
            orderToUpdate.setDiscount(order.getDiscount());
        }

        orderRepository.save(orderToUpdate);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order with id " + orderId + " does not exist"));
    }

    public List<Order> getOrdersByCustomerId(String userId) {
        return orderRepository.findByCustomerId(userId);
    }

    public List<Order> getOrdersByWaiterId(String userId) {
        return orderRepository.findByWaiterId(userId);
    }

    public List<Order> getOrdersByCashierId(String userId) {
        return orderRepository.findByCashierId(userId);
    }

    public List<Order> getOrdersByCafeId(Long cafeId) {
        return orderRepository.findByCafeId(cafeId);
    }

    public List<Order> getOrdersByDeliveryPersonId(String userId) {
        return orderRepository.findByDeliveryPersonId(userId);
    }

    public List<Order> getPendingOrdersbywaiterId(String userId) {
        return orderRepository.findByWaiterIdAndOrderStatus(userId, OrderStatus.READY);
    }

    public List<Order> getPendingOrdersbydeliveryPersonId(String userId) {
        return orderRepository.findByDeliveryPersonIdAndOrderStatus(userId, OrderStatus.READY);
    }

    public void changeOrderStatus(OrderStatusChangeRequest orderStatusChangeRequest) {
        if(orderStatusChangeRequest == null || orderStatusChangeRequest.getId() == null || orderStatusChangeRequest.getOrderStatus() == null) {
            throw new IllegalStateException("OrderStatusChangeRequest cannot be null");
        }
        else {
            try {
                System.out.println("Changing order status" + orderStatusChangeRequest.getId() + " " + orderStatusChangeRequest.getOrderStatus());
                Order order = orderRepository.findById(orderStatusChangeRequest.getId())
                        .orElseThrow(() -> new IllegalStateException("Order with id " + orderStatusChangeRequest.getId() + " does not exist"));

                if(!(orderStatusChangeRequest.getOrderStatus().equals(OrderStatus.PREPARING) || orderStatusChangeRequest.getOrderStatus().equals(OrderStatus.READY) || orderStatusChangeRequest.getOrderStatus().equals(OrderStatus.DELIVERED))){
                    throw new IllegalStateException("Invalid OrderStatusChangeRequest");
                }
                if(order.getOrderStatus().equals(orderStatusChangeRequest.getOrderStatus())) {
                    throw new IllegalStateException("Nothing to change");
                }
                order.setOrderStatus(orderStatusChangeRequest.getOrderStatus());
                SimpleDateFormat DateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                order.setUpdatedAt(DateTimeFormatter.format(date));

                if(orderStatusChangeRequest.getOrderStatus().equals(OrderStatus.READY)) {
                    if(order.getOrderType().equals("ONLINE")) {
                        order.setDeliveryPersonId(findDeliveryPerson(order.getCafeId()));
                        if(order.getDeliveryPersonId().equals("No delivery person available")) {
                            throw new IllegalStateException("No delivery person available");
                        }
                    }
                    else if(order.getOrderType().equals("OFFLINE")) {
                        order.setWaiterId(findWaiter(order.getCafeId()));
                        if(order.getWaiterId().equals("No waiter available")) {
                            throw new IllegalStateException("No waiter available");
                        }
                    }
                }
                orderRepository.save(order);
            } catch (Exception e) {
                throw new IllegalStateException("OrderStatusChangeRequest cannot be null");
            }
        }
    }

    private String findWaiter(String cafeId) {
        System.out.println("Finding waiter" + cafeId);
        List<User> waiters = userRepository.findUserByCafeIdAndRole(cafeId,"WAITER");
        System.out.println("Waiters found " + waiters.size());
        if(waiters.isEmpty()) {
            System.out.println("No waiter available");
            return "No waiter available";
        }

        waiters.sort(Comparator.comparingLong(User::getStatusUpdatedAt).reversed());
        System.out.println("Waiter found" + waiters.get(0).getUserId() + "waiter name" + waiters.get(0).getFirstName() + " " + waiters.get(0).getLastName());
        return waiters.get(0).getUserId();
    }

    private String findDeliveryPerson(String cafeId) {
        List<User> deliveryPersons = userRepository.findUserByCafeIdAndRole(cafeId,"DELIVERY_PERSON");
        if(deliveryPersons.isEmpty()) {
            return "No delivery person available";
        }

        deliveryPersons.sort(Comparator.comparingLong(User::getStatusUpdatedAt).reversed());
        return deliveryPersons.get(0).getUserId();
    }


    public void updateOrderStatus(ItemStatusChangeDTO itemStatusChangeDTO) {
        System.out.println("Updating order status" + itemStatusChangeDTO.getOrderId() + " " + itemStatusChangeDTO.getItemId() + " " + itemStatusChangeDTO.getStatus());
        Order orderToUpdate = orderRepository.findById(itemStatusChangeDTO.getOrderId())
                .orElseThrow(() -> new IllegalStateException("Order with id " + itemStatusChangeDTO.getOrderId() + " does not exist"));

        int queueItems = 0;
        int processingItems = 0;
        int readyItems = 0;
        int deliveringItems = 0;
        int deliveredItems = 0;

        orderToUpdate.getOrderItems().forEach(item -> {
            if(item.getFoodId().equals(itemStatusChangeDTO.getItemId())) {
                item.setOrderStatus(itemStatusChangeDTO.getStatus());
            }
        });

        for(int i = 0; i < orderToUpdate.getOrderItems().size(); i++) {
            if(orderToUpdate.getOrderItems().get(i).getOrderStatus().equals(OrderStatus.PENDING)) {
                queueItems++;
            }
            else if(orderToUpdate.getOrderItems().get(i).getOrderStatus().equals(OrderStatus.PREPARING)) {
                processingItems++;
            }
            else if(orderToUpdate.getOrderItems().get(i).getOrderStatus().equals(OrderStatus.READY)) {
                readyItems++;
            }
            else if(orderToUpdate.getOrderItems().get(i).getOrderStatus().equals(OrderStatus.DELIVERING)) {
                deliveringItems++;
            }
            else if(orderToUpdate.getOrderItems().get(i).getOrderStatus().equals(OrderStatus.DELIVERED)) {
                deliveredItems++;
            }
        }

        if(processingItems != 0) {
            orderToUpdate.setOrderStatus(OrderStatus.PREPARING);
            orderToUpdate.setUpdatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        }
        else if(queueItems == 0 && processingItems == 0 && readyItems != 0 ){
            orderToUpdate.setOrderStatus(OrderStatus.READY);
            orderToUpdate.setUpdatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            if(orderToUpdate.getOrderType().equals("ONLINE")) {
                orderToUpdate.setDeliveryPersonId(findDeliveryPerson(orderToUpdate.getCafeId()));
                if(orderToUpdate.getDeliveryPersonId().equals("No delivery person available")) {
                    throw new IllegalStateException("No delivery person available");
                }
            }
            else {
                orderToUpdate.setWaiterId(findWaiter(orderToUpdate.getCafeId()));
                if(orderToUpdate.getWaiterId().equals("No waiter available")) {
                    throw new IllegalStateException("No waiter available");
                }
            }
        }
        else if(readyItems == 0 && processingItems == 0 && queueItems == 0 && deliveringItems != 0 && deliveredItems != 0){
            orderToUpdate.setOrderStatus(OrderStatus.DELIVERING);
            orderToUpdate.setUpdatedAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        }
//        else if(deliveringItems == 0 && readyItems == 0 && processingItems == 0 && queueItems == 0 && deliveredItems != 0){
//            orderToUpdate.setOrderStatus(OrderStatus.DELIVERED);
//        }

        orderRepository.save(orderToUpdate);
    }

    public List<Order> getQueueItems(Long cafeId) {
        if(cafeId == null) {
            throw new IllegalStateException("CafeId cannot be null");
        }
        return orderRepository.findByCafeIdAndOrderStatus(cafeId, OrderStatus.PENDING);
    }

    public List<Order> getPreparingItems(Long cafeId) {
        if(cafeId == null) {
            throw new IllegalStateException("CafeId cannot be null");
        }
        return orderRepository.findByCafeIdAndOrderStatus(cafeId, OrderStatus.PREPARING);
    }

    public List<Order> getReadyItems(Long cafeId) {
        if(cafeId == null) {
            throw new IllegalStateException("CafeId cannot be null");
        }
        return orderRepository.findByCafeIdAndOrderStatus(cafeId, OrderStatus.READY);
    }

    public void deliveryOrderStatus(ItemStatusChangeDTO itemStatusChangeDTO) {
        Order orderToUpdate = orderRepository.findById(itemStatusChangeDTO.getOrderId())
                .orElseThrow(() -> new IllegalStateException("Order with id " + itemStatusChangeDTO.getOrderId() + " does not exist"));

        orderToUpdate.getOrderItems().forEach(item -> {
            item.setOrderStatus(itemStatusChangeDTO.getStatus());
        });

        orderToUpdate.setOrderStatus(itemStatusChangeDTO.getStatus());

        orderRepository.save(orderToUpdate);

    }


    public List<Order> getCompletedOrdersByDeliveryPersonId(String userId) {
        return orderRepository.findByDeliveryPersonIdAndOrderStatus(userId, OrderStatus.DELIVERED);
    }

    public List<Order> ondeliveryordersbydeliverypersonid(String userId) {
        return orderRepository.findByDeliveryPersonIdAndOrderStatus(userId, OrderStatus.DELIVERING);
    }

    public List<Order> getSalesInThisWeek(Long cafeId) {
        List<Order> orders = orderRepository.findByCafeId(cafeId);
        List<Order> salesInThisWeek = new ArrayList<>();
        for(Order order : orders) {
            if(isInThisWeek(order.getCreatedAt())) {
                salesInThisWeek.add(order);
            }
        }
        return salesInThisWeek;
    }

    public List<Order> getSalesInLastWeek(Long cafeId) {
        List<Order> orders = orderRepository.findByCafeId(cafeId);
        List<Order> salesInLastWeek = new ArrayList<>();
        for(Order order : orders) {
            if(isInLastWeek(order.getCreatedAt())) {
                salesInLastWeek.add(order);
            }
        }
        return salesInLastWeek;
    }

    public boolean isInThisWeek(String createdAt) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            // Parse the createdAt string to a Date object
            Date createdAtDate = dateTimeFormatter.parse(createdAt);

            // Get the current date and time
            Date now = new Date();

            // Calculate the date and time exactly one week ago from now
            Date oneWeekAgo = new Date(now.getTime() - 7 * 24 * 3600 * 1000);

            // Check if createdAtDate is within the last week
            return createdAtDate.after(oneWeekAgo) && createdAtDate.before(now);
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Return false if there's a parsing error
        }
    }
    public boolean isInLastWeek(String createdAt) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            // Parse the createdAt string to a Date object
            Date createdAtDate = dateTimeFormatter.parse(createdAt);

            // Get the current date and time
            Date now = new Date() ;

            // Calculate the date and time exactly one week ago from now
            Date oneWeekAgo = new Date(now.getTime() - 7 * 24 * 3600 * 1000);

            Date twoWeekAgo = new Date(now.getTime() - 14 * 24 * 3600 * 1000);

            // Check if createdAtDate is within the last week
            return createdAtDate.after(twoWeekAgo) && createdAtDate.before(oneWeekAgo);
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Return false if there's a parsing error
        }
    }

    public BranchStat getBranchStatistics(Long cafeId) {
        BranchStat branchStat = new BranchStat();
        List<Order> orders = orderRepository.findByCafeId(cafeId);
        List<Order> salesInThisWeek = new ArrayList<>();
        List<Order> salesInLastWeek = new ArrayList<>();
        Map<String,Integer> foodSalesThisWeek = new HashMap<>();
        Map<String,Integer> foodSalesLastWeek = new HashMap<>();

        for(Order order : orders) {
            if(isInThisWeek(order.getCreatedAt())) {
                salesInThisWeek.add(order);
                for(OrderFood orderFood : order.getOrderItems()) {
                    if(foodSalesThisWeek.containsKey(orderFood.getFoodId())) {
                        foodSalesThisWeek.put(orderFood.getFoodId(), foodSalesThisWeek.get(orderFood.getFoodId()) + orderFood.getQuantity());
                    }
                    else {
                        foodSalesThisWeek.put(orderFood.getFoodId(), orderFood.getQuantity());
                    }
                }
            }
            if(isInLastWeek(order.getCreatedAt())) {
                salesInLastWeek.add(order);
            }
        }

        return branchStat;

    }

}
