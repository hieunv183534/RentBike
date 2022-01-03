package entities;

public class Invoice {
	private Bike currentBike;
	private long rentTime = 0;
	private long rentCost = 0;
	private long deposit = 0;
	
	public Invoice() {
		
	}
	
	
	public Bike getCurrentBike() {
		return this.currentBike;
	}
	public void setCurrentBike(Bike currentBike) {
		this.currentBike = currentBike;
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
	
}
