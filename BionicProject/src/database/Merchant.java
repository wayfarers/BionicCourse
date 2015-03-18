package database;

import java.sql.Date;

public class Merchant {
	private int id;
	private String name;
	private double charge;
	private Period period;
	private double minSum;
	private String bankName;
	private String swift;
	private String account;
	private double needToSend;
	private double sent;
	private Date lastSent;
	
	
	public Merchant(int id, String name, double charge, int period, double minSum,
			String bankName, String swift, String account, double needToSend, double sent, Date dt) {
		this.id = id;
		this.name = name;
		this.charge = charge;
		setPeriod(period);
		this.minSum = minSum;
		this.bankName = bankName;
		this.swift = swift;
		this.account = account;
		this.needToSend = needToSend;
		this.sent = sent;
		this.lastSent = dt;
	}
	
	public enum Period {UNKNOWN, WEEKLY, BEWEEKLY, MONTHLY}
	
	@Override
	public String toString() {
		return String.format("Merchant's info:\nName: %1s\ncharge: %2f\nperiod: %3d\nminsum: %4f\nbankName: %5s\nswift: %6s\naccount: %7s\n", 
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

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		switch (period) {
		case 1:
			this.period = Period.WEEKLY;
			break;
		case 2:
			this.period = Period.BEWEEKLY;
			break;
		case 3:
			this.period = Period.MONTHLY;
			break;
		default:
			this.period = Period.UNKNOWN;
		}
	}
	
	public void setPeriod(Period period) {
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNeedToSend() {
		return needToSend;
	}

	public void setNeedToSend(double needToSend) {
		this.needToSend = needToSend;
	}

	public double getSent() {
		return sent;
	}

	public void setSent(double sent) {
		this.sent = sent;
	}

	public Date getLastSent() {
		return lastSent;
	}

	public void setLastSent(Date lastSent) {
		this.lastSent = lastSent;
	}
}
