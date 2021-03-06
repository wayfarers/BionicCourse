package deposits;

import java.time.LocalDate;

public class MonthCapitalizeDeposit extends DepoBase implements Deposit {
	
	public MonthCapitalizeDeposit(double interestRate, double sum, LocalDate startDate, int dayLong) {
		super(interestRate, sum, startDate, dayLong);
	}
	
	public double getInterest() {
		double interest = 0;
		int daysLeft = dayLong;
		LocalDate tempDate = LocalDate.ofEpochDay(startDate.toEpochDay()).plusDays(1);
		while (tempDate.isBefore(endDate)) {
			int tillMonthEnd = tempDate.lengthOfMonth() - tempDate.getDayOfMonth() + 1;
			int period = daysLeft > tillMonthEnd ? tillMonthEnd : daysLeft;
			
			interest += getPeriodInterest((sum + interest), period, tempDate.isLeapYear());
			
			tempDate = tempDate.plusDays(tillMonthEnd);
			daysLeft -= period;
		}
		return Math.round(interest * 100.0) / 100.0;
	}
}
