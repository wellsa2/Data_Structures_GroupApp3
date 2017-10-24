package edu.wit.dcsn.comp2000.queueapp;
/**
 * A class that specifies the properties and functions of a passenger. 
 */
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

    
    /**
     * gets a specific passenger's ID.
     * @return passengerID
     */
    public int getPassengerID()
    {
        return passengerID;
    }// end getPassengerID

    
    /**
     * gets a specific passenger's entering station.
     * @return enteringStation
     */
    public Station getEnteringStation()
    {
        return enteringStation;
    }// end getEnteringStation

    
    /**
     * gets a specific passenger's exiting station.
     * @return exitingStation
     */
    public Station getExitingStation()
    {
        return exitingStation;
    }// end getExitingStation

    
    /**
     * gets a specific passenger's entry time.
     * @return entryTime
     */
    public int getEntryTime()
    {
        return entryTime;
    }// end getEntryTime

    
    /**
     * Converts a passenger instance to a string.
     * @return passenger as string
     */
    @Override
    public String toString()
    {
        return "Passenger " + passengerID;
    }// end toString
}// end Passenger
