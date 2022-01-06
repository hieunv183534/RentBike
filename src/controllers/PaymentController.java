package controllers;

import entities.CreditCard;
import entities.PaymentTransaction;
import exception.InvalidCardException;
import exception.PaymentException;
import exception.UnrecognizedException;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class PaymentController extends BaseController{
    /**
     * Represent the card used for payment
     */
    private CreditCard card;

    /**
     * Represent the Interbank subsystem
     */
    private InterbankInterface interbank;

    private PaymentTransaction successPayment;

    public PaymentTransaction getSuccessPayment() {
        return successPayment;
    }
    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link java.lang.String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link java.lang.String String} represents the input date
     * @return {@link java.lang.String String} - date representation of the required
     *         format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */
    private String getExpirationDate(String date) throws InvalidCardException {
        String[] strs = date.split("/");
        if (strs.length != 2) {
            throw new InvalidCardException();
        }

        String expirationDate = null;
        int month = -1;
        int year = -1;

        try {
            month = Integer.parseInt(strs[0]);
            year = Integer.parseInt(strs[1]);
            if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
                throw new InvalidCardException();
            }
            expirationDate = strs[0] + strs[1];

        } catch (Exception ex) {
            throw new InvalidCardException();
        }

        return expirationDate;
    }

    /**
     * Pay order, and then return the result with a message.
     *
     * @param amount         - the amount to pay
     * @param contents       - the transaction contents
     * @param cardNumber     - the card number
     * @param cardHolderName - the card holder name
     * @param expirationDate - the expiration date in the format "mm/yy"
     * @param securityCode   - the cvv/cvc code of the credit card
     * @return {@link java.util.Map Map} represent the payment result with a
     *         message.
     */
    public Map<String, String> pay(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode) {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
                    getExpirationDate(expirationDate));

            this.interbank = new InterbankSubsystem();
             this.successPayment = interbank.pay(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;
    }
    public Map<String, String> refund(long amount, String contents, String cardNumber, String cardHolderName,
            							String expirationDate, String securityCode) {
    	String strResult = "{" +
                " cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", content='" + contents + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    	System.out.print(strResult);
    	Map<String, String> result = new Hashtable<String, String>();
    	result.put("RESULT", "PAYMENT SUCCESS");
    	return result;
    }
}
