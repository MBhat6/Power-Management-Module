package com.powermanagement.juints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.powermanagement.classes.UserMode;

class TestCase01 {

	@Test
	void test() {
		
		UserMode newObj = new UserMode(2);
		//fail("Not yet implemented");
		assertEquals(newObj.calculateAverage(1300, 2000.0), 7);
		
		assertEquals(newObj.calculateAverage(1200, 1300), 1);
		
		assertEquals(newObj.calculateAverage(900, 1200), 3);
//		
//		
	}

}
