package edu.wit.dcsn.comp2000.queueapp;

public class Passenger
{
    private Station enteringStation = new Station();
    private Station exitingStation = new Station();
    private int passengerID;
    private int entryTime;
    private static int idCount = 0;

    public Passenger(Station enteringStation, Station exitingStation, int entryTime)
    {
        this.enteringStation = enteringStation;
        this.exitingStation = exitingStation;
        this.entryTime = entryTime;
        passengerID = idCount++;
    }

    public int getPassengerID()
    {
        return passengerID;
    }

    public Station getEnteringStation()
    {
        return enteringStation;
    }

    public Station getExitingStation()
    {
        return exitingStation;
    }

    public int getEntryTime()
    {
        return entryTime;
    }

    @Override
    public String toString()
    {
        return "Passenger " + passengerID;
    }
}
