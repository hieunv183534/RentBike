package controllers;

import entities.Bike;
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
public class RentBikeController  extends BaseController{

    private List<Bike> bikes;
    private Bike myBike;
    private int DepositAmount;
    private String DepositTransactionContent;
    private RenterHomeController homeController;

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    private StringProperty totalTime;
    private StringProperty totalRent;

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public StringProperty totalRentProperty() {
        return totalRent;
    }

    public Bike getMyBike(){return this.myBike;}

    public int getDepositAmount() {
        return DepositAmount;
    }

    public String getDepositTransactionContent() {
        return DepositTransactionContent;
    }


    public RentBikeController(RenterHomeController renterHomeController) {
        this.homeController = renterHomeController;
        //   kscq2_group1_2021
        this.totalTime = new SimpleStringProperty();
        this.totalRent = new SimpleStringProperty();
    }

    /**
     * hàm kiểm tra mã code xem có lấy đc xe phù hợp không
     * @param bikeCode
     * @return
     * @author HieuNV
     */
    public int checkBikeCode(String bikeCode){
        for(int i=0; i< this.bikes.size(); i++){
            Bike bike = this.bikes.get(i);
            if(bike.getBikeCode().equals(bikeCode)){
                if(bike.getStatus() == 0){
                    this.myBike = bike;
                    this.DepositTransactionContent = "rentbike " + this.myBike.getBikeCode()+ " " + new Date().getTime();
                    switch(this.myBike.getType()){
                        case 1:
                            this.DepositAmount = 400000;
                            break;
                        case 2:
                            this.DepositAmount = 700000;
                            break;
                    }
                    return 1;
                }else{
                    return 2;
                }
            }
        }
        return 0;
    }

    public String getBikeRentalInfo(){
        if(this.myBike.getType()==1) {
            return "Tiền thuê xe: nếu khách hàng dùng xe hơn 10 phút," +
                    " phí thuê xe được tính lũy theo thời gian thuê như sau:" +
                    " Giá khởi điểm cho 30 phút đầu là 10.000 đồng. " +
                    "Cứ mỗi 15 phút tiếp theo, khách sẽ phải trả thêm 3.000 đồng. " +
                    "Ví dụ, khách thuê 1 tiếng 10 phút cần trả 10.000+ 3x3.000 = 19.000 đồng";
        }else{
                return "Tiền thuê xe: nếu khách hàng dùng xe hơn 10 phút," +
                        " phí thuê xe được tính lũy tiếntheo thời gian thuê như sau:" +
                        " Giá khởi điểm cho 30 phút đầu là 15.000 đồng." +
                        " Cứ mỗi 15 phút tiếp theo, khách sẽ phải trả thêm 4.500 đồng. " +
                        "Ví dụ, khách thuê 1 tiếng 10 phút cần trả 15.000+ 3x4.500 = 28.500 đồng";
        }
    }

    /**
     * hàm sự kiện bắt đầu thuê 1 chiếc xe
     * @author HieuNV
     */
    public void rent(){
        this.myBike.setStatus(1);
        this.myBike.setStartTime(new Date());
        if(this.myBike.getType()==1){
            this.totalRent.setValue("10000 đồng");
            this.totalTime.setValue("0 phút");
        }else{
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
                    totalTime.setValue(timeElapsed.getSeconds()+ " phút");
                    totalRent.setValue(calculateMoney((int) timeElapsed.getSeconds())+ " đồng");
                }
            });
            }
        }, 0, 1000);
    }

    /**
     * hàm tính số tiền thuê từ số phút đã thuê
     * @param numOfMinutes
     * @return
     * @author HieuNV
     */
    public long calculateMoney(int numOfMinutes){
        int m,n;
        if(this.myBike.getType()==1){
            m = 10000;
            n = 3000;
        }else{
            m = 15000;
            n = 4500;
        }
        if(numOfMinutes <= 30){
            return m;
        }else{
            return ((numOfMinutes-30)/15)*n + m + n ;
        }
    }

}
