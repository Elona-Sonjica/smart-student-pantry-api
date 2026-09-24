package za.ac.cput.smartstudentpantryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.smartstudentpantryapi.dto.OrderRequest;
import za.ac.cput.smartstudentpantryapi.model.OrderItem;
import za.ac.cput.smartstudentpantryapi.model.PantryOrder;
import za.ac.cput.smartstudentpantryapi.model.StudentSubscription;
import za.ac.cput.smartstudentpantryapi.repository.OrderItemRepository;
import za.ac.cput.smartstudentpantryapi.repository.PantryOrderRepository;
import za.ac.cput.smartstudentpantryapi.repository.StudentSubscriptionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private PantryOrderRepository pantryOrderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private StudentSubscriptionRepository subscriptionRepository;

    @Transactional
    public PantryOrder placeOrder(OrderRequest request) {
        // 1. Create the Main Order
        PantryOrder order = new PantryOrder();
        order.setStudentId(request.getStudentId());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        PantryOrder savedOrder = pantryOrderRepository.save(order);

        // 2. Loop through cart items and route them to the correct tables
        for (OrderRequest.CartItem item : request.getCartItems()) {

            if (item.getIsSubscription() != null && item.getIsSubscription()) {
                // Save to subscriptions table
                StudentSubscription sub = new StudentSubscription();
                sub.setStudentId(request.getStudentId());
                sub.setPlanName(item.getName());
                sub.setMonthlyPrice(item.getPrice());
                sub.setStartDate(LocalDate.now());
                sub.setIsActive(true);
                subscriptionRepository.save(sub);
            } else {
                // Save to regular order items table
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(savedOrder.getId());
                orderItem.setProductName(item.getName());
                orderItem.setQuantity(item.getQty());
                orderItem.setPrice(item.getPrice());
                orderItemRepository.save(orderItem);
            }
        }
        return savedOrder;
    }
}