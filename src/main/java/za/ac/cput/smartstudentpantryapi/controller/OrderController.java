package za.ac.cput.smartstudentpantryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.smartstudentpantryapi.dto.OrderRequest;
import za.ac.cput.smartstudentpantryapi.model.PantryOrder;
import za.ac.cput.smartstudentpantryapi.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<PantryOrder> checkout(@RequestBody OrderRequest request) {
        try {
            PantryOrder newOrder = orderService.placeOrder(request);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}