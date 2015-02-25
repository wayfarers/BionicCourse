package depositscopy;

import java.time.LocalDate;

public class MonthCapitalizeDeposit implements Deposit {
	protected int daysLeft;
	protected double interestRate;
	protected double sum;
	protected int dayLong;
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
			
			interest += getPeriodInterest((sum + interest), period, tempDate.isLeapYear(), interestRate);
			
			tempDate = tempDate.plusDays(tillMonthEnd);
			daysLeft -= period;
		}
		return Math.round(interest * 100.0) / 100.0;
	}
}
