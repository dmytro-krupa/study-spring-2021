package lpnu.service.impl;

import lpnu.entity.Item;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.entity.enumeration.UserRole;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.mapper.UserToUserDTOMapper;
import lpnu.repository.OrderRepository;
import lpnu.repository.UserRepository;
import lpnu.service.OrderService;
import lpnu.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Test
    public void getTotalPriceForPeriodTest(){

        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final int firstDay = 10;
        final List<Order> orders = getTestOrders();
        final double expectedSum = orders.stream().filter(e -> e.getOrderTime().isAfter(LocalDateTime.now().minusDays(firstDay))).mapToDouble(Order::getTotalPrice).sum();

        when(orderRepository.getAllOrders()).thenReturn(orders);

        final double sum = orderService.getTotalPriceForPeriod(LocalDateTime.now().minusDays(firstDay), LocalDateTime.now());

        assertEquals(expectedSum, sum, 0.1);


    }

    @Test
    public void getTotalPriceForPeriodTest_wrongDateTimePeriod(){

        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final int firstDay = 10;
        final List<Order> orders = getTestOrders();
        final double expectedSum = orders.stream().filter(e -> e.getOrderTime().isAfter(LocalDateTime.now().minusDays(firstDay))).mapToDouble(Order::getTotalPrice).sum();

        when(orderRepository.getAllOrders()).thenReturn(orders);

        final double sum = orderService.getTotalPriceForPeriod(LocalDateTime.now(), LocalDateTime.now().minusDays(firstDay));

        assertEquals(0, sum, 0.1);


    }

//    @Test
//    public void getBestItem_success(){
//        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
//        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);
//
//        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);
//
//        ReflectionTestUtils.setField(orderService, "limit", 5);
//
//        final List<Order> orders = getTestOrders();
//        when(orderRepository.getAllOrders()).thenReturn(orders);
//
//        final User user1 = new User(1L, "", "", "", null);
//
//
//        final Item item = orderService.getBestItem(user1);
//
//        System.out.println();
//
//
//    }



    private List<Order> getTestOrders(){
        final User user1 = new User(1L, "", "", "", null);
        final User user2 = new User(2L, "", "", "", null);

        final Item item1 = new Item(1L, "item1", 15.);
        final Item item2 = new Item(2L, "item2", 25.);

        final Map<Item, Integer> itemMap1 = new HashMap<>();
        itemMap1.put(item1, 2);
        itemMap1.put(item2, 5);
        final Map<Item, Integer> itemMap2 = new HashMap<>();
        itemMap2.put(item1, 100);
        itemMap2.put(item2, 1);
        final Map<Item, Integer> itemMap3 = new HashMap<>();
        itemMap3.put(item1, 1);
        itemMap3.put(item2, 1);
        final Map<Item, Integer> itemMap4 = new HashMap<>();
        itemMap4.put(item1, 3);
        itemMap4.put(item2, 3);
        final Map<Item, Integer> itemMap5 = new HashMap<>();
        itemMap5.put(item1, 2);
        itemMap5.put(item2, 15);




        final Order order1 = new Order(1L, user1, itemMap1, null, 10., LocalDateTime.now().minusDays(5));
        final Order order2 = new Order(2L, user2, itemMap2, null, 2., LocalDateTime.now().minusDays(3));
        final Order order3 = new Order(3L, user1, itemMap3, null, 15., LocalDateTime.now().minusDays(4));
        final Order order4 = new Order(4L, user1, itemMap4, null, 100., LocalDateTime.now().minusDays(40));
        final Order order5 = new Order(5L, user1, itemMap5, null, 100., LocalDateTime.now().minusDays(40));

        return Arrays.asList(order1, order2, order3, order4, order5);
    }
}
