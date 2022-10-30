package org.homework.repository;

import com.google.gson.Gson;
import org.homework.entity.Account;
import org.homework.entity.Indexable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Repository <K,V extends Indexable>{

    private Gson gson = new Gson();
    private String filePath;
    private Class<V[]> storageType;

    private Map<K,V> entityMap;

    public Repository(String filePath, Class storageType) {
        this.filePath = filePath;
        this.storageType = storageType;
        try (FileReader file = new FileReader(filePath)) {

            BufferedReader accountsReader = new BufferedReader(file);
            V[] entities = gson.fromJson(accountsReader, (Type) storageType);
            entityMap= Arrays.stream(entities).collect(Collectors.toMap((entity) -> (K) entity.getId(), Function.identity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public V getValue(K key) {
        return entityMap.get(key);
    }
    public void updateValue(K key, V value) {
        if (key.equals(value.getId())) {
            entityMap.replace(key, value);
        }
    }

}
