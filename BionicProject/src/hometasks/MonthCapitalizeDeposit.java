package hometasks;

import java.time.LocalDate;

public class MonthCapitalizeDeposit {
	protected double interestRate;
	protected double sum;
	protected int dayLong;
	protected int daysLeft;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	
	
	public MonthCapitalizeDeposit(double interestRate, double sum, LocalDate startDate, int dayLong) {
		this.interestRate = interestRate;
		this.sum = sum;
		this.startDate = startDate;
		this.dayLong = dayLong;
		endDate = startDate.plusDays(dayLong);
		daysLeft = dayLong;
	}
	
	public double getInterest() {
		double interest = 0;
		LocalDate tempDate = LocalDate.ofEpochDay(startDate.toEpochDay()).plusDays(1);
		while (tempDate.isBefore(endDate)) {
			int tillMonthEnd = tempDate.lengthOfMonth() - tempDate.getDayOfMonth() + 1;
			int period = daysLeft > tillMonthEnd ? tillMonthEnd : daysLeft;
			
			interest += getMonthInterest((sum + interest), period, tempDate.isLeapYear());
			
			tempDate = tempDate.plusDays(tillMonthEnd);
			daysLeft -= period;
		}
		return Math.round(interest * 100.0) / 100.0;
	}
	
	private double getMonthInterest(double sum, int period, boolean isLeap) {
		if (isLeap) {
			return sum * (interestRate / 100.0) * (period / 366.0);
		} else {
			return sum * (interestRate / 100.0) * (period / 365.0);
		}
	}
}
