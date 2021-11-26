package lpnu.service;

import lpnu.entity.Item;
import lpnu.entity.User;

import java.time.LocalDateTime;

public interface OrderService {
    double getTotalPriceForPeriod(LocalDateTime start, LocalDateTime end);
    Item getBestItem(User user);
}
