package org.homework.repository;

import com.google.gson.Gson;
import org.homework.entity.Indexable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Repository<K, V extends Indexable<K>> {

    private final Gson gson = new Gson();
    private final String filePath;

    private Map<K, V> entityMap;

    public Repository(String filePath, Class<V[]> storageType) {
        this.filePath = filePath;
        try (FileReader file = new FileReader(filePath)) {

            BufferedReader accountsReader = new BufferedReader(file);
            V[] entities = gson.fromJson(accountsReader, (Type) storageType);
            entityMap = Arrays.stream(entities).collect(Collectors.toMap(Indexable::getId, Function.identity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public V getValue(K key) {
        return entityMap.get(key);
    }

    public void sync() {
        try (FileWriter file = new FileWriter(this.filePath)) {
            this.gson.toJson(entityMap.values().toArray(), file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
