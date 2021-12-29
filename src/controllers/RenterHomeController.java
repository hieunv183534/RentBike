package controllers;

import entities.Bike;
import entities.BikePark;

import java.util.ArrayList;
import java.util.List;

public class RenterHomeController extends BaseController{
    private Bike myBike;
    public RenterHomeController() {

    }

    public Bike getMyBike() {
        return myBike;
    }

    public void setMyBike(Bike myBike) {
        this.myBike = myBike;
    }
}
