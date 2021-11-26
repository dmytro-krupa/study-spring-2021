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
    public void init() {

        final Path file = Paths.get("items.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            items = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<List<Item>>() {
            });

            if (items == null) {
                items = new ArrayList<>();
                return;
            }

            final long maxId = items.stream().mapToLong(Item::getId).max().orElse(1);

            this.id = maxId + 1;


        } catch (final Exception e) {
            System.out.println("We have an issue");
            items = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("items.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(items), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

    public List<Item> getAllItem() {
        return new ArrayList<>(items);
    }

    public Item getItemById(final long id) {
        return items.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "item with id {" + id + "} not found"));
    }

    public Item saveItem(final Item item) {
        item.setId(id);

        ++id;

        items.add(item);
        return item;
    }

    public Item updateItem(final Item item) {

        final Item savedItem = getItemById(item.getId());

        savedItem.setPrice(item.getPrice());
        savedItem.setTitle(item.getTitle());

        return savedItem;
    }

    public void deleteItemById(final long id) {
        items = items.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }
}
