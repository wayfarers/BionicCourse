package lesson07;

import java.time.LocalDate;

import deposits.BarrierDeposit;
import deposits.Deposit;
import deposits.MonthCapitalizeDeposit;
import deposits.SimpleDeposit;

public class DifferentDeposits {
	public static void main(String[] args) {
		double sum = 0;
		Deposit [] deps = {
				new SimpleDeposit(15, 1000, LocalDate.of(2013, 9, 8), 20),
				new SimpleDeposit(18, 2500, LocalDate.of(2013, 9, 8), 20),
				new BarrierDeposit(11.5, 15000, LocalDate.of(2013, 9, 8), 40),
				new BarrierDeposit(14, 5000, LocalDate.of(2013, 9, 8), 80),
				new MonthCapitalizeDeposit(16.5, 2000, LocalDate.of(2013, 9, 8), 180),
				new MonthCapitalizeDeposit(12.1, 40000, LocalDate.of(2013, 9, 8), 91) };
		
		for (Deposit dep : deps) {
			sum += dep.getInterest();
		}
		System.out.println(sum);
	}
}
