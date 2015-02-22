package tests;

import static org.junit.Assert.assertEquals;
import hometasks.MonthCapitalizeDeposit;

import java.time.LocalDate;

import org.junit.Test;

public class MonthCapitalizeDepositTest {

	@Test
	public void test() {
		MonthCapitalizeDeposit dep = new MonthCapitalizeDeposit(15, 1000, LocalDate.of(2013, 9, 8), 20);
		assertEquals(8.22, dep.getInterest(), 0);
	}
	
	@Test
	public void test2() {
		MonthCapitalizeDeposit dep = new MonthCapitalizeDeposit(15, 1000, LocalDate.of(2013, 9, 8), 30);
		assertEquals(12.36, dep.getInterest(), 0);
	}
	
	@Test
	public void test3() {
		MonthCapitalizeDeposit dep = new MonthCapitalizeDeposit(15, 1000, LocalDate.of(2014, 5, 12), 180);
		assertEquals(76.32, dep.getInterest(), 0);
	}
}
