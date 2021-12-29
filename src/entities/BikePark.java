package entities;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class BikePark {
    private String name;
    private String address;
    private List<Bike> bikes;
    private int numOfBikes;
    private int numOfEBikes;
    private int numOfTwinBikes;
    private int numOfEmptyDocks;
    private String imageURL;

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

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.numOfBikes = 0;
        this.numOfEBikes =0;
        this.bikes = bikes;
        for (Bike bike: bikes) {
            if(bike.getType()==1)
                this.numOfBikes ++;
            else
                this.numOfEBikes++;
        }
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

    public void rentBike(String bikeCode){
        for(int i=0; i<this.bikes.size(); i++){
            Bike bike = this.bikes.get(i);
            if(bike.getBikeCode().equals(bikeCode)){
                this.bikes.remove(i);
                bike.setStatus(1);
                bike.setStartTime(new Date());
            }
        }
    }

    public void returnBike(Bike bike){
        bike.setStatus(0);
        bike.setStartTime(null);
        this.bikes.add(bike);
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
            List<BikePark> bikeParks = gson.fromJson(new FileReader("src/entities/data/bikeparks.json"), type);
            return bikeParks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNumOfTwinBikes() {
        return numOfTwinBikes;
    }

    public void setNumOfTwinBikes(int numOfTwinBikes) {
        this.numOfTwinBikes = numOfTwinBikes;
    }
}
