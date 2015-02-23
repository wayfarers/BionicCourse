package lesson07;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import lesson08.DepoBase;

public class SimpleDeposit extends DepoBase{
	
	
	public SimpleDeposit(double interestRate, double sum, LocalDate startDate, int dayLong) {
		super(interestRate, sum, startDate, dayLong);
	}
	
	public double getInterest() {
		double interest = 0;
		if (startDate.isLeapYear() && endDate.isLeapYear()) {
			interest = getPeriodInterest(sum, dayLong, true);
		} else if(startDate.isLeapYear() && !endDate.isLeapYear()) {
			int leapDays = (int) startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = getPeriodInterest(sum, leapDays, true);
			interest += getPeriodInterest(sum, dayLong - leapDays, false);
		} else if (!startDate.isLeapYear() && endDate.isLeapYear()) {
			int noLeapDays = (int) startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = getPeriodInterest(sum, noLeapDays, false);
			interest += getPeriodInterest(sum, dayLong - noLeapDays, false);
		} else if(!startDate.isLeapYear() && !endDate.isLeapYear()) {
			interest = getPeriodInterest(sum, dayLong, false);
		}
		return Math.round(interest * 100.0) / 100.0;
	}
	
}
