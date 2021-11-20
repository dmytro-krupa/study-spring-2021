package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Item;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ItemRepository {
    private List<Item> items;

    private long id = 1;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("items.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            items = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<List<Item>>() {});
        } catch (final Exception e){
            System.out.println("We have an issue");
            items = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("items.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(items), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }





    //todo FINISH!!!!!!!





    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserById(final long id) {
        return users.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "user with id {" + id + "} not found"));
    }

    public User saveUser(final User user) {
        user.setId(id);

        ++id;

        users.add(user);
        return user;
    }

    public User updateUser(final User user) {

        final User savedUser = getUserById(user.getId());

        savedUser.setName(user.getName());
        savedUser.setSurname(user.getSurname());
        savedUser.setEmail(user.getEmail());

        return savedUser;
    }

    public void deleteUserById(final long id) {
        users = users.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }
}
