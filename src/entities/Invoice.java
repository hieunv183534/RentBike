package entities;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import controllers.calculate.CalculateMoney1;
import exception.InvalidCalculateInputException;
import utils.Utils;

public class Invoice {
	private Bike currentBike;
	private int typeOfRent;
	private long rentTime;
	private long rentCost;
	private long deposit;
	private String bikePark;
	
	public Invoice(int typeOfRent) {
		this.typeOfRent = typeOfRent;
		this.rentCost = 0;
		this.rentTime = 0;
		this.deposit = 0;
	}
	public Invoice() {
		this(0);
	}
	
	public String getBikePark() {
		return this.bikePark;
	}
	
	public void setBikePark(String bikePark) {
		this.bikePark = bikePark;
	}
	
	public Bike getCurrentBike() {
		return this.currentBike;
	}
	public void setCurrentBike(Bike currentBike) {
		this.currentBike = currentBike;
	}
	
	public int getTypeOfRent() {
		return this.typeOfRent;
	}
	public void setTypeOfRent(int type) {
		this.typeOfRent = type;
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
	
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	public long getDeposit() {
		return this.deposit;
	}
	
	public void setDepositOfInvoice(int typeOfBike) {
		switch (typeOfBike) {
			case 1: {
				this.setDeposit(400000);
				break;
			}
			case 2: {
				this.setDeposit(700000);
				break;
			}
			case 3: {
				this.setDeposit(550000);
				break;
			}
			default: 
				break;
		}
	}
	
	public void calculateRentTime(Date date) {
		Date current = new Date();
		long time = Utils.getDateDiff(date, current, TimeUnit.MINUTES);
		this.setRentTime(time);
	}
	
	/*
	 * Calculate post rent cost
	 */
	
	public void calculatePostPaid(long time) throws InvalidCalculateInputException{
		long rentCost = 0;
		if (this.getCurrentBike().getType() == 1) {
			rentCost = new CalculateMoney1(10000,30,3000,15).calculateMoney(time);
			this.setRentCost(rentCost);
		} else {
			rentCost = new CalculateMoney1(15000,30,4500,15).calculateMoney(time);
			this.setRentCost(rentCost);
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
	/*
	 * Calculate money with type of rent
	 * 
	 */
	
	public void calculateCost(long time) throws InvalidCalculateInputException {
		if (this.getTypeOfRent() == 0) {
			this.calculatePostPaid(time);
		} else {
			this.calculatePrePaid(time);
		}
	}
	
}
