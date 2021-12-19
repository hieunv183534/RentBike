package controllers;

public class RentBikeController  extends BaseController{
    public RentBikeController() {
    }

    public int checkBikeCode(String bikeCode){
        if(bikeCode.equals("12345"))
            return 1;
        else if(bikeCode.equals("54321"))
            return 2;
        return 0;
    }

}
