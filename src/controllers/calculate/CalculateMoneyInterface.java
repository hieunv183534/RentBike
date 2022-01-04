package controllers.calculate;

import exception.InvalidCalculateInputException;

public interface CalculateMoneyInterface {
    long calculateMoney(long time) throws InvalidCalculateInputException;
}
