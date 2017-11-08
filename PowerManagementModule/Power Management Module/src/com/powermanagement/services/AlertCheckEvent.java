package com.powermanagement.services;

import com.powermanagement.mapapi.MapDataAPI;
import com.powermanagement.batteryapi.BatteryAPI;
import com.powermanagement.classes.LearningMode;

public class AlertCheckEvent implements Runnable{

	@Override
	public void run() {
		System.out.println("Thread running");
		
		try {
			checkForAlert();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkForAlert() {
		
		float travelDist = MapDataAPI.getDistance();
		int average =  LearningMode.getAverage();
		float capacity = BatteryAPI.getCurrentCapacity();
		float actualCapacity = DiagnosticsInfoEvent.getTotalCapacity();
		boolean flag = false;
		
		float estimatedDist = capacity/average;
		
		if(estimatedDist  < travelDist) {
			
			System.out.println("Alert!!!! Insufficient battery for the trip. Please charge your vehicle");
			flag = true;
			
		}
		
		float batteryToEmpty = (capacity/actualCapacity) * 100;
		
		if(batteryToEmpty <= 20) {
			
			System.out.println("Alert!!!! Low on battery. Please charge your vehicle");
			flag = true;
			
		}
		return flag;		
	}
	
	public static void main(String[] args) {
		
		AlertCheckEvent alertEvent = new AlertCheckEvent();
		
		Thread alertThread = new Thread(alertEvent);		
		alertThread.start();
	}
}
