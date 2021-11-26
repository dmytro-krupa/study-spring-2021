package lpnu.mapper;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDTOMapper {
    public Item toEntity(final ItemDTO itemDTO){
        final Item item = new Item();

        item.setId(itemDTO.getId());
        item.setTitle(itemDTO.getTitle());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    public ItemDTO toDTO(final Item item){
        final ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setTitle(item.getTitle());
        itemDTO.setPrice(item.getPrice());

        return itemDTO;
    }
}
