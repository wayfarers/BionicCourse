package lesson07;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BarrierDeposit {
	protected double interestRate;
	protected double sum;
	protected int dayLong;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	
	
	public BarrierDeposit(double interestRate, double sum, LocalDate startDate, int dayLong) {
		this.interestRate = interestRate;
		this.sum = sum;
		this.startDate = startDate;
		this.dayLong = dayLong;
		endDate = startDate.plusDays(dayLong);
		if (sum > 100000.0) {
			this.interestRate += 2;
		} else if (sum > 50000.0) {
			this.interestRate++;
		}
	}
	
	public double getInterest() {
		double interest = 0;
		if (startDate.isLeapYear() && endDate.isLeapYear()) {
			interest = sum * (interestRate / 100.0) * (dayLong / 366.0);
		} else if(startDate.isLeapYear() && !endDate.isLeapYear()) {
			long leapDays = startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = sum * (interestRate / 100.0) * (leapDays / 366.0);
			interest += sum * (interestRate / 100.0) * ((dayLong - leapDays) / 365.0);
		} else if (!startDate.isLeapYear() && endDate.isLeapYear()) {
			long noLeapDays = startDate.until(LocalDate.of(startDate.getYear() + 1, 1, 1), ChronoUnit.DAYS);
			interest = sum * (interestRate / 100.0) * (noLeapDays / 365.0);
			interest += sum * (interestRate / 100.0) * ((dayLong - noLeapDays) / 365.0);
		} else if(!startDate.isLeapYear() && !endDate.isLeapYear()) {
			interest = sum * (interestRate / 100.0) * (dayLong / 365.0);
		}
		return Math.round(interest * 100.0) / 100.0;
	}
	
}
