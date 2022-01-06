package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import entities.Bike;
import entities.BikePark;
import entities.Invoice;
import entities.data.BikeRepository;
import entities.data.BikeParkRepository;
import exception.InvalidCalculateInputException;

public class ReturnBikeController  extends BaseController {
	
	private List<BikePark> listBikeParks;
	private List<Bike> listBikes;
	private BikeRepository bikeDAO = new BikeRepository();
	private BikeParkRepository bikeParkDAO = new BikeParkRepository();
	private Invoice invoice = new Invoice();
	
	public Invoice getInvoice() {
		return this.invoice;
	}
	
	public ReturnBikeController() {
		this.listBikes = this.bikeDAO.getAll();
		this.listBikeParks = this.bikeParkDAO.getAll();
	}
	
	public boolean checkBikeCodeAvailable(String bikeCode) {
		for(Bike bike : this.listBikes) {
			if (bike.getBikeCode().equals(bikeCode)) {
			   if (bike.getStatus() == 1) {
				   this.getInvoice().setCurrentBike(bike);
				   this.getInvoice().setTypeOfRent(bike.getRentType());
				   	Date date1 = bike.getStartTime();
					this.getInvoice().calculateRentTime(date1);
					this.getInvoice().setDepositOfInvoice(bike.getType());
				   try {
					   this.getInvoice().calculateCost(this.getInvoice().getRentTime());
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
					this.getInvoice().setBikePark(bikePark.getName());
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
	
	public void saveDataState() {
		String currentBikeCode = this.getInvoice().getCurrentBike().getBikeCode();
		for(int i = 0; i < this.listBikes.size(); i++) {
			if (this.listBikes.get(i).getBikeCode().equals(currentBikeCode)) {
				this.listBikes.get(i).setStatus(0);
				break;
			}
		}
		this.bikeDAO.save(this.listBikes);
		String currentBikePark = this.getInvoice().getBikePark();
		for(int i = 0; i < this.listBikeParks.size(); i++) {
			if (this.listBikeParks.get(i).getName().equals(currentBikePark)) {
				int numberOfEmptyDocks = this.listBikeParks.get(i).getNumOfEmptyDocks();
				this.listBikeParks.get(i).setNumOfEmptyDocks(numberOfEmptyDocks - 1);
				break;
			}
		}
		this.bikeParkDAO.save(this.listBikeParks);
	}

}
