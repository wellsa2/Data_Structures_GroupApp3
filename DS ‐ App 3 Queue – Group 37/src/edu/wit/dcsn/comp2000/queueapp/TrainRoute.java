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
 * Acts as a point-to-point track where Train objects and Station objects are held.
 *
 * @author TBD
 * 
 */

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
     * Adds Station object to index.
     * @param station 
     */
    public void addStation( Station station )
    {
        stations.add( station );
    } // end addStation

    /**
     * Adds Train object to the inbound track.
     * @param train
     */
    public void addInboundTrain( Train train )
    {
        inboundTrains.add( train ) ;
    } // end addInboundTrain

    /**
     * Removes Train object from the inbound track.
     * @param train
     */
    public void removeInboundTrain( Train train )
    {
        inboundTrains.remove( train ) ;
    } // end removeInboundTrain

    /**
     * Adds Train object to the outbound track.
     * @param train
     */
    public void addOutboundTrain( Train train )
    {
        outboundTrains.add( train ) ;
    } // end addOutboundTrain

    /**
     * Removes Train object from the outbound track.
     * @param train
     */
    public void removeOutboundTrain( Train train )
    {
        outboundTrains.remove( train ) ;
    } // end removeOutboundTrain

    /**
     * Returns route length
     * @return Route length as integer
     */
    public int getRouteLength()
    {
        return routeLength;
    }

    public int getNumberOfStations()
    {
        return stations.size();
    }

    public Station getStation(int index)
    {
        return stations.get(index);
    }

    public int getNumberOfInboundTrains()
    {
        return inboundTrains.size();
    }

    public Train getInboundTrains(int index)
    {
        return inboundTrains.get(index);
    }

    public int getNumberOfOutboundTrains()
    {
        return outboundTrains.size();
    }

    public Train getOutboundTrains(int index)
    {
        return outboundTrains.get(index);
    }

    public void moveTrains()
    {
        swapTrainsAtEndOfLine();
        for (Train train : inboundTrains)
        {
            train.moveTrain(true);
        }
        for (Train train : outboundTrains)
        {
            train.moveTrain(false);
        }
    }

    private void swapTrainsAtEndOfLine()
    {
        for (int i = 0; i < inboundTrains.size(); i++)
        {
            if (inboundTrains.get(i).getPositionOnTrack() == routeLength - 1)
            {
                Train trainToMove = inboundTrains.get(i);
                inboundTrains.remove(trainToMove);
                outboundTrains.add(trainToMove);
            }
        }

        for (int i = 0; i < outboundTrains.size(); i++)
        {
            if (outboundTrains.get(i).getPositionOnTrack() == 0)
            {
                Train trainToMove = outboundTrains.get(i);
                outboundTrains.remove(trainToMove);
                inboundTrains.add(trainToMove);
            }
        }
    }

    /**
     * Iterates through indexes to determine which trains have arrived at a station,
     * transferring passengers on the trains that have.
     * @return String describing disembarking passengers
     */
    public String determineArrivals(int tick)
    {
        StringBuilder disembarkingPassengersLog = new StringBuilder();

        for ( Station station : stations )
        {
            for ( Train train : inboundTrains )
            {
                if ( station.getPositionOnTrack() == train.getPositionOnTrack() )
                {
                    ArrayList<Passenger> disembarkingPassengers = train.transferPassengers( station, true );
                    for (Passenger passenger : disembarkingPassengers)
                    {
                        disembarkingPassengersLog.append(String.format("%n      Passenger %d arrived at Station %d in %d ticks%n", passenger.getPassengerID(), station.getStationID(), tick -passenger.getEntryTime()));
                    }
                } // end if
            } // end for

            for ( Train train : outboundTrains )
            {
                if ( station.getPositionOnTrack() == train.getPositionOnTrack() )
                {
                    ArrayList<Passenger> disembarkingPassengers = train.transferPassengers( station, false );
                    for (Passenger passenger : disembarkingPassengers)
                    {
                        disembarkingPassengersLog.append(String.format("%n      Passenger %d arrived at Station %d in %d ticks%n", passenger.getPassengerID(), station.getStationID(), tick -passenger.getEntryTime()));
                    }
                } // end if
            } // end for
        } // end for

        return disembarkingPassengersLog.toString();
    } // end determineArrivals
    
} // end TrainRoute
