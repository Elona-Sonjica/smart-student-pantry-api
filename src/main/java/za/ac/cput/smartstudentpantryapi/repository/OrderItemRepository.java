package za.ac.cput.smartstudentpantryapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.smartstudentpantryapi.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}