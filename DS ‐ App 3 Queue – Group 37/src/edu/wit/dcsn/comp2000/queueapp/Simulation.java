package edu.wit.dcsn.comp2000.queueapp;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wit.dcsn.comp2000.queueapp.Configuration.PairedLimit;
import edu.wit.dcsn.comp2000.queueapp.Configuration.RouteSpec;
import edu.wit.dcsn.comp2000.queueapp.Configuration.TrainSpec;

public class Simulation {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Configuration config = new Configuration();
		
		Random rand = new Random();
		
		int tickCounter = 0;
		
		Station stations[] = setStations(config); //build stations
		
		PairedLimit[] passengerParameters = testInitialPassengers(config, tickCounter, stations, rand);
		
		TrainRoute route = setTrainRoute(config, stations); //build train route and add stations to the route
		
		setTrains(config, route); //build trains and add them to the route
		
		Passenger[] pertickPassengers = new Passenger[config.getPassengers()[config.PASSENGERS_PER_TICK].maximum];
		
		tickCounter++; //starting tick iteration...
		
		for(int i = 1; i <= config.getTicks(); i++){
			
			determinePerTickPassengers(passengerParameters, pertickPassengers, tickCounter, stations, rand);
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
			
			// assign entStation to an actual station
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
			
			//assign extStation to an actual station
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
		
		addPassengersToStation(initPassengers);
		
		
		return passengerParameters;
		
		
	}
	
	public static void determinePerTickPassengers(PairedLimit[] ps, Passenger[] perTickPassengers, int tickCounter, Station[] stations, Random rand){
		
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
		
		for(int i = 0; i < pertickPassengers; i++){
		
		randEnter = rand.nextInt(stations.length) + 1; //give each passenger a random entering station
		randExit = rand.nextInt(stations.length) + 1; //give each passenger a random exiting station
		
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
		
		perTickPassengers[i] = new Passenger(entStation, extStation, tickCounter);
		
		}// end for
		
		addPassengersToStation(perTickPassengers); //add the passengers to their entering station
		Arrays.fill(perTickPassengers, null); //clear array for next tick iteration
		
	}
	
	public static void addPassengersToStation(Passenger[] p){
		
		for(int i = 0; i < p.length; i++){
			
			p[i].getEnteringStation().addPassenger(p[i]);
			
			
		}
		
		
	}
	
	public static void setTrains(Configuration config, TrainRoute tr){
		
		TrainSpec[] spec = config.getTrains();
		
		Train[] trains = new Train[spec.length];
		
		for(int i = 0; i < trains.length; i++){
			
			trains[i] = new Train(spec[i].location, spec[i].direction, spec[i].capacity); //create trains
			
		}
		
		// add trains to the train route
		
		for(int i = 0; i < trains.length; i++){
			
			if(trains[i].direction == Direction.INBOUND){
				
				tr.addInboundTrain(spec[i].location, trains[i]) ;
				
			}
			
			else if(trains[i].direction == Direction.OUTBOUND){
				
				tr.addOutboundTrain(spec[i].location, trains[i]);
				
			}
			
		} //end for
		
		
	}
	
	public static Station[] setStations(Configuration config){
		
		int[]stationLocations = config.getStations();
		Station[] stations = new Station[stationLocations.length];
		
		for(int i = 0; i < stations.length; i++){
			
			stations[i] = new Station(stationLocations[i]);
			
		}
		
		return stations;
		
	}
	
	public static TrainRoute setTrainRoute(Configuration config, Station[] stations){
		
		RouteSpec routeSpec = config.getRoute();
		
		int rtLength = routeSpec.length; //get length of train route
		
		
		TrainRoute trainRoute = new TrainRoute(rtLength); //create route
		

		for(int i = 0; i < stations.length; i++){ //add stations to the route
			
			trainRoute.addStation(stations[i]); 
			
		}
		
		return trainRoute;
		
	}
	
	
	
	   
	}

	