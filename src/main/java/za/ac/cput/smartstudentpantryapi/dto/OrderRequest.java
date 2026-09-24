package za.ac.cput.smartstudentpantryapi.dto;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long studentId;
    private String deliveryAddress;
    private Double totalAmount;
    private List<CartItem> cartItems;

    @Data
    public static class CartItem {
        private String name;
        private Integer qty;
        private Double price;
        private Boolean isSubscription;
    }
}