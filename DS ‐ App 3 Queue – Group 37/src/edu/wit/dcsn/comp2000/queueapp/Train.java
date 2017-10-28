package edu.wit.dcsn.comp2000.queueapp ;

import java.util.ArrayList ;

public class Train
{
    private ArrayList<Passenger> passengersOnTrain ;
    private int maxCapacity ;
    private int positionOnTrack ;
    private int trainID ;
	Direction direction;
    private static int idCount = 0 ;

    public Train( int positionOnTrack, Direction direction, int maxCapacity )
    {
        passengersOnTrain =		new ArrayList<>() ;
        this.positionOnTrack = 	positionOnTrack ;
        this.direction = direction;
        this.maxCapacity = 		maxCapacity ;
        trainID = idCount++ ;
    } // end Train

    public void transferPassengers(	Station currentStation,
    								boolean isInbound )
    {
        disembark( currentStation ) ;
        embark( currentStation, isInbound ) ;
    } // end transferPassengers

    public void disembark( Station currentStation )
    {
        for ( int i = 0 ; i < passengersOnTrain.size() ; i++ )
        {
            if ( passengersOnTrain.get( i ).getExitingStation() == currentStation )
            {
                passengersOnTrain.set( i, passengersOnTrain.get( passengersOnTrain.size() - 1 ) ) ;
                passengersOnTrain.remove( passengersOnTrain.size() - 1 ) ;
                i-- ;
            } // end if
        } // end for
    } // end disembark

    public void embark( Station currentStation,
    					boolean isInbound )
    {
        while( passengersOnTrain.size() < maxCapacity )
        {
            passengersOnTrain.add( currentStation.board( isInbound ) ) ;
        } // end while
    } // end embark

    public int getPositionOnTrack()
    {
        return positionOnTrack ;
    } // end getPositionOnTrack

    public int getTrainID()
    {
        return trainID ;
    } // end getTrainID

    @Override
    public String toString()
    {
        return "Train " + trainID ;
    } // end toString
}
