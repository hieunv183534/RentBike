package controllers;

import entities.BikePark;

import java.util.List;

public class AdminHomeController extends BaseController{

    public List getAllBikeParks(){
        return new BikePark().getAllBikeParks();
    }
}
