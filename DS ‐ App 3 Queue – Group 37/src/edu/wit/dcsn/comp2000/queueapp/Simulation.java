package edu.wit.dcsn.comp2000.queueapp;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wit.dcsn.comp2000.queueapp.Configuration.PairedLimit;

public class Simulation {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Configuration config = new Configuration();
		
		Random rand = new Random();
		
		int tickCounter = 0;
		
		Station stations[] = testStation(config);
		
		PairedLimit[] passengerParameters = testInitialPassengers(config, tickCounter, stations, rand);
		
		//tick iteration
		for(int i = 0; i < config.getTicks(); i++){
			
			testPerTickPassengers(passengerParameters, tickCounter, stations, rand);
			tickCounter++;
			
		}
		

}
	
	public static PairedLimit[] testInitialPassengers(Configuration config, int tickCounter, Station[] stations, Random rand){
		
		int minInitial = 0;
		int maxInitial = 0;
		int initialPassengers = 0;
		
		Station entStation = null;
		Station extStation = null;
		
		int randEnter = 0;
		int randExit = 0;
		
		PairedLimit[] passengerParameters = config.getPassengers();
		
		//initial limits
		minInitial = passengerParameters[0].minimum;
		maxInitial = passengerParameters[0].maximum;
		
		
		//check min and max
		if(minInitial == maxInitial){ 
			
			initialPassengers = minInitial;
			
		}
		
		else if(minInitial < maxInitial){
			
			initialPassengers = rand.nextInt(maxInitial) + minInitial;
			
		}

		Passenger[] initPassengers = new Passenger[initialPassengers];
		
		for(int i = 0; i < initPassengers.length; i++){
			
			randEnter = rand.nextInt(stations.length) + 1; //give each passenger a random entering station
			randExit = rand.nextInt(stations.length) + 1; //give each passenger a random exiting station
			
			if(randEnter == randExit){ //the entering station and exiting station shouldn't be the same.
				
				randExit++;
				
			}
			
			// assign randEnter to an actual station
			switch(randEnter){
			
			case 1: randEnter = 1;
			
				entStation = stations[0];
			
				break;
			
			case 2: randEnter = 2;
			
				entStation = stations[1];
			
				break;
			
			case 3: randEnter = 3;
			
				entStation = stations[2];
			
				break;
			
			case 4: randEnter = 4;
			
				entStation = stations[3];
			
				break;
			
			case 5: randEnter = 5;
			
				entStation = stations[4];
			
				break;
			
			}
			
			//assign randExit to an actual station
			switch(randExit){
			
			case 1: randExit = 1;
			
				extStation = stations[0];
			
				break;
			
			case 2: randExit = 2;
			
				extStation = stations[1];
			
				break;
			
			case 3: randExit = 3;
			
				extStation = stations[2];
				break;
				
			case 4: randExit = 4;
			
				extStation = stations[3];
				
				break;
				
			case 5: randExit = 5;
			
				extStation = stations[4];
				break;
			
			}
			
			initPassengers[i] = new Passenger(entStation, extStation, 0); // creating a passenger at initial time.
			
			
		}//end for
		
		
		return passengerParameters;
		
		
	}
	
	public static void testPerTickPassengers(PairedLimit[] ps, int tickCounter, Station[] stations, Random rand){
		
		int minPerTick = 0;
		int maxPerTick = 0;
		int pertickPassengers = 0;
		
		minPerTick = ps[1].minimum;
		maxPerTick = ps[1].maximum;
		
		if(minPerTick == maxPerTick){
			
			pertickPassengers = minPerTick;
			
		}
		
		else if(minPerTick < maxPerTick){
			
			pertickPassengers = rand.nextInt(maxPerTick) + minPerTick;
			
		}
		
		
		Station entStation = null;
		Station extStation = null;
		
		int randEnter = 0;
		int randExit = 0;
		
		if(randEnter == randExit){
			
			randExit++;
			
		}
		
		switch(randEnter){
		
		case 1: randEnter = 1;
		
			entStation = stations[0];
		
			break;
		
		case 2: randEnter = 2;
		
			entStation = stations[1];
		
			break;
		
		case 3: randEnter = 3;
		
			entStation = stations[2];
		
			break;
		
		case 4: randEnter = 4;
		
			entStation = stations[3];
		
			break;
		
		case 5: randEnter = 5;
		
			entStation = stations[4];
		
			break;
		
		}
		
		switch(randExit){
		
		case 1: randExit = 1;
		
			extStation = stations[0];
		
			break;
		
		case 2: randExit = 2;
		
			extStation = stations[1];
		
			break;
		
		case 3: randExit = 3;
		
			extStation = stations[2];
			break;
			
		case 4: randExit = 4;
		
			extStation = stations[3];
			
			break;
			
		case 5: randExit = 5;
		
			extStation = stations[4];
			break;
		
		}
		
		//create pertick passengers here
		
		
		
	}
	
	public static void testTrain(){
		
		
	}
	
	public static Station[] testStation(Configuration config){
		
		int[]stationLocations = config.getStations();
		Station[] stations = new Station[stationLocations.length];
		
		for(int i = 0; i < stations.length; i++){
			
			stations[0] = new Station(stationLocations[i]);
			
		}
		
		return stations;
		
	}
	
	public static void testTrainRoute(){
		
		
	}
	
	
	   
	}
	