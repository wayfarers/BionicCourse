package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import lesson07.SimpleDeposit;

import org.junit.Test;

public class SimpleDepositTest {

	@Test
	public void test() {
		SimpleDeposit dep = new SimpleDeposit(15, 1000, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, dep.getInterest(), 1e-2);
	}
	
	@Test
	public void test2() {
		SimpleDeposit dep = new SimpleDeposit(15, 1000, LocalDate.of(2012, 9, 8), 180);
		assertEquals(73.84, dep.getInterest(), 1e-2);
	}
	
	@Test
	public void test3() {
		SimpleDeposit dep = new SimpleDeposit(15, 1000, LocalDate.of(2014, 9, 8), 20);
		assertEquals(8.22, dep.getInterest(), 1e-2);
	}
	
	@Test
	public void test4() {
		SimpleDeposit dep = new SimpleDeposit(15, 1000, LocalDate.of(2014, 9, 8), 180);
		assertEquals(73.97, dep.getInterest(), 1e-2);
	}

}
