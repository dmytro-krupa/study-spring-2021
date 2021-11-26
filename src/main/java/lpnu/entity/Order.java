package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private User user;
    private Map<Item, Integer> itemMap = new HashMap<>(); // Integer - number of items
    private Fuel fuel;
    private Double totalPrice;
    private LocalDateTime orderTime;
}
