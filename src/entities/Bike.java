package entities;

import java.util.Date;

public class Bike {
    private String name;
    private String bikeCode;
    private int type;

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

    private float weight;

    public Bike(String name, String bikeCode, int type, int status) {
        this.name = name;
        this.bikeCode = bikeCode;
        this.type = type;
        this.status = status;
    }

    private String licensePlate;
    private Date manuafacturingDate;
    private String producer;
    private float cost;
    private int status;
    private Date startTime;

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
}
