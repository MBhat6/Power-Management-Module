
//API to learn about the battery information
package com.powermanagement.batteryapi;

//class to get the battery information
public class BatteryAPI extends Object{
	
	public static int cellCount;
	
	static float cellCapacity;
	
	static float temperature;
	
	static float currentCapacity;
	
	public BatteryAPI() {
		
	}

	//to get the number of cells configured in the battery
	public static int getCellCount() {
		return cellCount;
	}

	//to get the capacity of each cell in the battery
	public static float getCellCapacity() {
		return cellCapacity;
	}

	//to get the temperature of the battery
	public static float getTemperature() {
		return temperature;
	}

	//The Battery API also provides the current ca[acity of the battery from the sensor device measuring it
	public static float getCurrentCapacity() {
		return currentCapacity;
	}
	
}
