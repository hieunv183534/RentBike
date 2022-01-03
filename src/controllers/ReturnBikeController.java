package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entities.Bike;
import entities.BikePark;
import utils.Utils;

public class ReturnBikeController  extends BaseController {
	
	private List<BikePark> listBikeParks;
	private List<Bike> listBikes;
	private Bike currentBike;
	private long rentTime = 0;
	private long rentCost = 0;
	
	public Bike getCurrentBike() {
		return this.currentBike;
	}
	public void setCurrentBike(Bike bike) {
		this.currentBike = bike;
	}
	
	public long getRentTime() {
		return this.rentTime;
	}
	
	public void setRentTime(long rentTime) {
		this.rentTime = rentTime;
	}
	
	public long getRentCost() {
		return this.rentCost;
	}
	public void setRentCost(long rentCost) {
		this.rentCost = rentCost;
	}
	
	public ReturnBikeController() {
		this.listBikes = (new Bike()).getAllBikes();
		this.listBikeParks = (new BikePark()).getAllBikeParks();
	}
	
	public boolean checkBikeCode(String bikeCode) {
		for(Bike bike : this.listBikes) {
			if (bike.getBikeCode().equals(bikeCode)) {
			   if (bike.getStatus() == 1) {
				   this.setCurrentBike(bike);
				   	Date date1 = new Date("Dec 31, 2021, 19:00:00");
					Date date2 = new Date();
					this.setRentTime(Utils.getDateDiff(date1, date2, TimeUnit.MINUTES));
					this.calculatePostPaid(this.getRentTime(), 30000, 3000, 15);
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
		return listNameBikeParks;
	}
	/*
	 * Calculate post rent cost
	 */
	
	public void calculatePostPaid(long time, long baseCost, long progressiveCost, long progressiveTime) {
		long progressive = Math.floorDiv(time, progressiveTime);
		if (progressive <= 2) {
			this.setRentCost(baseCost);
		} else {
			long _rentCost = baseCost + (progressive - 2) * progressiveCost;
			this.setRentCost(_rentCost);
		}
	}
	/*
	 * Calculate prepay rent cost
	 */
	public void calculatePrePaid(long time) {
		if (time < 12 * 60) {
			long earlyPayHours = Math.floorDiv(time, 60);
			long earlyCost = 200000 - earlyPayHours*10000;
			this.setRentCost(earlyCost);
			
		} else if (12 * 60 <= time && time <= 24 * 60) {
			this.setRentCost(200000);
		} else if (time > 24 * 60) {
			long progressiveLatePayTime = Math.floorDiv(time - 24 * 60, 15);
			long lateCost = 200000 + progressiveLatePayTime * 2000;
			this.setRentCost(lateCost);
		}
		
	}
}
