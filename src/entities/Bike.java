package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> e7aed2bc45babf3ae872d3757b63e690c187819f
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Bike {
    private String name;
    private String bikeCode;
    private int type;
<<<<<<< HEAD
    private float weight;
    private String licensePlate;
    private Date manuafacturingDate;
    private String producer;
    private float cost;
    private int status;
    private Date startTime;
=======
>>>>>>> e7aed2bc45babf3ae872d3757b63e690c187819f
    private String parkCode;

    public Bike() {
    }

    public Bike(String name, String bikeCode, int type, int status) {
        this.name = name;
        this.bikeCode = bikeCode;
        this.type = type;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getManuafacturingDate() {
        return manuafacturingDate;
    }

    public void setManuafacturingDate(Date manuafacturingDate) {
        this.manuafacturingDate = manuafacturingDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(String bikeCode) {
        this.bikeCode = bikeCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

<<<<<<< HEAD
    public void rentBike(){
        this.status = 1;
        this.startTime = new Date();
    }

    public void returnBike(String parkCode){
        this.status = 0;
        this.startTime = null;
        this.parkCode = parkCode;
    }


    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", bikeCode='" + bikeCode + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", licensePlate='" + licensePlate + '\'' +
                ", manuafacturingDate=" + manuafacturingDate +
                ", producer='" + producer + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                ", startTime=" + startTime +
                '}';
    }


=======
    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

>>>>>>> e7aed2bc45babf3ae872d3757b63e690c187819f
    public List<Bike> getAllBikes(){
        Type type = new TypeToken<List<Bike>>() {
        }.getType();
        Gson gson = new Gson();
        try {
            List<Bike> bikes = gson.fromJson(new FileReader("src/entities/data/bikes.json"), type);
<<<<<<< HEAD
            System.out.println(bikes);
=======
>>>>>>> e7aed2bc45babf3ae872d3757b63e690c187819f
            return bikes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(List<Bike> bikes){
        Gson gson = new Gson();
        try {
            gson.toJson(bikes, new FileWriter("src/entities/data/bikes.json"));
<<<<<<< HEAD
        } catch (IOException e) {
=======
        } catch (Exception e) {
>>>>>>> e7aed2bc45babf3ae872d3757b63e690c187819f
            e.printStackTrace();
        }
    }
}
