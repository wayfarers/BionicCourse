package hometasks;

public class TBill {
	private double nominal;
	private double price;
	private int amount;
	
	public TBill() {
		this(0, 0, 0);
	}
	
	public TBill(double nominal, double price, int amount) {
		this.nominal = nominal;
		this.price = price;
		this.amount = amount;
	}
	
	public double getIncome() {
		return (nominal - price) * amount;
	}
}
