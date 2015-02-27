package tests;

import static org.junit.Assert.*;
import lesson10.DepoList;

import org.junit.Test;

public class DepoListTest {

	@Test
	public void test() {
		DepoList deps = new DepoList();
		assertEquals(73000, deps.getPrincipal(), 0);
	}
	
	@Test
	public void test2() {
		DepoList deps = new DepoList();
		deps.remove();
		assertEquals(65000, deps.getPrincipal(), 0);
	}

}
