package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;

public class TrainRoute
{
    private ArrayList<Train> inboundTrains = new ArrayList<>();
    private ArrayList<Train> outboundTrains = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();
    private int routeLength;

    public TrainRoute(int routeLength)
    {
        this.routeLength = routeLength;
    }

    //adds station to index
    public void addStation(Station station)
    {
        stations.add(station);
    }

    //adds train to first index
    public void addInboundTrain(Train train)
    {
        addInboundTrain(0, train);
    }

    //adds train to specific index
    public void addInboundTrain(int positionOnTrack, Train train)
    {
        inboundTrains.add(positionOnTrack, train);
    }

    //removes train at end of list
    public void removeInboundTrain()
    {
        removeInboundTrain(routeLength-1);
    }

    //removes train at index
    public void removeInboundTrain(int positionOnTrack)
    {
        inboundTrains.remove(positionOnTrack);
    }

    public void addOutboundTrain(Train train)
    {
        addOutboundTrain(0, train);
    }

    public void addOutboundTrain(int positionOnTrack, Train train)
    {
        outboundTrains.add(positionOnTrack, train);
    }

    public void removeOutboundTrain()
    {
        removeOutboundTrain(routeLength-1);
    }

    public void removeOutboundTrain(int positionOnTrack)
    {
        outboundTrains.remove(positionOnTrack);
    }
}
