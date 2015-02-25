package depositscopy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SimpleDeposit implements Deposit {
	protected double interestRate;
	protected double sum;
	protected int dayLong;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	public SimpleDeposit(double interestRate, double sum, LocalDate startDate, int dayLong) {
		this.interestRate = interestRate;
		this.sum = sum;
		this.startDate = startDate;
		this.dayLong = dayLong;
		endDate = startDate.plusDays(dayLong);
	}
	
	public double getInterest() {
		double interest = 0;
		if (startDate.isLeapYear() && endDate.isLeapYear()) {
			interest = getPeriodInterest(sum, dayLong, true, interestRate);
		} else if(startDate.isLeapYear() && !endDate.isLeapYear()) {
			int leapDays = (int) startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = getPeriodInterest(sum, leapDays, true, interestRate);
			interest += getPeriodInterest(sum, dayLong - leapDays, false, interestRate);
		} else if (!startDate.isLeapYear() && endDate.isLeapYear()) {
			int noLeapDays = (int) startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = getPeriodInterest(sum, noLeapDays, false, interestRate);
			interest += getPeriodInterest(sum, dayLong - noLeapDays, false, interestRate);
		} else if(!startDate.isLeapYear() && !endDate.isLeapYear()) {
			interest = getPeriodInterest(sum, dayLong, false, interestRate);
		}
		return Math.round(interest * 100.0) / 100.0;
	}
	
}
