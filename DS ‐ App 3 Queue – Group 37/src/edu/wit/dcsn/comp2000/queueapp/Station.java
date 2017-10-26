package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;
/**
 * A class that specifies the properties and functions of a train station.
 */
public class Station
{
    private ArrayQueue<Passenger> passengerOutbound;
    private ArrayQueue<Passenger> passengerInbound;
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
        if (goInbound(passenger))
        {
            passengerInbound.enqueue(passenger);
        }
        else
        {
            passengerOutbound.enqueue(passenger);
        }
    }// end addPassenger
    
    /**
     * Boards a passenger onto the train. Removes the passenger from the respective queue.
     * @param isInbound
     * @return boarding passenger
     */
    public Passenger board(boolean isInbound)
    {
        if (isInbound)
        {
            return passengerInbound.dequeue();
        }
        else
        {
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
}// end Station
