package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entities.Bike;
import entities.BikePark;

public class ReturnBikeController  extends BaseController {
	
	private List<BikePark> listBikeParks;
	private List<Bike> listBikes;
	
	public ReturnBikeController() {
		this.listBikes = (new Bike()).getAllBikes();
		this.listBikeParks = (new BikePark()).getAllBikeParks();
	}
	
	public boolean checkBikeCode(String bikeCode) {
		for(Bike bike : this.listBikes) {
			if (bike.getBikeCode().equals(bikeCode)) {
			   if (bike.getStatus() == 1) {
				   return true;
			   }
			   return false;
			}
		}
		return false;
	}
	
	public boolean checkBikePark (String parkName) {
		for(BikePark bikePark : this.listBikeParks) {
			if (bikePark.getName().toString() == parkName) {
				if (bikePark.getNumOfEmptyDocks() > 0) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	public ArrayList<String> getListNameBikeParks () {
		ArrayList<String> listNameBikeParks = new ArrayList<String>();
		for(BikePark i : this.listBikeParks) {
			listNameBikeParks.add(i.getName());
		}
		//System.out.print(listNameBikeParks);
		return listNameBikeParks;
	}
}
