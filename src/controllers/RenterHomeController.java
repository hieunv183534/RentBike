package controllers;

import entities.Account;
import entities.Bike;
import entities.BikePark;

import java.util.ArrayList;
import java.util.List;

public class RenterHomeController extends BaseController{
    private List<Bike> bikes;
    private Bike thisBike;
    private List<BikePark> bikeParts;

    public RenterHomeController() {
        bikes = new ArrayList<>();


        // init data
        List<Bike> bikeList1 = new ArrayList<>();
        bikeList1.add(new Bike("Xe đạp thường","XDT-A1", 1, 0));
        bikeList1.add(new Bike("Xe đạp thường","XDT-c1", 1, 1));
        bikeList1.add(new Bike("Xe đạp Điện","XDD-N2", 2, 1));
        bikeList1.add(new Bike("Xe đạp Điện","XDD-T6", 2, 0));

        List<Bike> bikeList2 = new ArrayList<>();
        bikeList2.add(new Bike("Xe đạp điện","XDD-V4", 2, 0));
        bikeList2.add(new Bike("Xe đạp điện","XDD-X3", 2, 1));
        bikeList2.add(new Bike("Xe đạp thường","XDT-M8", 1, 0));
        bikeList2.add(new Bike("Xe đạp thường","XDT-B6", 1, 1));

        bikes.addAll(bikeList1);
        bikes.addAll(bikeList2);

        bikeParts = new ArrayList<>();
        BikePark bikePart1 = new BikePark("Bãi xe 1", "Hà Nội");
        bikePart1.setBikes(bikeList1);

        bikeParts = new ArrayList<>();
        BikePark bikePart2 = new BikePark("Bãi xe 2", "TP HCM");
        bikePart2.setBikes(bikeList2);

    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public Bike getThisBike() {
        return thisBike;
    }

    public void setThisBike(Bike thisBike) {
        this.thisBike = thisBike;
    }

    public List<BikePark> getBikeParts() {
        return bikeParts;
    }

    public void setBikeParts(List<BikePark> bikeParts) {
        this.bikeParts = bikeParts;
    }
}
