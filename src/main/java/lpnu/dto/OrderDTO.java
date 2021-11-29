package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private UserDTO user;
    private Map<Long, Integer> itemMap; // Integer - number of items
    private FuelDTO fuel;
    private Double totalPrice;
    private LocalDateTime orderTime;
}
