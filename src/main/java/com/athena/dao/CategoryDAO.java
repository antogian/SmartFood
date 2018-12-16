package com.athena.dao;

import com.athena.entities.Category;
//import com.athena.entities.Item;
//import com.athena.entities.Modifier;
//import com.athena.entities.Size;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
//import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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

        ClassLoader classLoader = getClass().getClassLoader();
        //TODO: Null Pointer exception needs handling
        File file = new File(classLoader.getResource("data/data.json").getFile());

        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        List<Category> allCategories = gson.fromJson(reader, new TypeToken<List<Category>>(){}.getType());

        return allCategories;
//        List<Category> allCats = getMongoCategories();
//        if(!allCats.isEmpty())
//            return allCats;
//
//        Gson gson = new Gson();
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream in = classLoader.getResourceAsStream("data/data.json");
//        JsonReader reader = new JsonReader(new InputStreamReader(in));
//        List<Category> allCategories = gson.fromJson(reader, new TypeToken<List<Category>>(){}.getType());
//
//        return allCategories;
    }
//
//    private List<Category> getMongoCategories()
//    {
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        MongoDatabase database = mongoClient.getDatabase("SmartFood");
//        //MongoCollection<Document> collection = database.getCollection("Categories");
//
//        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
//                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//        MongoDatabase mongoDatabase = database.withCodecRegistry(pojoCodecRegistry);
//
//        MongoCollection<Category> collectionCategory = mongoDatabase.getCollection("Categories", Category.class);
//
//        List<Category> allCats = (List<Category>) collectionCategory.find().into(new ArrayList<Category>());
//
//        if(allCats.isEmpty())
//            return new ArrayList<>();
//        else
//            return allCats;
//    }

}
