package com.athena.dao;

import com.athena.entities.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class CategoryDAO
{

    public CategoryDAO()
    {
    }

    public List<Category> getAllCategories() throws FileNotFoundException
    {
        Type REVIEW_TYPE = new TypeToken<List<Category>>() {}.getType();

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("D:\\Documents\\data.json"));
        List<Category> allCategories = gson.fromJson(reader, new TypeToken<List<Category>>(){}.getType());

        return allCategories;
    }

}
