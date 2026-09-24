package za.ac.cput.smartstudentpantryapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.smartstudentpantryapi.model.PantryOrder;

public interface PantryOrderRepository extends JpaRepository<PantryOrder, Long> {
}