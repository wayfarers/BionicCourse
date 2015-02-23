package lesson08;

import java.time.LocalDate;

public class DepoBase {
	protected double interestRate;
	protected double sum;
	protected int dayLong;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	public DepoBase() {
	}
	
	public double getInterest() {
		return 0;
	}
	
	public DepoBase(double interestRate, double sum, LocalDate startDate, int dayLong) {
		this.interestRate = interestRate;
		this.sum = sum;
		this.startDate = startDate;
		this.dayLong = dayLong;
		endDate = startDate.plusDays(dayLong);
	}
	
	protected double getPeriodInterest(double sum, int period, boolean isLeap) {
		if (isLeap) {
			return sum * (interestRate / 100.0) * (period / 366.0);
		} else {
			return sum * (interestRate / 100.0) * (period / 365.0);
		}
	}
}
