package edu.wit.dcsn.comp2000.queueapp ;

/* COMP 2000 - 03
 * Queue Application 3
 * Group 37
 * AUTHOR TBD
 * 10/24/2017
 */

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
    public Train(	int maxCapacity,
    				int positionOnTrack )
    {
        passengersOnTrain =		new ArrayList<>() ;
        this.maxCapacity = 		maxCapacity ;
        this.positionOnTrack = 	positionOnTrack ;
        trainID = idCount++ ;
    } // end constructor

    /**
     * Joint method call for disembarking and embarking passengers for a train at a station.
     * 
     * @param currentStation station at the same position on the track as the train
     * @param isInbound true for inbound, false for outbound
     */
    public void transferPassengers(	Station currentStation,
    								boolean isInbound )
    {
        disembark( currentStation ) ;
        embark( currentStation, isInbound ) ;
    } // end transferPassengers

    /**
     * Removes those passengers from the train whose destination station is the current station.
     * 
     * @param currentStation 
     */
    public void disembark( Station currentStation )
    {
        for ( int i = 0 ; i < passengersOnTrain.size() ; i++ )
        {
            if ( passengersOnTrain.get( i ).getExitingStation() == currentStation )
            {
                passengersOnTrain.set( i, passengersOnTrain.get( passengersOnTrain.size() - 1 ) ) ;
                passengersOnTrain.remove( passengersOnTrain.size() - 1 ) ;
                i-- ;
            } // end if
        } // end for
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
        while( passengersOnTrain.size() < maxCapacity )
        {
            passengersOnTrain.add( currentStation.board( isInbound ) ) ;
        } // end while
    } // end embark

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
}
