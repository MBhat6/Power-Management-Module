//This class is designed with an assumption that, to update baseline with passengers, the engine is completely stopped.

package com.powermanagement.classes;

public class LearningMode implements DriveMode {
	
	private int passengerCount;
	static int average;
	static int baseLine1;
	static int baseLine2;
	static int baseLine3;
	static int baseLine4;
	private final int baseNum = 100;
	
	public LearningMode(int passengerCount) {
		
		this.passengerCount = passengerCount;
	}
	
	public static int getAverage() {
		
		return average;
	}
	
	@Override
	public int calculateAverage(double newVal, double oldVal) {
		// Assuming that the engine is turned on and off when caliberated for different baselines for different passengers
		
		switch (passengerCount) {
			
			case 0:
				average = (int)(oldVal - newVal) / baseNum;
				return average;
				
			case 1:
				baseLine1 = (int)(oldVal - newVal) / baseNum;
				return baseLine1;
				
			case 2:
				baseLine2 = (int)(oldVal - newVal) / baseNum;
				return baseLine2;
				
			case 3:
				baseLine3 = (int)(oldVal - newVal) / baseNum;
				return baseLine3;
				
			case 4:
				baseLine4 = (int)(oldVal - newVal) / baseNum;
				return baseLine4;
				
			default:
				return average;
		}
				
	}

	
}
