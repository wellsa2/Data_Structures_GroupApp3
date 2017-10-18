package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;

public class Train
{
    private ArrayList<Passenger> passengersOnTrain;
    private int maxCapacity;
    private int positionOnTrack;
    private int trainID;
    private static int idCount = 0;

    public Train(int maxCapacity, int positionOnTrack)
    {
        passengersOnTrain = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.positionOnTrack = positionOnTrack;
        trainID = idCount++;
    }

    public void disembark()
    {
        //Remove passengers from list that need to get off at current station
    }

    public void embark()
    {
        //add passengers from station queue to passengersOnTrain list
    }

    public int getTrainID()
    {
        return trainID;
    }

    @Override
    public String toString()
    {
        return "Train " + trainID;
    }
}
