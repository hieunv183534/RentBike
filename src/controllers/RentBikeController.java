package controllers;

import controllers.calculate.CalculateMoney1;
import entities.Bike;
import entities.BikePark;
import entities.data.BikeDataController;
import entities.data.BikeParkDataController;
import exception.InvalidCalculateInputException;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * @author HieuNV
 */
public class RentBikeController extends BaseController {

    private Bike myBike;
    private BikePark park;
    private int DepositAmount;
    private String DepositTransactionContent;
    private RenterHomeController homeController;

    private StringProperty totalTime;
    private StringProperty totalRent;

    private List<BikePark> bikeParks;
    private List<Bike> bikes;	

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public StringProperty totalRentProperty() {
        return totalRent;
    }

    public Bike getMyBike() {
        return this.myBike;
    }

    public int getDepositAmount() {
        return DepositAmount;
    }

    public String getDepositTransactionContent() {
        return DepositTransactionContent;
    }


    public RentBikeController(RenterHomeController renterHomeController) {
        bikeParks = new BikeParkDataController().getAll();
        bikes = new BikeDataController().getAll();
        this.homeController = renterHomeController;
        //   kscq2_group1_2021
        this.totalTime = new SimpleStringProperty();
        this.totalRent = new SimpleStringProperty();
    }

    /**
     * hàm kiểm tra mã code xem có lấy đc xe phù hợp không
     *
     * @param bikeCode
     * @return
     * @author HieuNV
     */
    public int checkBikeCode(String bikeCode) {
        for (int i = 0; i < this.bikes.size(); i++) {
            Bike bike = this.bikes.get(i);
            if(bike.getBikeCode().equals(bikeCode)){
                if(bike.getStatus()==0){
                    this.myBike = bike;
                    setMyData();
                    return 1;
                }else{
                    return 2;
                }
            }
        }
        return 0;
    }

    /**
     * set up các data cho controller để sử dụng;
     */
    public void setMyData(){
        for(BikePark bikePark: this.bikeParks) {
            if(bikePark.getCode().equals(this.myBike.getParkCode())){
                this.park = bikePark;
                break;
            }
        }
        this.DepositTransactionContent = "renbike "+ this.myBike.getBikeCode();
        if(this.myBike.getType()==1){
            this.DepositAmount = 400;
        }else{
            this.DepositAmount = 700;
        }
    }

    public String getBikeRentalInfo() {
        if (this.myBike.getType() == 1) {
            return "Tiền thuê xe: nếu khách hàng dùng xe hơn 10 phút," +
                    " phí thuê xe được tính lũy theo thời gian thuê như sau:" +
                    " Giá khởi điểm cho 30 phút đầu là 10.000 đồng. " +
                    "Cứ mỗi 15 phút tiếp theo, khách sẽ phải trả thêm 3.000 đồng. " +
                    "Ví dụ, khách thuê 1 tiếng 10 phút cần trả 10.000+ 3x3.000 = 19.000 đồng";
        } else {
            return "Tiền thuê xe: nếu khách hàng dùng xe hơn 10 phút," +
                    " phí thuê xe được tính lũy tiếntheo thời gian thuê như sau:" +
                    " Giá khởi điểm cho 30 phút đầu là 15.000 đồng." +
                    " Cứ mỗi 15 phút tiếp theo, khách sẽ phải trả thêm 4.500 đồng. " +
                    "Ví dụ, khách thuê 1 tiếng 10 phút cần trả 15.000+ 3x4.500 = 28.500 đồng";
        }
    }

    /**
     * hàm sự kiện bắt đầu thuê 1 chiếc xe
     *
     * @author HieuNV
     */
    public void rent() {
        this.myBike.rentBike();
        this.park.rentBike(this.myBike.getType());
        new BikeDataController().save(this.bikes);
        new BikeParkDataController().save(this.bikeParks);

        homeController.setMyBike(this.myBike);

        if (this.myBike.getType() == 1) {
            this.totalRent.setValue("10000 đồng");
            this.totalTime.setValue("0 phút");
        } else {
            this.totalRent.setValue("15000 đồng");
            this.totalTime.setValue("0 phút");
        }
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Instant end = Instant.now();
                        Instant start = Instant.ofEpochMilli(myBike.getStartTime().getTime());
                        Duration timeElapsed = Duration.between(start, end);
                        totalTime.setValue(timeElapsed.getSeconds() + " phút");
                        try {
                            totalRent.setValue(calculateMoney( timeElapsed.getSeconds()) + " đồng");
                        } catch (InvalidCalculateInputException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * hàm tính số tiền thuê từ số phút đã thuê
     *
     * @param time
     * @return
     * @author HieuNV
     */
    public long calculateMoney(long time) throws InvalidCalculateInputException {
        if (this.myBike.getType() == 1) {
            return new CalculateMoney1(10000,30,3000,15).calculateMoney(time);
        } else {
            return new CalculateMoney1(15000,30,4500,15).calculateMoney(time);
        }
    }

}
