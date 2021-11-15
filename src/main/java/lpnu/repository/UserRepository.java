package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.dto.UserDTO;
import lpnu.entity.User;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserRepository {
    private List<User> users;

    private long id = 1;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("users.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            users = JacksonUtil.deserialize(savedUsersAsString, new TypeReference<List<User>>() {});
        } catch (final Exception e){
            System.out.println("We have an issue");
            users = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("users.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(users), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

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
