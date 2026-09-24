package za.ac.cput.smartstudentpantryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class StudentSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String planName;

    @Column(nullable = false)
    private Double monthlyPrice;

    private LocalDate startDate = LocalDate.now();

    @Column(nullable = false)
    private Boolean isActive = true;
}