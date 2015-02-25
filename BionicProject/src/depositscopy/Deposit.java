package depositscopy;

public interface Deposit {
	 default double getPeriodInterest(double sum, int period, boolean isLeap, double interestRate) {
		if (isLeap) {
			return sum * (interestRate / 100.0) * (period / 366.0);
		} else {
			return sum * (interestRate / 100.0) * (period / 365.0);
		}
	}
	double getInterest();
}
