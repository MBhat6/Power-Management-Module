package com.powermanagement.platformapi;

public class PlatformAPI extends Object{
	
	public static int passengerCount;
	
	public PlatformAPI() {
		
	}

	//gets the passenger count from the platform team
	public static int getPassengerCount() {
		
		return passengerCount;
		
	}
}
