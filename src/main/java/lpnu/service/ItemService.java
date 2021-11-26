package lpnu.service;

import lpnu.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(long id);
    ItemDTO saveItem(ItemDTO itemDTO);
    ItemDTO updateItem(ItemDTO itemDTO);
    void deleteItemById(long id);
}
