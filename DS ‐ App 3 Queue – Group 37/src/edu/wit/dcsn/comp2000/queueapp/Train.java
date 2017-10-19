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

    public void transferPassengers(Station currentStation, boolean isInbound)
    {
        disembark(currentStation);
        embark(currentStation, isInbound);
    }

    public void disembark(Station currentStation)
    {
        for (int i = 0; i < passengersOnTrain.size(); i++)
        {
            if (passengersOnTrain.get(i).getExitingStation() == currentStation)
            {
                passengersOnTrain.set(i, passengersOnTrain.get(passengersOnTrain.size()-1));
                passengersOnTrain.remove(passengersOnTrain.size()-1);
                i--;
            }
        }
    }

    public void embark(Station currentStation, boolean isInbound)
    {
        while(passengersOnTrain.size() < maxCapacity)
        {
            passengersOnTrain.add(currentStation.board(isInbound));
        }
    }

    public int getPositionOnTrack()
    {
        return positionOnTrack;
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
