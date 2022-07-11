package server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
    private final Path currentRelativePath = Paths.get("");
    final private String s = currentRelativePath.toAbsolutePath().toString();
    String temp = "/JSON Database/task";
    private final String FILENAME_TEST_ENVIRONMENT = s+"/src/server/data/db.json";
    private final ReadWriteLock readWriteLock
            = new ReentrantReadWriteLock();
    private final Lock writeLock
            = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    private JsonObject database;

    public Database() {
        System.out.println(FILENAME_TEST_ENVIRONMENT);
        try {
            String contents = Files.readString(Paths.get(FILENAME_TEST_ENVIRONMENT));
            database = new Gson().fromJson(contents, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setValue(JsonElement key, JsonElement message) {
        try {
            writeLock.lock();
            if (Objects.equals(database, null)) {
                database = new JsonObject();
                database.add(key.getAsString(), message);
            } else {
                if (key.isJsonPrimitive())
                    database.add(key.getAsString(), message);
                else {
                    JsonArray keys = key.getAsJsonArray();
                    String keysAdd = keys.remove(keys.size() - 1).getAsString();

                    finding(keys).getAsJsonObject().add(keysAdd, message);
                }
            }
            this.updateDatabaseFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }

    }

    private void updateDatabaseFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILENAME_TEST_ENVIRONMENT);
            fileWriter.write(database.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonElement getValue(JsonElement key) throws Exception {
        try {
            readLock.lock();

            if (key.isJsonPrimitive() && database.has(key.getAsString()) ) {
                return database.get(key.getAsString());
            } else if (key.isJsonArray()) {
                JsonElement tmp2 = database;
                for (JsonElement k : key.getAsJsonArray()) {
                    if (!k.isJsonPrimitive() || !tmp2.getAsJsonObject().has(k.getAsString())) {
                        throw new Exception("No such key");
                    }
                    tmp2 = tmp2.getAsJsonObject().get(k.getAsString());
                }
                return tmp2;
            } else {
                throw new Exception("No such key");
            }
        }
        finally {
            readLock.unlock();
        }

    }

    public void deleteValue(JsonElement key) throws Exception {
        try {
            writeLock.lock();

            if (key.isJsonPrimitive() && database.has(key.getAsString())) {

                database.remove(key.getAsString());
            }
                else if(key.isJsonArray()){
                    JsonArray keys = key.getAsJsonArray();
                    String keysDelete = keys.remove(keys.size() - 1).getAsString();
                    finding(keys).getAsJsonObject().remove(keysDelete);
                }
            else {
                throw new Exception("No such key");
            }
            this.updateDatabaseFile();
        } finally {
            writeLock.unlock();
        }

    }

    private JsonElement finding(JsonArray keys) {

        JsonElement tmp2 = database;
        for (JsonElement k : keys) {
            if (!tmp2.getAsJsonObject().has(k.getAsString())) {
                tmp2.getAsJsonObject().add(k.getAsString(), new JsonObject());
            }

            tmp2 = tmp2.getAsJsonObject().get(k.getAsString());
        }
        return tmp2;
    }
}
