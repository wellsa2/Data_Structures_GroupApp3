package edu.wit.dcsn.comp2000.queueapp ;

/* COMP 2000 - 03
 * Queue Application 3
 * Group 37
 * AUTHOR TBD
 * 10/24/2017
 */

/**
 * A class that specifies the properties and functions of a passenger. 
 */
public class Passenger
{
    private Station enteringStation = new Station() ;
    private Station exitingStation = new Station() ;
    private int passengerID ;
    private int entryTime ;
    private static int idCount = 0 ;
    private boolean initialized = false ;

    public Passenger( 	Station enteringStation,
    					Station exitingStation,
    					int entryTime )
    {
        if (enteringStation == null || exitingStation == null )
        {
            initialized = false ;
        }
        else
        {
            this.enteringStation = enteringStation ;
            this.exitingStation = exitingStation ;
            this.entryTime = entryTime ;
            this.passengerID = idCount++ ;
            initialized = true ;
        }
    }

    
    /**
     * gets a specific passenger's ID.
     * @return passengerID
     */
    public int getPassengerID()
    {
        checkInitialization() ;
        return passengerID ;
    }// end getPassengerID

    
    /**
     * gets a specific passenger's entering station.
     * @return enteringStation
     */
    public Station getEnteringStation()
    {
        checkInitialization() ;
        return enteringStation ;
    }// end getEnteringStation

    
    /**
     * gets a specific passenger's exiting station.
     * @return exitingStation
     */
    public Station getExitingStation()
    {
        checkInitialization() ;
        return exitingStation ;
    }// end getExitingStation

    
    /**
     * gets a specific passenger's entry time.
     * @return entryTime
     */
    public int getEntryTime()
    {
        checkInitialization() ;
        return entryTime ;
    }// end getEntryTime

    
    /**
     * Converts a passenger instance to a string.
     * @return passenger as string
     */
    @Override
    public String toString()
    {
        checkInitialization() ;
        return "Passenger " + passengerID ;
    }// end toString


    /**
     * ensures passenger has been properly initialized
     */
    private void checkInitialization()
    {
        if ( !initialized )
        {
            throw new SecurityException( "Passenger is not properly initialized." ) ;
        } // end checkInitialization
    } // end checkInitialization


    public static void main( String[] args )
    {
        System.out.println( "\n----------Testing Passenger----------\n" ) ;

        testConstructors() ;

        testPublicMethods() ;
    }


    private static void testConstructors()
    {
        System.out.println( "\n----------Testing Constructors----------\n" ) ;
        Passenger passenger0 = new Passenger( new Station(), new Station( 1 ), 0 ) ;
        passenger0.checkInitialization() ;
        Passenger passenger1 = new Passenger( null, null, 0 ) ;
        try
        {
            passenger1.checkInitialization() ;
        }
        catch ( SecurityException e )
        {
            System.out.println( e.toString() ) ;
        }

    }


    private static void testPublicMethods()
    {
        System.out.println( "\n----------Testing Public Methods----------\n" ) ;
        Passenger passenger0 = new Passenger( new Station(), new Station( 1 ), 0 ) ;
        System.out.println( passenger0.getPassengerID() ) ;
        System.out.println( passenger0.getEnteringStation() ) ;
        System.out.println( passenger0.getExitingStation() ) ;
        System.out.println( passenger0.getEntryTime() ) ;
        System.out.println( passenger0.toString() ) ;

    }
}// end Passenger
