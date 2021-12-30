package controllers;

public class ReturnBikeController  extends BaseController {
	
	public ReturnBikeController() {
		
	}
	
	public boolean checkBikeCode(String bikeCode) {
		String bikeCodeExisted = "111";
		if (bikeCodeExisted.equals(bikeCode)) {
			return true;
		}
		return false;
	}
	
	public boolean checkBikePark (String parkName) {
		String bikeParkName = "P01";
		if (bikeParkName.equals(parkName)) {
			return true;
		} else {
			return false;
		}
	}
}
