package entities;

import java.util.List;

public class BikePart {
    private String name;
    private String address;
    private List<Bike> bikes;
    private int numOfBikes;
    private int numOfEBikes;
    private int numOfTwinBikes;

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
        this.bikes = bikes;
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

    public int getNumOfTwinBikes() {
        return numOfTwinBikes;
    }

    public void setNumOfTwinBikes(int numOfTwinBikes) {
        this.numOfTwinBikes = numOfTwinBikes;
    }
}
