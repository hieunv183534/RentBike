package controllers.calculate;

import exception.InvalidCalculateInputException;

/**
 * @author HieuNV
 */
public class CalculateMoney1 implements CalculateMoneyInterface{
    private long baseCost;
    private long baseTime;
    private long progressiveCost;
    private long progressiveTime;

    public CalculateMoney1(long baseCost, long baseTime, long progressiveCost, long progressiveTime) throws InvalidCalculateInputException {
        if( (baseCost<=0)||(baseTime<=0)||(progressiveCost<=0)||(progressiveTime<=0) ){
            throw new InvalidCalculateInputException();
        }else {
            this.baseCost = baseCost;
            this.baseTime = baseTime;
            this.progressiveCost = progressiveCost;
            this.progressiveTime = progressiveTime;
        }
    }

    /**
     * Tính tiền thuê từ thời gian
     * @param time thời gian đã thuê
     * @return
     * @throws InvalidCalculateInputException
     */
    @Override
    public long calculateMoney(long time) throws InvalidCalculateInputException {
        if(time <=0){
            throw new InvalidCalculateInputException();
        }else{
            if(time <= baseTime){
                return baseCost;
            }else{
                return baseCost + ((time - baseTime)/progressiveTime)*progressiveCost + progressiveCost;
            }
        }
    }
}
