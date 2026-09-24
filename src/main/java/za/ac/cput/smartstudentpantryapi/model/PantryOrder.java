package za.ac.cput.smartstudentpantryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pantry_orders")
public class PantryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId; // Links to the Student table

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String status; // e.g., "PENDING", "DELIVERED"

    private LocalDateTime orderDate = LocalDateTime.now();
}