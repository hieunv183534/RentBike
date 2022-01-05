package subsystem;

import entities.CreditCard;
import entities.PaymentTransaction;
import exception.PaymentException;
import exception.UnrecognizedException;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link InterbankSubsystem InterbankSubsystem} to make transaction
 * 
 * @author hieud
 * 
 */
public interface InterbankInterface {

	/**
	 * Pay order, and then return the payment transaction
	 * 
	 * @param card     - the credit card used for payment
	 * @param amount   - the amount to pay
	 * @param contents - the transaction contents
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	public abstract PaymentTransaction pay(CreditCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException;
	
	public abstract boolean refund(CreditCard card, long amount, String contents) 
			throws PaymentException, UnrecognizedException;

}
