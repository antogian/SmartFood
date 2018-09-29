package com.athena.services;

import com.athena.entities.Category;
import com.athena.entities.Item;
import com.athena.model.ItemDTO;
import com.athena.model.ModifierDTO;
import com.athena.model.SizeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService
{
    private ModifierService modifierService;
    private SizeService sizeService;

    @Autowired
    public ItemService(ModifierService modifierService, SizeService sizeService)
    {
        this.modifierService = modifierService;
        this.sizeService = sizeService;
    }

    List<ItemDTO> getItems(Category category)
    {
        List<ItemDTO> allItems = new ArrayList<>();

        for(Item item : category.getAllItems())
        {
            ItemDTO itemDto = new ItemDTO();
            //----------------------------------------------------------------------------------------------------------
            itemDto.setName(item.getName());
            itemDto.setId(UUID.randomUUID().toString());
            itemDto.setIndex(item.getIndex());
            itemDto.setFreeModEntries(item.getFreeModEntries());
            //----------------------------------------------------------------------------------------------------------

            //----------------------------------------------------------------------------------------------------------
            List<SizeDTO> allSizes = new ArrayList<>();
            if(item.getSize() == null)
            {
                itemDto.setAllSizes(allSizes);
            }
            else
            {
                allSizes = sizeService.getSizes(item.getSize());
                for(int i = 0; i < allSizes.size(); i++)
                {
                    allSizes.get(i).setCost(item.getCost()[i]);
                }
                allSizes.get(0).setSelected(true);

                itemDto.setAllSizes(allSizes);
            }
            itemDto.setCost(item.getCost()[0]);
            itemDto.setTotalCost(item.getCost()[0]);
            //----------------------------------------------------------------------------------------------------------
            List<ModifierDTO> allModifiers = new ArrayList<>();
            if(item.getModifiers() == null || item.getModifiers().isEmpty())
            {
                itemDto.setModifiers(allModifiers);
            }
            else
            {
                allModifiers = modifierService.getModifiers(item);
                itemDto.setModifiers(allModifiers);
            }
            //----------------------------------------------------------------------------------------------------------
            allItems.add(itemDto);
        }
        return allItems;
    }

    public ItemDTO getItemByValue(ItemDTO item)
    {
        ItemDTO newItem = new ItemDTO();

        newItem.setName(item.getName());
        newItem.setIndex(item.getIndex());
        newItem.setCost(item.getCost());
        newItem.setTotalCost(item.getTotalCost());
        newItem.setFreeModEntries(item.getFreeModEntries());

        List<ModifierDTO> allModifiers = new ArrayList<ModifierDTO>();
        for(ModifierDTO mod : item.getModifiers())
        {
            allModifiers.add(modifierService.getValues(mod));
        }
        newItem.setModifiers(allModifiers);

        List<SizeDTO> allSizes = new ArrayList<>();
        if(!(item.getAllSizes() == null || item.getAllSizes().isEmpty()))
        {
            for(SizeDTO size : item.getAllSizes())
            {
                SizeDTO newSize = new SizeDTO();
                newSize.setName(size.getName());
                newSize.setIndex(size.getIndex());
                newSize.setSelected(size.isSelected());
                newSize.setCost(size.getCost());
                allSizes.add(newSize);
            }
        }
        newItem.setAllSizes(allSizes);

        return newItem;
    }

}
