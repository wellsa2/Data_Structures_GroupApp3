package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.EmptyQueueException;

/**
 * A class that specifies the properties and functions of a train station.
 */
public class Station
{
    private ArrayQueue<Passenger> passengerOutbound;
    private ArrayQueue<Passenger> passengerInbound;
    private int numberOfOutboundPassengers = 0;
    private int numberOfInboundPassengers = 0;
    private int positionOnTrack;
    private int stationID;
    private static int idCount = 0;

    public Station()
    {
        this(0);
    }

    public Station(int positionOnTrack)
    {
        this.positionOnTrack = positionOnTrack;
        passengerOutbound = new ArrayQueue<>();
        passengerInbound = new ArrayQueue<>();
        this.stationID = idCount++;
    }

    
    /**
     * adds a passenger to either the inbound or outbound queue.
     * @param passenger
     */
    public void addPassenger(Passenger passenger)
    {
        if ( goInbound( passenger ) )
        {
            passengerInbound.enqueue(passenger);
            numberOfInboundPassengers++;
        }
        else
        {
            passengerOutbound.enqueue(passenger);
            numberOfOutboundPassengers++;
        }
    }// end addPassenger


    public boolean canBoard( boolean isInbound )
    {
        if ( isInbound )
        {
            if ( !passengerInbound.isEmpty() )
            {
                return true;
            }
            return false;
        }
        else
        {
            if ( !passengerOutbound.isEmpty() )
            {
                return true;
            }
            return false;
        }
    }


    public int getNumberOfOutboundPassengers()
    {
        return numberOfOutboundPassengers;
    }


    public int getNumberOfInboundPassengers()
    {
        return numberOfInboundPassengers;
    }


    /**
     * Boards a passenger onto the train. Removes the passenger from the respective queue.
     * @param isInbound
     * @return boarding passenger
     */
    public Passenger board(boolean isInbound)
    {
        if ( !canBoard( isInbound ) )
        {
            throw new EmptyQueueException("Queue is empty. No passengers to board.");
        }
        if ( isInbound )
        {
            numberOfInboundPassengers--;
            return passengerInbound.dequeue();
        }
        else
        {
            numberOfOutboundPassengers--;
            return passengerOutbound.dequeue();
        }
    }// end board
    
    /**
     * gets a specified station's position on the track.
     * @return station's position
     */
    public int getPositionOnTrack()
    {
        return positionOnTrack;
    }// end getPositionOnTrack


    public boolean inboundQueueEmpty()
    {
        return passengerInbound.isEmpty();
    }


    public boolean outboundQueueEmpty()
    {
        return passengerOutbound.isEmpty();
    }


    /**
     * gets a specified station's id number.
     * @return stationID
     */
    public int getStationID()
    {
        return stationID;
    }// end getStationID


    /**
     * Determines if a passenger should wait in the inbound (true) or outbound (false) queue.
     * @param passenger
     * @return true or false
     */
    public boolean goInbound(Passenger passenger)
    {
        // Inbound -> traveling to station at larger index
        // Outbound -> traveling to station at smaller index
        return passenger.getEnteringStation().getPositionOnTrack() < passenger.getExitingStation().getPositionOnTrack();
    }// end goInbound

    
    /**
     * Converts specific station to a string.
     * @return station as a string
     */
    @Override
    public String toString()
    {
        return "Station " + stationID;
    }// end toString


    public static void main(String[] args)
    {
        System.out.println("\n----------Testing Station----------\n");

        testConstructors();

        testPublicMethods();
    }


    private static void testConstructors()
    {
        Station station0 = new Station();
        Station station1 = new Station(3);
    }


    private static void testPublicMethods()
    {
        //testing goInbound
        Station station0 = new Station(0);
        Station station1 = new Station(1);
        Station station2 = new Station(2);
        Passenger inboundPassenger = new Passenger(station1, station2, 0);
        Passenger outboundPassenger = new Passenger(station1, station0, 1);
        System.out.println(station1.goInbound(inboundPassenger));//working
        System.out.println(station1.goInbound(outboundPassenger));//working

        //testing addPassenger
        station1.addPassenger(inboundPassenger);//working
        station1.addPassenger(outboundPassenger);//working

        //testing board
        System.out.println(station1.board(true));//working
        System.out.println(station1.board(false));//working

        //testing canBoard
        System.out.println( station1.canBoard( true ) );//working
        station1.addPassenger(inboundPassenger);
        System.out.println( station1.canBoard( true ) );//working

        //testing getPositionOnTrack
        System.out.println(station1.getPositionOnTrack());//working

        //testing getStationID
        System.out.println(station1.getStationID());//working

        //testing toString
        System.out.println(station1.toString());//working
    }
}// end Station
