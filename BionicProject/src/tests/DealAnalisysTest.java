package tests;

import static org.junit.Assert.assertEquals;
import hometasks.DealAnalisys;
import hometasks.TBill;

import java.time.LocalDate;

import org.junit.Test;

import deposits.BarrierDeposit;
import deposits.Deposit;
import deposits.MonthCapitalizeDeposit;
import deposits.SimpleDeposit;

public class DealAnalisysTest {

	@Test
	public void testSimple() {
		DealAnalisys<TBill> analisys = new DealAnalisys<>(new TBill(50, 30, 5));
		Deposit dep = new SimpleDeposit(15.3, 5500, LocalDate.of(2013, 11, 12), 30);
		assertEquals(30.84, analisys.compareIncome(dep), 0);
		
	}
	
	@Test
	public void testMonth() {
		DealAnalisys<TBill> analisys = new DealAnalisys<>(new TBill(50, 30, 5));
		Deposit dep = new MonthCapitalizeDeposit(21, 10000, LocalDate.of(2012, 2, 1), 181);
		assertEquals(-984.5, analisys.compareIncome(dep), 0);
		
	}
	
	@Test
	public void testBarrier() {
		DealAnalisys<TBill> analisys = new DealAnalisys<>(new TBill(50, 30, 5));
		Deposit dep = new BarrierDeposit(19.56, 43000, LocalDate.of(2011, 12, 18), 370);
		assertEquals(-8426.02, analisys.compareIncome(dep), 0);
		
	}

}
