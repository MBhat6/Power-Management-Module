package com.powermanagement.services;

import com.powermanagement.batteryapi.BatteryAPI;

public class DiagnosticsInfoEvent implements Runnable{
	
	static float currentCapacity = 0;
	static float TotalCapacity = 0;
	private static float chargeCycle = 0;
	private static float dischargeCycle = 0;
	static int chargeCounter = 0;
	static int dischargeCounter = 0;
	static private float prevValue = 0;
	
	//get total capacity of the battery
	public static float getTotalCapacity() {
		
		TotalCapacity = BatteryAPI.getCellCount() * BatteryAPI.getCellCapacity();		
		return  TotalCapacity;
		
	}
	
	//get the current capacity of the battery at any time - assumed to come from an ECU system
	public static float getCurrentCapacity() {
		
		currentCapacity = BatteryAPI.getCurrentCapacity();
		
		return currentCapacity;
		
	}

	//calculate the charge and discharge cycles
	public void getChargeCycles() {
		
		if(prevValue  < currentCapacity) {
			chargeCycle = currentCapacity - prevValue;
			
			if(chargeCycle > 100) {
				chargeCycle = chargeCycle -100;
				chargeCounter++;
			}
		}
		else if(prevValue  > currentCapacity) {
			dischargeCycle = prevValue - currentCapacity;
			
			if(dischargeCycle > 100) {
				dischargeCycle = dischargeCycle -100;
				dischargeCounter++;
			}
		}
		prevValue = currentCapacity;
		
	}
	
	//to display the number of charge cycles
	public int getChargeCycle() {
		return chargeCounter;
	}
	
	//to display the number of discharge cycleslearning mode or
	public int getDischargeCycle() {
		return dischargeCounter;
	}

	@Override
	public void run() {
		
		try {
			getTotalCapacity();
			getCurrentCapacity();
			prevValue = TotalCapacity;
			getChargeCycles();
			
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		DiagnosticsInfoEvent diagnosticEvent = new DiagnosticsInfoEvent();
		
		Thread cycleThread = new Thread(diagnosticEvent);		
		cycleThread.start();
	}
	
}
