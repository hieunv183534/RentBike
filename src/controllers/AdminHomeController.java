package controllers;

import entities.repository.BikeParkRepository;

import java.util.List;

public class AdminHomeController extends BaseController{

    public List getAllBikeParks(){
        return new BikeParkRepository().getAll();
    }
}
