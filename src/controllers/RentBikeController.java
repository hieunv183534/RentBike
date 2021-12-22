package controllers;

import entities.Bike;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    private IntegerProperty totalTime;
    private IntegerProperty totalRent;

    public IntegerProperty totalTimeProperty() {
        return totalTime;
    }

    public IntegerProperty totalRentProperty() {
        return totalRent;
    }

    public Bike getMyBike(){return this.myBike;}

    public int getDepositAmount() {
        return DepositAmount;
    }

    public String getDepositTransactionContent() {
        return DepositTransactionContent;
    }


    public RentBikeController() {
        bikes = new ArrayList<>();
        bikes.add(new Bike("Xe đạp thường","1de5b34e-b240-41c1-b8a0-991c9b98849c", 1, 0));
        bikes.add(new Bike("Xe đạp thường","b1201a9b-e23b-44c8-88b2-a00a5145461f", 1, 1));
        bikes.add(new Bike("Xe đạp thường","40edf4cd-4225-4cad-908f-3c48b110d467", 1, 1));
        bikes.add(new Bike("Xe đạp điện","d2eb2832-bb85-4d37-88b1-48898242fb6a", 2, 0));
        bikes.add(new Bike("Xe đạp điện","44c3e3f4-227a-45c3-a940-9065b184dfa5", 2, 1));
        bikes.add(new Bike("Xe đạp điện","17ce92c0-d60f-42e3-ad0e-4922ac24efa7", 2, 0));

        this.totalTime = new SimpleIntegerProperty();
        this.totalRent = new SimpleIntegerProperty();
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
                    this.DepositTransactionContent = "rentbike " + this.myBike.getBikeCode();
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
            this.totalRent.setValue(10000);
            this.totalTime.setValue(0);
        }else{
            this.totalRent.setValue(15000);
            this.totalTime.setValue(0);
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
                        totalTime.setValue(timeElapsed.toMinutes());
                        totalRent.setValue(calculateMoney((int) timeElapsed.toMinutes()));
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
