package tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import lesson07.BarrierDeposit;

import org.junit.Test;

public class BarrierDepositTest {

	@Test
	public void test() {
		BarrierDeposit dep = new BarrierDeposit(15, 1000, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, dep.getInterest(), 0);
	}
	
	@Test
	public void test2() {
		BarrierDeposit dep = new BarrierDeposit(15, 60000, LocalDate.of(2012, 9, 8), 30);
		assertEquals(786.89, dep.getInterest(), 0);
	}
	
	@Test
	public void test3() {
		BarrierDeposit dep = new BarrierDeposit(15, 60000, LocalDate.of(2014, 9, 8), 30);
		assertEquals(789.04, dep.getInterest(), 0);
	}
	
	@Test
	public void test4() {
		BarrierDeposit dep = new BarrierDeposit(15, 100001, LocalDate.of(2014, 5, 12), 180);
		assertEquals(8383.65, dep.getInterest(), 0);
	}

}
