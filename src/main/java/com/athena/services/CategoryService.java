package com.athena.services;

import com.athena.dao.CategoryDAO;
import com.athena.entities.Category;
import com.athena.entities.Item;
import com.athena.entities.ModEntry;
import com.athena.model.CategoryDTO;
import com.athena.model.ItemDTO;
import com.athena.model.ModEntryDTO;
import com.athena.model.ModifierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService
{
    private CategoryDAO catDao;
    private ItemService itemService;

    @Autowired
    public CategoryService(CategoryDAO catDao, ItemService itemService)
    {
        this.catDao = catDao;
        this.itemService = itemService;
    }

    public List<CategoryDTO> getAllCats() throws Exception
    {
        List<Category> allCats = catDao.getAllCategories();
        List<CategoryDTO> allCatsDTO = new ArrayList<>();
        for (Category cat : allCats)
        {
            CategoryDTO catDto = new CategoryDTO();
            catDto.setName(cat.getName());
            catDto.setIndex(cat.getIndex());
            List<ItemDTO> allItems = new ArrayList<>();
            if(cat.getAllItems() == null || cat.getAllItems().isEmpty())
                continue;
            allItems = itemService.getItems(cat);
            catDto.setAllItems(allItems);

            allCatsDTO.add(catDto);
        }

        return allCatsDTO;
    }

}
