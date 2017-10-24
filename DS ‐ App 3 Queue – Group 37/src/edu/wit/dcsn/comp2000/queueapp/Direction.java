/**
 * A general purpose enumeration for representing the direction an entity is moving along a Route.
 * 
 * <p>Note: You may use this class, with or without modification, in your Comp 2000, 
 * Queue application/Train Simulation solution.  You must retain all authorship comments.  If you
 * modify this, add your authorship to mine.
 */
package edu.wit.dcsn.comp2000.queueapp;

/**
 * @author David M Rosenberg
 * @version	1.0.0	initial version
 */
public enum Direction
    {
    /**
     * default value
     */
    NOT_SPECIFIED       ( "Not specified" ),
    /**
     * direction does not apply to this entity
     */
    NOT_APPLICABLE      ( "Not applicable" ),
    /**
     * the entity is not movable
     */
    STATIONARY          ( "Stationary" ),
    /**
     * the entity is associated with moving toward a hub as determined by Route; typically applies to end-to-end Routes
     */
    INBOUND             ( "Inbound" ),
    /**
     * the entity is associated with moving away from a hub as determined by Route; typically applies to end-to-end Routes
     */
    OUTBOUND            ( "Outbound" ),
    /**
     * the entity is associated with moving in a clockwise direction as determined by Route
     * - typically applies to circular Routes
     */
    CLOCKWISE           ( "Clockwise" ),
    /**
     * the entity is associated with moving in a counter-clockwise direction as determined by Route 
     * - typically applies to circular Routes
     */
    COUNTER_CLOCKWISE   ( "Counter-clockwise" )
    ;
    
    
    // instance variables
    
    private final String displayText ;      // user-friendly version for display
    
    
    /**
     * Sets the corresponding user-friendly text for display
     * @param textToDisplay the user-friendly text
     */
    private Direction( String textToDisplay )
        {
        displayText =               textToDisplay ;
        }

    
    /**
     * @return this Direction's display name
     */
    public String getDisplayText()
        {
        return displayText ;
        }
    
    
    // public utility methods
    
    /**
     * Parses a string representing a Direction.  The string is matched against the display text
     * for each member of the enumeration in order of declaration.  The match is case-insensitive
     * and is restricted to a substring of the display text starting with the first character and
     * ending with the number of characters specified in the provided string.
     * <p>NOTE: Substring matching will succeed on the first match, which for this enumeration will
     * not behave correctly when only the first character/a single character string is provided.
     * @param directionText the text to parse
     * @return if successfully parsed, the corresponding enumeration element; otherwise null
     */
    public static Direction parse( String directionText )
        {
        Direction parsedDirection 			= null ;
        
        for( Direction aDirection : Direction.values() )
        	{
        	int comparisonLength			= Math.min( directionText.length(),
        	                    			            aDirection.displayText.length() ) ;
        	if( directionText.equalsIgnoreCase( 
        	                      aDirection.displayText.substring( 0, comparisonLength ) ) )
        		{
        		parsedDirection				= aDirection ;
        		
        		break ;							// found one so done
        		}
        	}
        
        return parsedDirection ;
        }


    @Override
    public String toString()
        {
        return displayText ;
        }
    
    
    /**
     * @param args -unused-
     */
    public static void main( String[] args )
        {
        // display column headers
        System.out.printf( "%-5s %-20s %-20s %-20s %-20s%n", 
                            "#",
                            "Name",
                            "Direction",
                            "Display Text",
                            "Parsed"
                            );
        
        // display each element of the enumeration
        for ( Direction aDirection : Direction.values() )
            {
            System.out.printf( "%-5d %-20s %-20s %-20s %-20s%n", 
                               aDirection.ordinal(),
                               aDirection.name(),
                               aDirection,
                               aDirection.displayText,
                               Direction.parse( aDirection.displayText )
                               );
            }
        
        // test the parser
        System.out.println() ;
        System.out.println( "parse(\"C\") returns " + Direction.parse( "C" ) ) ;
        System.out.println( "parse(\"Co\") returns " + Direction.parse( "Co" ) ) ;
        System.out.println( "parse(\"Not Specifically\") returns " + Direction.parse( "Not Specifically" ) ) ;
        System.out.println( "parse(\"nonesuch\") returns " + Direction.parse( "nonesuch" ) ) ;
        System.out.println( "parse(\"a very long string which may or may not match\") returns " +
        				Direction.parse( "a very long string which may or may not match" )) ;
            
        }

    }
