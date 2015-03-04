package tests;

import static org.junit.Assert.*;
import hometasks.TBill;

import org.junit.Test;

public class TBillTest {

	@Test
	public void test() {
		assertEquals(100, new TBill(50, 30, 5).getIncome(), 0);
	}

}
