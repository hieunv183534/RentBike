package subsystem.interbank;

import java.util.Map;


import entities.CreditCard;
import entities.PaymentTransaction;
import exception.*;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BUXj/7/gHHI=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.0";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}


	public PaymentTransaction pay(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}
	
	public PaymentTransaction refund(CreditCard card, long amount, String contents) {
		return null;
	}

	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		String errorCode = (String)response.get("errorCode");

		switch (errorCode) {
			case "00":
				MyMap transcation = (MyMap) response.get("transaction");
				CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
						Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
				PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
						(String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
						Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));
				return trans;
			case "01":
				throw new InvalidCardException();
			case "02":
				throw new NotEnoughBalanceException();
			case "03":
				throw new InternalServerErrorException();
			case "04":
				throw new SuspiciousTransactionException();
			case "05":
				throw new NotEnoughTransactionInfoException();
			case "06":
				throw new InvalidVersionException();
			case "07":
				throw new InvalidTransactionAmountException();
			default:
				throw new UnrecognizedException();
		}

	}

}
