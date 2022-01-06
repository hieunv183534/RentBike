package entities.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.BikePark;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class BikeParkRepository implements RepositoryInterface<BikePark> {
    @Override
    public List<BikePark> getAll() {
        Type type = new TypeToken<List<BikePark>>() {
        }.getType();
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("src/entities/repository/bikeparks.json");
            List<BikePark> bikeParks = gson.fromJson(fileReader, type);
            fileReader.close();
            return bikeParks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(List<BikePark> bikeParks) {
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("src/entities/repository/bikeparks.json");
            gson.toJson(bikeParks, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
