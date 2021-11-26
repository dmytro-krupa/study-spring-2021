package lpnu.service.impl;

import lpnu.entity.Item;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    //todo remove and use repository instead
    private List<Order> orderList = new ArrayList<>();


    @Override
    public double getTotalPriceForPeriod(final LocalDateTime start, final LocalDateTime end) {
        return orderList.stream()
                .filter(e -> e.getOrderTime().isAfter(start) && e.getOrderTime().isBefore(end))
                .mapToDouble(Order::getTotalPrice)
                .sum();

    }

    @Override
    public Item getBestItem(final User user) {
        //todo finish
//        return orderList.stream()
//                .filter(e -> e.getUser().equals(user))
//                .sorted(Comparator.comparing(Order::getOrderTime))
//                .limit(5)

//                .map(e -> e.getItemMap())
//                .map(e -> e.entrySet())
//
//
//                .mapToDouble(Order::getTotalPrice)
//                .sum();

        return null;
    }
}
