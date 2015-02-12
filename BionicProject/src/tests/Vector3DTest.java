package tests;

import static org.junit.Assert.*;
import hometasks.Vector3D;

import org.junit.Test;

public class Vector3DTest {

	@Test
	public void testAdd() {
		assertEquals(new Vector3D(5, 7, 9), new Vector3D(1, 2, 3).add(new Vector3D(4, 5, 6)));
	}
	
	@Test
	public void testMultScalar() {
		assertEquals(32, new Vector3D(1, 2, 3).multScalar(new Vector3D(4, 5, 6)), 1e-5);
	}
	
	@Test
	public void	testMultVector() {
		assertEquals(new Vector3D(-3, 6, -3), new Vector3D(1, 2, 3).multVector(new Vector3D(4, 5, 6)));
	}
	
	@Test
	public void testGetModule() {
		assertEquals(6, new Vector3D(2, 4, 4).getModule(), 1e-5);
	}
	
	@Test
	public void testEquals() {
		assertTrue(new Vector3D(1, 2, 3).equals(new Vector3D(1, 2, 3)));
	}
	
	@Test
	public void testNotEquals() {
		assertFalse(new Vector3D(2, 2, 3).equals(new Vector3D(1, 2, 3)));
	}
	
	
}
