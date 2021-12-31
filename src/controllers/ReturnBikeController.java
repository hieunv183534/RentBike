package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public void calculateMoney () {
		Date date1 = new Date(2021, 12, 31, 9, 0, 0);
		Date date2 = new Date(2021, 12, 31, 10, 0, 0);
		long diff = getDateDiff(date1, date2, TimeUnit.MINUTES);
		System.out.print(diff);
	}
}
