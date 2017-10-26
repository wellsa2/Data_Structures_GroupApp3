package edu.wit.dcsn.comp2000.queueapp ;

/* COMP 2000 - 03
 * Queue Application 3
 * Group 37
 * AUTHOR TBD
 * 10/24/2017
 */

import java.util.ArrayList ;

/**
 * Acts as a point-to-point track where Train objects and Station objects are held.
 *
 * @author TBD
 * 
 */

// TODO restructure add/remove methods
public class TrainRoute
{
	
	// instance variables
    private ArrayList<Train> inboundTrains = 	new ArrayList<>() ;	// array of inbound trains
    private ArrayList<Train> outboundTrains = 	new ArrayList<>() ; // array of outbound trains
    private ArrayList<Station> stations = 		new ArrayList<>() ; // array of stations
    private int routeLength ;										// arbitrary unit length of track

    /**
     * Creates and initializes a TrainRoute object given the route length. 
     * @param routeLength arbitrary unit length of route
     */
    public TrainRoute( int routeLength )
    {
        this.routeLength = routeLength ;
    } // end constructor

    /**
     * Add Station object to index.
     * @param station 
     */
    public void addStation( Station station )
    {
        stations.add( station ) ;
    } // end addStation

    //adds train to specific index
    public void addInboundTrain( Train train )
    {
        inboundTrains.add( train ) ;
    } // end addInboundTrain

    public void removeInboundTrain( Train train )
    {
        inboundTrains.remove( train ) ;
    } // end removeInboundTrain

    public void addOutboundTrain( Train train )
    {
        outboundTrains.add( train ) ;
    } // end addOutboundTrain

    public void removeOutboundTrain( Train train )
    {
        outboundTrains.remove( train ) ;
    } // end removeOutboundTrain

    // TODO rearrange for loops to increase efficiency
    /**
     * Iterates through indexes to determine which trains have arrived at a station,
     * transferring passengers on the trains that have.
     */
    public void determineArrivals()
    {
        for ( Station station : stations )
        {
            for ( Train train : inboundTrains )
            {
                if ( station.getPositionOnTrack() == train.getPositionOnTrack() )
                {
                    train.transferPassengers( station, true ) ;
                } // end if
            } // end for

            for ( Train train : outboundTrains )
            {
                if ( station.getPositionOnTrack() == train.getPositionOnTrack() )
                {
                    train.transferPassengers( station, false ) ;
                } // end if
            } // end for
        } // end for
    } // end determineArrivals
    
} // end TrainRoute
