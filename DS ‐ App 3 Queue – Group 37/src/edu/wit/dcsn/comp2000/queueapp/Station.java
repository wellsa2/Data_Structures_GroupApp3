package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;

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
        stationID = idCount++;
    }

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
    }

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
    }

    public int getPositionOnTrack()
    {
        return positionOnTrack;
    }

    public int getStationID()
    {
        return stationID;
    }

    public boolean goInbound(Passenger passenger)
    {
        return passenger.getEnteringStation().getPositionOnTrack() < passenger.getExitingStation().getPositionOnTrack();
    }

    @Override
    public String toString()
    {
        return "Station " + stationID;
    }
}
