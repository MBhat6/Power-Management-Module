package com.powermanagement.juints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.powermanagement.classes.LearningMode;

class TestCase_02 {

	@Test
	void test() {
		LearningMode Obj = new LearningMode(0);
		assertEquals(Obj.calculateAverage(1400,2000.0), 6);
		//fail("Not yet implemented");
		
		assertEquals(Obj.calculateAverage(2000.0, 1400), -6);
		
		assertEquals(Obj.calculateAverage(0, 2000), 20);
	}

}
