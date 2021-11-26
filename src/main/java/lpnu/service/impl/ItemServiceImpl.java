package lpnu.service.impl;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import lpnu.mapper.ItemToItemDTOMapper;
import lpnu.repository.ItemRepository;
import lpnu.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemToItemDTOMapper itemMapper;
    private ItemRepository itemRepository;


    public ItemServiceImpl(final ItemToItemDTOMapper itemMapper, final ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.getAllItem().stream()
                .map(e -> itemMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(final long id) {
        return itemMapper.toDTO(itemRepository.getItemById(id));
    }

    @Override
    public ItemDTO saveItem(final ItemDTO itemDTO) {

        final Item item = itemMapper.toEntity(itemDTO);

        itemRepository.saveItem(item);

        return itemMapper.toDTO(item);
    }

    @Override
    public ItemDTO updateItem(final ItemDTO itemDTO) {
        final Item item = itemMapper.toEntity(itemDTO);

        return itemMapper.toDTO(itemRepository.updateItem(item));
    }

    @Override
    public void deleteItemById(final long id) {
        itemRepository.deleteItemById(id);
    }
}
