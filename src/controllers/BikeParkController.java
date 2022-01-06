package controllers;

import entities.BikePark;
import entities.repository.BikeParkRepository;

import java.util.List;

public class BikeParkController extends BaseController{

    public BikeParkController(){
    }

    public void editBikePark(List<BikePark> bikeParks, BikePark bikePark, String address, String name, int numOfEBikes, int numOfBikes, int numOfDocks){
        int index = bikeParks.indexOf(bikePark);
        bikeParks.get(index).setAddress(address);
        bikeParks.get(index).setName(name);
        bikeParks.get(index).setNumOfEBikes(numOfEBikes);
        bikeParks.get(index).setNumOfBikes(numOfBikes);
        bikeParks.get(index).setNumOfEmptyDocks(numOfDocks);
        new BikeParkRepository().save(bikeParks);
    }

    public void addBikePark(List<BikePark> bikeParks,String code, String address, String name, int numOfEBikes, int numOfBikes, int numOfDocks){
        BikePark bikePark = new BikePark();
        bikePark.setCode(code);
        bikePark.setAddress(address);
        bikePark.setName(name);
        bikePark.setNumOfEBikes(numOfEBikes);
        bikePark.setNumOfBikes(numOfBikes);
        bikePark.setNumOfEmptyDocks(numOfDocks);
        bikeParks.add(bikePark);
        new BikeParkRepository().save(bikeParks);
    }

    public boolean validateNewCode(List<BikePark> bikeParks,String newCode){
        BikePark bk = bikeParks.stream().filter(t -> t.getCode().equals(newCode)).findFirst().orElse(null);
        if (bk != null){
            return false;
        }
        return true;
    }

    public boolean deleteBikePark(List<BikePark> bikeParks,BikePark bikePark){
        return bikeParks.remove(bikePark);
    }
}
