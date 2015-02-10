package tests;

import static org.junit.Assert.*;
import hometasks.ExpRow;
import org.junit.Test;

public class ExpRowTest {
	@Test
	public void test10() {
		test(10);
	}
	
	@Test
	public void test100() {
		test(100);
	}
	
	@Test
	public void test_m10() {
		System.out.println(ExpRow.expSum(-10));
		test(-10);
		
	}


	private void test(double x) {
		assertEquals(1.0, Math.exp(x) / ExpRow.expSum(x), 1e-5);
	}

}
