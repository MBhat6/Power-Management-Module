package com.powermanagement.mapapi;

public class MapDataAPI extends Object{
		
	public static float distance;
	static double distTravelled;
	
	public MapDataAPI() {
		
		
	}
	
	//get the distance from a module
	public static float getDistance() {
		
		return distance;
	}

	//API team provides the distance travelled by the car
	public static double getDistTravelled() {
		return distTravelled;
	}
	
}
