package za.ac.cput.smartstudentpantryapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.smartstudentpantryapi.model.StudentSubscription;

public interface StudentSubscriptionRepository extends JpaRepository<StudentSubscription, Long> {
}