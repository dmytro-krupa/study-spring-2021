package lpnu.repository;

import lpnu.entity.Order;
import lpnu.exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    private long id = 1;

//
//    @PostConstruct
//    public void init(){
//
//        final Path file = Paths.get("users.txt");
//        try {
//            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
//            users = JacksonUtil.deserialize(savedUsersAsString, new TypeReference<List<User>>() {});
//        } catch (final Exception e){
//            System.out.println("We have an issue");
//            users = new ArrayList<>();
//        }
//
//    }
//
//    @PreDestroy
//    public void preDestroy(){
//        final Path file = Paths.get("users.txt");
//        try {
//            Files.writeString(file, JacksonUtil.serialize(users), StandardCharsets.UTF_16);
//        } catch (final Exception e){
//            System.out.println("We have an issue");
//        }
//    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order getOrderById(final long id) {
        return orders.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Order with id {" + id + "} not found"));
    }

    public Order saveOrder(final Order order) {
        order.setId(id);

        ++id;

        orders.add(order);
        return order;
    }

    public Order updateOrder(final Order order) {

        final Order savedOrder = getOrderById(order.getId());

        savedOrder.setTotalPrice(order.getTotalPrice());
        savedOrder.setItemMap(order.getItemMap());
        savedOrder.setFuel(order.getFuel());
        savedOrder.setOrderTime(order.getOrderTime());
        savedOrder.setUser(order.getUser());

        return savedOrder;
    }

    public void deleteOrderById(final long id) {
        orders = orders.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }
}
