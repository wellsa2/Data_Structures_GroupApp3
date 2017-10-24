package edu.wit.dcsn.comp2000.queueapp ;

//COMP 2000 - 03
//Queue Application 3
//Group 37
//Aiden Wells
//10/24/2017

import java.util.ArrayList ;

/**
 * Acts as a point-to-point track where Train objects and Station objects are held.
 *
 * 
 * @author Aiden Wells
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

    //adds train to first index
    public void addInboundTrain( Train train )
    {
        addInboundTrain( 0, train ) ;
    } // end addInboundTrain

    //adds train to specific index
    public void addInboundTrain( 	int positionOnTrack,
    								Train train )
    {
        inboundTrains.add( positionOnTrack, train ) ;
    } // end addInboundTrain

    //removes train at end of list
    public void removeInboundTrain()
    {
        removeInboundTrain( routeLength - 1 ) ;
    } // end removeInboundTrain

    //removes train at index
    public void removeInboundTrain( int positionOnTrack )
    {
        inboundTrains.remove( positionOnTrack ) ;
    } // end removeInboundTrain

    public void addOutboundTrain( Train train )
    {
        addOutboundTrain( 0, train ) ;
    } // end addOutboundTrain

    public void addOutboundTrain( 	int positionOnTrack,
    								Train train )
    {
        outboundTrains.add( positionOnTrack, train ) ;
    } // end addOutboundTrain

    public void removeOutboundTrain()
    {
        removeOutboundTrain( routeLength - 1 ) ;
    } // end removeOutboundTrain

    public void removeOutboundTrain( int positionOnTrack )
    {
        outboundTrains.remove( positionOnTrack ) ;
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
