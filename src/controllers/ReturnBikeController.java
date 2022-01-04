package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import controllers.calculate.CalculateMoney1;
import entities.Bike;
import entities.BikePark;
import entities.Invoice;
import entities.data.BikeDataController;
import entities.data.BikeParkDataController;
import exception.InvalidCalculateInputException;
import utils.Utils;

public class ReturnBikeController  extends BaseController {
	
	private List<BikePark> listBikeParks;
	private List<Bike> listBikes;
	private Invoice invoice = new Invoice();
	
	public Invoice getInvoice() {
		return this.invoice;
	}
	
	public ReturnBikeController() {
		this.listBikes = new BikeDataController().getAll();
		this.listBikeParks = new BikeParkDataController().getAll();
	}
	
	public boolean checkBikeCodeAvailable(String bikeCode) {
		for(Bike bike : this.listBikes) {
			if (bike.getBikeCode().equals(bikeCode)) {
			   if (bike.getStatus() == 1) {
				   this.getInvoice().setCurrentBike(bike);
				   	Date date1 = new Date("Jan 4, 2022, 12:00:00");
					Date date2 = new Date();
					this.getInvoice().setRentTime(Utils.getDateDiff(date1, date2, TimeUnit.MINUTES));
					this.getInvoice().setDepositOfInvoice(bike.getType());
				   try {
					   this.getInvoice().calculateMoney(this.getInvoice().getRentTime());
				   } catch (InvalidCalculateInputException e) {
					   e.printStackTrace();
				   }
				   return true;
			   }
			   return false;
			}
		}
		return false;
	}
	
	
	public boolean checkBikeParkAvailable (String parkName) {
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

}
