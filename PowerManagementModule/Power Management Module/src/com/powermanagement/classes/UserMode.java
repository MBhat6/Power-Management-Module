//This class is designed with an assumption that the running average is calculated for every 5 samples of 100km each.

package com.powermanagement.classes;

import java.util.*;

public class UserMode implements DriveMode{

	private int passengerCount;
	private final int baseNum = 100;
	
	//Assumptions: Calculation the baselines for 5 samples hence storing the baseline values for 5 days and then evaluating the moving average
	//Used queue to store the baseline averages for all the cases
	Queue<Integer> avgQueue = new ArrayDeque<Integer>();
	Queue<Integer> baseline1Queue = new ArrayDeque<Integer>();
	Queue<Integer> baseline2Queue = new ArrayDeque<Integer>();
	Queue<Integer> baseline3Queue = new ArrayDeque<Integer>();
	Queue<Integer> baseline4Queue = new ArrayDeque<Integer>();
		
	//get the passenger count from the API
	public UserMode(int passengerCount) {
		
		this.passengerCount = passengerCount;
	}
	
	@Override
	public int calculateAverage(double newVal, double oldVal) {
		// TODO Auto-generated method stub
		
		int newAvg = (int)(oldVal - newVal) / baseNum;
		
		switch(passengerCount) {
			
			case 0:
				if(avgQueue.size() == 5) {
					avgQueue.remove();
					avgQueue.add(newAvg);
				}
				else {
					avgQueue.add(newAvg);
				}
				
				for(Integer item : avgQueue) {
					
					LearningMode.average += item;
				}
				
				return (LearningMode.average/avgQueue.size());
			
			case 1:
				
				if(baseline1Queue.size() == 5) {
					baseline1Queue.remove();
					baseline1Queue.add(newAvg);
				}
				else {
					baseline1Queue.add(newAvg);
				}
				
				for(Integer item : baseline1Queue) {
					
					LearningMode.baseLine1 += item;
				}
				
				return (LearningMode.baseLine1/baseline1Queue.size());
				
			case 2:
				if(baseline2Queue.size() == 5) {
					baseline2Queue.remove();
					baseline2Queue.add(newAvg);
				}
				else {
					baseline2Queue.add(newAvg);
					System.out.println(newAvg);
				}
				
				for(Integer item : baseline2Queue) {
					
					LearningMode.baseLine2 += item;
					System.out.println(item + " ___item");
				}
				
				return (LearningMode.baseLine2/(baseline2Queue.size()));
				
			case 3:
				
				if(baseline3Queue.size() == 5) {
					baseline3Queue.remove();
					baseline3Queue.add(newAvg);
				}
				else {
					baseline3Queue.add(newAvg);
				}
				
				for(Integer item : baseline3Queue) {
					
					LearningMode.baseLine3 += item;
				}
				
				return (LearningMode.baseLine3/baseline3Queue.size());
				
			case 4:
				
				if(baseline4Queue.size() == 5) {
					baseline4Queue.remove();
					baseline4Queue.add(newAvg);
				}
				else {
					baseline4Queue.add(newAvg);
				}
				
				for(Integer item : baseline4Queue) {
					
					LearningMode.baseLine4 += item;
				}
				
				return (LearningMode.baseLine4/baseline4Queue.size());
	
			
			default:
				return 0;
		
		}
	}

}
