package edu.wit.dcsn.comp2000.queueapp ;

/* COMP 2000 - 03
 * Queue Application 3
 * Group 37
 * AUTHOR TBD
 * 10/24/2017
 */

import java.lang.reflect.Array;
import java.util.ArrayList ;

/**
 * Acts as a passenger train, maintaining a list of its passengers, 
 * its location, and 
 * @author TBD
 *
 */
public class Train
{
	// instance variables
    private ArrayList<Passenger> passengersOnTrain ;	// list of passengers on train
    private int maxCapacity ;							// maximum number of passengers permitted on train
    private int positionOnTrack ;						// train's current position on track
    private int trainID ;								// ID will be unique for each Train object based off idCount
    
    // static variable
    private static int idCount = 0 ;					// counts up by 1 for each new Train object created

    /**
     * Creates and initializes a Train object given a maximum capacity and its position on the track.
     * 
     * @param maxCapacity maximum number of passengers permitted on train
     * @param positionOnTrack between 0 & routeLength (from config), inclusive
     */
     public Train(  int positionOnTrack,
                    int maxCapacity )
    {
        passengersOnTrain =		new ArrayList<>() ;
        this.positionOnTrack = 	positionOnTrack ;
        this.maxCapacity = 		maxCapacity ;
        trainID = idCount++ ;
    } // end constructor

    /**
     * Joint method call for disembarking and embarking passengers for a train at a station.
     * 
     * @param currentStation station at the same position on the track as the train
     * @param isInbound true for inbound, false for outbound
     */
    public ArrayList<Passenger> transferPassengers(	Station currentStation,
    								boolean isInbound )
    {
        ArrayList<Passenger> disembarkingPassengers = disembark( currentStation ) ;
        embark( currentStation, isInbound ) ;
        return disembarkingPassengers;
    } // end transferPassengers

    /**
     * Removes those passengers from the train whose destination station is the current station.
     * 
     * @param currentStation 
     */
    public ArrayList<Passenger> disembark( Station currentStation )
    {
        ArrayList<Passenger> disembarkingPassengers = new ArrayList<>();

        for ( int i = 0 ; i < passengersOnTrain.size() ; i++ )
        {
            if ( passengersOnTrain.get( i ).getExitingStation() == currentStation )
            {
                disembarkingPassengers.add(passengersOnTrain.get(i));
                passengersOnTrain.set( i, passengersOnTrain.get( passengersOnTrain.size() - 1 ) ) ;
                passengersOnTrain.remove( passengersOnTrain.size() - 1 ) ;
                i-- ;
            } // end if
        } // end for

        return disembarkingPassengers;
    } // end disembark

    /**
     * Adds passengers from the appropriate platform to the train.
     * 
     * @param currentStation station to take passengers from
     * @param isInbound	determines platform to take from: inbound for true, outbound for false
     */
    public void embark( Station currentStation,
    					boolean isInbound )
    {
        while( passengersOnTrain.size() < maxCapacity && currentStation.canBoard(isInbound))
        {
            passengersOnTrain.add( currentStation.board( isInbound ) );
        } // end while
    } // end embark


    /**
     * Adjusts the train's index based on whether the train is moving inbound or outbound
     * @param isInbound true if the train is headed inbound
     */
    public void moveTrain(Boolean isInbound)
    {
        if (isInbound)
        {
            positionOnTrack++;
        }
        else
        {
            positionOnTrack--;
        }
    }


    /**
     * Gives current position of train on the track.
     * 
     * @return current position of train
     */
    public int getPositionOnTrack()
    {
        return positionOnTrack ;
    } // end getPositionOnTrack

    /**
     * Gives current train's ID.
     * 
     * @return
     */
    public int getTrainID()
    {
        return trainID ;
    } // end getTrainID

    @Override
    /**
     * Converts train ID to String
     * 
     * @return formatted string
     */
    public String toString()
    {
        return "Train " + trainID ;
    } // end toString


    public int numberOfPassengers()
    {
        return passengersOnTrain.size();
    }


    public Passenger getPassenger(int index)
    {
        return passengersOnTrain.get(index);
    }


    public static void main(String[] args)
    {
        System.out.println("\n----------Testing Train----------\n");

        testConstructors();

        testPublicMethods();
    }

    private static void testConstructors()
    {
        System.out.println("\n----------\nTesting Constructors");
        Train train0 = new Train(20, 0);
    }

    private static void testPublicMethods()
    {
        Station station0 = new Station(0);
        Station station1 = new Station(1);
        Passenger inboundPassenger0 = new Passenger(station0, station1, 0);
        Passenger inboundPassenger1 = new Passenger(station0, station1, 0);
        Passenger inboundPassenger2 = new Passenger(station0, station1, 0);
        station0.addPassenger(inboundPassenger0);
        station0.addPassenger(inboundPassenger1);
        station0.addPassenger(inboundPassenger2);
        Train train0 = new Train(2, 0);
        train0.transferPassengers(station0, true);
        train0.moveTrain(true);
        train0.transferPassengers(station1, true);
    }
}
