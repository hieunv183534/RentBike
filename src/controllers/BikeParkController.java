package controllers;

import entities.BikePark;
import entities.repository.BikeParkRepository;

import java.util.ArrayList;
import java.util.List;

public class BikeParkController extends BaseController{

    public BikeParkController(){
    }

    public void editBikePark(List<BikePark> bikeParks, BikePark bikePark, String address, String name, String numOfEBikes, String numOfBikes,
                             String numOfDocks){
        int index = bikeParks.indexOf(bikePark);
        bikeParks.get(index).setAddress(address);
        bikeParks.get(index).setName(name);
        bikeParks.get(index).setNumOfEBikes(Integer.parseInt(numOfEBikes));
        bikeParks.get(index).setNumOfBikes(Integer.parseInt(numOfBikes));
        bikeParks.get(index).setNumOfEmptyDocks(Integer.parseInt(numOfDocks));
        new BikeParkRepository().save(bikeParks);
    }

    public void addBikePark(List<BikePark> bikeParks,String code, String address, String name, String numOfEBikes,
                            String numOfBikes, String numOfDocks){
        if (!validateInfoBikePark(numOfEBikes,numOfBikes,numOfDocks))
            return;
        if (bikeParks == null) bikeParks = new ArrayList<>();
        BikePark bikePark = new BikePark();
        bikePark.setCode(code);
        bikePark.setAddress(address);
        bikePark.setName(name);
        bikePark.setNumOfEBikes(Integer.parseInt(numOfEBikes));
        bikePark.setNumOfBikes(Integer.parseInt(numOfBikes));
        bikePark.setNumOfEmptyDocks(Integer.parseInt(numOfDocks));
        bikeParks.add(bikePark);
        new BikeParkRepository().save(bikeParks);
    }

    public boolean validateNewCode(List<BikePark> bikeParks,String newCode){
        if (bikeParks == null) return true;
        BikePark bk = bikeParks.stream().filter(t -> t.getCode().equals(newCode)).findFirst().orElse(null);
        if (bk != null){
            return false;
        }
        return true;
    }

    public boolean validateInfoBikePark(String ebikes, String bikes, String emptyDocks){
        try {
            Integer.parseInt(ebikes);
            Integer.parseInt(bikes);
            Integer.parseInt(emptyDocks);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteBikePark(List<BikePark> bikeParks,BikePark bikePark){
        if (bikeParks.remove(bikePark)) {
            new BikeParkRepository().save(bikeParks);
            return true;
        } else return false;
    }
}
