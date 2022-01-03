package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Bike {
    private String name;
    private String bikeCode;
    private int type;
    private String parkCode;
	private int status;
	private float weight;
	private String licensePlate;
	private Date manuafacturingDate;
	private String producer;
	private float cost;
	private Date startTime;

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

    public void rentBike() {
        this.status = 1;
        this.startTime = new Date();
        this.parkCode = null;
    }

        public String getParkCode () {
            return parkCode;
        }

        public void setParkCode (String parkCode){
            this.parkCode = parkCode;
        }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", bikeCode='" + bikeCode + '\'' +
                ", type=" + type +
                ", parkCode='" + parkCode + '\'' +
                ", status=" + status +
                ", weight=" + weight +
                ", licensePlate='" + licensePlate + '\'' +
                ", manuafacturingDate=" + manuafacturingDate +
                ", producer='" + producer + '\'' +
                ", cost=" + cost +
                ", startTime=" + startTime +
                '}';
    }
}

