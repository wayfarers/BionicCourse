package deposits;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

abstract public class DepoBase implements Comparable<DepoBase>, Serializable{
	protected double interestRate;
	protected double sum;
	protected int dayLong;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	public DepoBase() {
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
	
	public double getSum() {
		return sum;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	abstract public double getInterest();

	@Override
	public int compareTo(DepoBase o) {
		if ((this.getInterest() - o.getInterest()) > 0) {
			return 1;
		} else if ((this.getInterest() - o.getInterest()) < 0) {
			return -1;
		}
		return 0;
	}
	
	public static class DepoComparator implements Comparator<DepoBase>{
		@Override
		public int compare(DepoBase o1, DepoBase o2) {
			if ((o1.getSum() - o2.getSum()) > 0) {
				return 1;
			} else if ((o1.getSum() - o2.getSum()) < 0) {
				return -1;
			}
			return 0;
		}
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public int getDaysLong() {
		return dayLong;
	}
}
