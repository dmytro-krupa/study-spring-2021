package lpnu.resource;

import lpnu.dto.ItemDTO;
import lpnu.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemResource {

    private ItemService itemService;

    public ItemResource(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public ItemDTO getItemById(@PathVariable final long id) {
        return itemService.getItemById(id);
    }


    @PostMapping("/items")
    public ItemDTO saveItem(@RequestBody @Validated final ItemDTO itemDTO) {
        return itemService.saveItem(itemDTO);
    }


    @PutMapping("/items")
    public ItemDTO updateItem(@RequestBody @Validated final ItemDTO itemDTO) {
        return itemService.updateItem(itemDTO);
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity deleteItemById(@PathVariable final long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.ok().build();
    }
}

