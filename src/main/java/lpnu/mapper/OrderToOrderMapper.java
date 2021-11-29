package lpnu.mapper;


import lpnu.dto.OrderDTO;
import lpnu.entity.Fuel;
import lpnu.entity.Order;
import lpnu.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderToOrderMapper {

    @Autowired
    private FuelToFuelDTOMapper fuelMapper;
    @Autowired
    private UserToUserDTOMapper userMapper;


    @Autowired
    private ItemRepository itemRepository;

    public Order toEntity(final OrderDTO orderDTO) {
        final Order order = new Order();


        order.setId(orderDTO.getId());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setTotalPrice(orderDTO.getTotalPrice());

        order.setUser(userMapper.toEntity(orderDTO.getUser()));
        order.setFuel(fuelMapper.toEntity(orderDTO.getFuel()));

        order.setItemMap(orderDTO.getItemMap().entrySet().stream().collect(Collectors.toMap(e -> itemRepository.getItemById(e.getKey()), Map.Entry::getValue)));


        return order;
    }

    public OrderDTO toDTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setTotalPrice(order.getTotalPrice());

        orderDTO.setUser(userMapper.toDTO(order.getUser()));
        orderDTO.setFuel(fuelMapper.toDTO(order.getFuel()));
        orderDTO.setItemMap(order.getItemMap().entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getId(), Map.Entry::getValue)));

        return orderDTO;
    }
}
