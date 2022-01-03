package controllers;

import entities.BikePark;
import entities.data.BikeParkDataController;

import java.util.List;

public class AdminHomeController extends BaseController{

    public List getAllBikeParks(){
        return new BikeParkDataController().getAll();
    }
}
