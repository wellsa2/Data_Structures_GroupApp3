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
        passengerOutbound = new ArrayQueue<>();
        passengerInbound = new ArrayQueue<>();
        stationID = idCount++;
    }

    public void addPassengerInbound(Passenger passenger)
    {
        passengerInbound.enqueue(passenger);
    }

    public void addPassengerOutbound(Passenger passenger)
    {
        passengerOutbound.enqueue(passenger);
    }

    public int getPositionOnTrack()
    {
        return positionOnTrack;
    }

    public int getStationID()
    {
        return stationID;
    }

    @Override
    public String toString()
    {
        return "Station " + stationID;
    }
}
