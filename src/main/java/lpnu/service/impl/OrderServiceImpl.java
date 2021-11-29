package lpnu.service.impl;

import lpnu.dto.OrderDTO;
import lpnu.entity.Item;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.repository.OrderRepository;
import lpnu.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private OrderToOrderMapper orderMapper;

    @Value("${limit}")
    private int limit;

    public OrderServiceImpl(final OrderRepository orderRepository, final OrderToOrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.getAllOrders().stream()
                .map(e -> orderMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(final long id) {
        return orderMapper.toDTO(orderRepository.getOrderById(id));
    }

    @Override
    public OrderDTO saveOrder(final OrderDTO orderDTO) {
        final Order order = orderMapper.toEntity(orderDTO);

        orderRepository.saveOrder(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO updateOrder(final OrderDTO orderDTO) {

        final Order order = orderMapper.toEntity(orderDTO);

        return orderMapper.toDTO(orderRepository.updateOrder(order));
    }

    @Override
    public void deleteOrderById(final long id) {
        orderRepository.deleteOrderById(id);
    }


    @Override
    public double getTotalPriceForPeriod(final LocalDateTime start, final LocalDateTime end) {
        return orderRepository.getAllOrders().stream()
                .filter(e -> e.getOrderTime().isAfter(start) && e.getOrderTime().isBefore(end))
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    /*
        Повертає товар, який користувач купляв найчастіше за останні 5 покупок
     */
    @Override
    public Item getBestItem(final User user) {
        //todo test it
        return orderRepository.getAllOrders().stream()
                .filter(e -> e.getUser().equals(user))
                .sorted(Comparator.comparing(Order::getOrderTime))
                .limit(limit)

                .map(Order::getItemMap)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .findFirst()
                .get()
                .getKey();
    }
}
