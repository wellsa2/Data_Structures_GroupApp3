package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;

public class Station
{
    public ArrayQueue<Passenger> passengerOutbound = new ArrayQueue<>();
    public ArrayQueue<Passenger> getPassengerInbount = new ArrayQueue<>();
    public int positionOnTrack;
}
