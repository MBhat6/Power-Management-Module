//Main program
//Assumptions made: 1. There is a sensor module which gives the real time battery capacity in kW
//                  2. Running average is calculated for 5 sample of 100km each (5 sample moving average)
//                  3. It is assumed that to calculate baselines for passengers, the vehicle shall make start and stop accordingly
//                  4. methodStart is invoked when the engine is turned on
//                  5. methodStop is invoked when engine is turned off
//                  6. There is a switch to be controlled by the factory to turn on and off the driving mode button - either factory 
//                     learning mode or user mode

package com.powermanagement.main;

import com.powermanagement.mapapi.MapDataAPI;
import com.powermanagement.batteryapi.BatteryAPI;
import com.powermanagement.classes.DriveMode;
import com.powermanagement.classes.LearningMode;
import com.powermanagement.classes.UserMode;
import com.powermanagement.platformapi.PlatformAPI;
import com.powermanagement.sensor.SensorOutput;
import com.powermanagement.services.AlertCheckEvent;
import com.powermanagement.services.DiagnosticsInfoEvent;

public class ProgramDriver {
	
	private double distTravelled;
	private DriveMode driveMode = null;
	static double newCapacity;
	static double oldCapacity;
		
	//Driver program
	public static void main(String[] args) {
		
		ProgramDriver driver = new ProgramDriver();
		System.out.println("Test" + MapDataAPI.getDistance());
		driver.methodStart();
	}
	
	//Method invoked when the engine is turned on
	public void methodStart() {
		
		//current capacity of the battery
		oldCapacity = BatteryAPI.getCurrentCapacity();
		
		DiagnosticsInfoEvent newObj = new DiagnosticsInfoEvent();
		newObj.run();
		newObj.getChargeCycles();
		
		AlertCheckEvent alertObj = new AlertCheckEvent();
		alertObj.run();
		alertObj.checkForAlert();
		
		int percentBattery = (int)(oldCapacity/(DiagnosticsInfoEvent.getTotalCapacity())) * 100;
		
		//Display information to the user
		System.out.println("Battery consumed: " + percentBattery + "%");
		System.out.println("Temperature of the battery: " + BatteryAPI.getTemperature());
		System.out.println("Battery charging Cycles : " + newObj.getChargeCycle());
		System.out.println("Battery discharging Cycles : " + newObj.getDischargeCycle());
		
	}
	
	//Method invoked when the engine is turned off
	public void methodStop() {
		
		distTravelled = MapDataAPI.getDistTravelled();// Assumption that the distance is obtained from the ECR device
		
		newCapacity = BatteryAPI.getCurrentCapacity();
		
		if(distTravelled % 100 == 0) {
			
			//Assumption that, the car is either set to factory mode or user mode - often controlled by the factory
			//Get the drive mode type from a switch output
			String mode = SensorOutput.getMode();
			
			//When the mode is learning
			if(mode.equals("Learning")) {
				
				driveMode = new LearningMode(PlatformAPI.getPassengerCount());
				driveMode.calculateAverage(newCapacity, oldCapacity);
			}
			
			//When the mode is user
			else if(mode.equals("User")) {
				
				driveMode = new UserMode(PlatformAPI.getPassengerCount());
				driveMode.calculateAverage(newCapacity, oldCapacity);
			}
			else {
				System.out.println("Invalid mode");
				
			}			
		}
		//System.out.println("Test" + MapDataAPI.getDistance()+ BatteryAPI.getCellCount() + PlatformAPI.getPassengerCount() );
		
	}
}
