package entities;

public class CreditCard {
	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	@Override
	public String toString() {
		return "CreditCard{" +
				"cardCode='" + cardCode + '\'' +
				", owner='" + owner + '\'' +
				", cvvCode=" + cvvCode +
				", dateExpired='" + dateExpired + '\'' +
				'}';
	}

	public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
}
