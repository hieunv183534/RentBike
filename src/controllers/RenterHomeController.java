package controllers;

import entities.Account;
import entities.Bike;

import java.util.List;

public class RenterHomeController extends BaseController{
    private List<Bike> bikes;

    public RenterHomeController() {
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }
}
