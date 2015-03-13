package database;

public class Merchant {
	private String name;
	private double charge;
	private int period;
	private double minSum;
	private String bankName;
	private String swift;
	private String account;
	
	
	
	/**
	 * @param name
	 * @param charge
	 * @param period
	 * @param minSum
	 * @param bankName
	 * @param swift
	 * @param account
	 */
	public Merchant(String name, double charge, int period, double minSum,
			String bankName, String swift, String account) {
		this.name = name;
		this.charge = charge;
		this.period = period;
		this.minSum = minSum;
		this.bankName = bankName;
		this.swift = swift;
		this.account = account;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %1s, charge: %2f, period: %3d, minsum: %4f, bankName: %5s, swift: %6s, account: %7s", 
				name, charge, period, minSum, bankName, swift, account);
	};
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public double getMinSum() {
		return minSum;
	}
	public void setMinSum(double minSum) {
		this.minSum = minSum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
