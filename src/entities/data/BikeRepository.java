package entities.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Bike;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class BikeRepository implements RepositoryInterface<Bike> {
    @Override
    public List<Bike> getAll() {
        Type type = new TypeToken<List<Bike>>() {
        }.getType();
        Gson gson = new Gson();
        try {
            List<Bike> bikes = gson.fromJson(new FileReader("src/entities/data/bikes.json"), type);
            return bikes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(List<Bike> bikes) {
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("src/entities/data/bikes.json");
            gson.toJson(bikes, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
