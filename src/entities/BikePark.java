package entities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class BikePark {
    private String name;
    private String address;
    private int numOfBikes;
    private int numOfEBikes;
    private int numOfEmptyDocks;
    private String imageURL;
    private String code;
    public BikePark() {
    }

    public BikePark(String name, String address) {
        this.name = name;
        this.address = address;
        this.numOfBikes = 0;
        this.numOfEBikes =0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumOfBikes() {
        return numOfBikes;
    }

    public void setNumOfBikes(int numOfBikes) {
        this.numOfBikes = numOfBikes;
    }

    public int getNumOfEBikes() {
        return numOfEBikes;
    }

    public void setNumOfEBikes(int numOfEBikes) {
        this.numOfEBikes = numOfEBikes;
    }

    public void rentBike(int bikeType){
        if(bikeType==1){
            this.numOfBikes --;
        }else{
            this.numOfEBikes --;
        }
        this.numOfEmptyDocks++;
    }

    public void returnBike(int bikeType){
        if(bikeType==1){
            this.numOfBikes ++;
        }else{
            this.numOfEBikes ++;
        }
        this.numOfEmptyDocks--;
    }

    public int getNumOfEmptyDocks() {
        return numOfEmptyDocks;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setNumOfEmptyDocks(int numOfEmptyDocks) {
        this.numOfEmptyDocks = numOfEmptyDocks;
    }

    public List<BikePark> getAllBikeParks(){
        Type type = new TypeToken<List<BikePark>>() {
        }.getType();
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("src/entities/data/bikeparks.json");
            List<BikePark> bikeParks = gson.fromJson(fileReader, type);
            fileReader.close();
            return bikeParks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(List<BikePark> bikeParks){
        Gson gson = new Gson();
        try {
            gson.toJson(bikeParks, new FileWriter("src/entities/data/bikeparks.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BikePark{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numOfBikes=" + numOfBikes +
                ", numOfEBikes=" + numOfEBikes +
                ", numOfEmptyDocks=" + numOfEmptyDocks +
                ", imageURL='" + imageURL + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
