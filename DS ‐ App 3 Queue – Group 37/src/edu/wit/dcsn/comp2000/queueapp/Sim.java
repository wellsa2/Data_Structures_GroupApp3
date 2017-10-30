package edu.wit.dcsn.comp2000.queueapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Sim
{
    private static Random random = new Random();
    private static Configuration configuration;
    private static String logFile;
    private static Logger logger = Logger.getLogger(Sim.class.getName());
    private static FileHandler fileHandler;


    public static void main(String[] args) throws FileNotFoundException
    {
        configuration = new Configuration();
        random.setSeed(configuration.getSeed());
        logFile = System.getProperty("user.dir") + "/DS ‐ App 3 Queue – Group 37/TrainSimulation.log";
        initializeLog();

        TrainRoute trainRoute = getTrainRoute();

        int runtime = configuration.getTicks();
        System.out.println(runtime);

        for ( int ticker = 0; ticker < runtime; ticker++)
        {
            logState(trainRoute, true, ticker);
            System.out.printf("%n---------------%nRunning tick %d", ticker);
            addPassengers(trainRoute, ticker);
            System.out.printf("%nTransferring Passengers");
            logger.info(trainRoute.determineArrivals(ticker));
            System.out.printf("%nMoving trains forward");
            trainRoute.moveTrains();
            logState(trainRoute, false, ticker);
        }
    }

    private static ArrayList<Station> getStations()
    {
        ArrayList<Station> stations = new ArrayList<>();
        for (int location : configuration.getStations())
        {
            stations.add(new Station(location));
        }
        return stations;
    }

    private static TrainRoute getTrainRoute()
    {

        TrainRoute trainRoute = new TrainRoute(configuration.getRoute().length);

        for (Station station : getStations())
        {
            trainRoute.addStation(station);
        }

        //Train needs position int and capacity int
        for (Configuration.TrainSpec trainSpec : configuration.getTrains())
        {
            Train train = new Train(trainSpec.location, trainSpec.capacity);
            if (trainSpec.direction.toString() == "Inbound")
            {
                trainRoute.addInboundTrain(train);
            }
            else
            {
                trainRoute.addOutboundTrain(train);
            }
        }


        return trainRoute;
    }


    private static void addPassengers(TrainRoute trainRoute, int enteringTime)
    {

        //Passenger takes entering station Station exiting station Station and entry time int

        Configuration.PairedLimit initialPairedLimit = configuration.getPassengers()[enteringTime == 0 ? 0: 1];
        int minimum = initialPairedLimit.minimum;
        int maximum = initialPairedLimit.maximum;

        int numberOfPassengers = minimum + random.nextInt(maximum - minimum + 1);

        while (numberOfPassengers > 0)
        {

            int randomStartingLocation = random.nextInt(trainRoute.getNumberOfStations());
            int randomEndingLocation = randomStartingLocation;
            while (randomEndingLocation == randomStartingLocation)
            {
                randomEndingLocation = random.nextInt(trainRoute.getNumberOfStations());
            }

            Passenger newPassenger = new Passenger(trainRoute.getStation(randomStartingLocation), trainRoute.getStation(randomEndingLocation), enteringTime);
            trainRoute.getStation(randomStartingLocation).addPassenger(newPassenger);

            numberOfPassengers--;
        }
    }


    private static void initializeLog()
    {
        try
        {
            logger = Logger.getLogger(Sim.class.getName());
            logger.setUseParentHandlers(false);
            fileHandler = new FileHandler(logFile, Integer.MAX_VALUE, 1, false);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        }
        catch(IOException ioException)
        {
            System.out.println("ERROR! Log Not Found!");
        }
    }


    private static void logState(TrainRoute trainRoute, boolean isStart, int tick)
    {
        logger.info(getHeader(tick, isStart));
        logger.info(getStationStates(trainRoute));
        logger.info(getTrainStates(trainRoute));

    }

    private static String getHeader(int tick, boolean isStart)
    {
        return String.format("%n--------------------------------%n%s of Tick %d%n", isStart ? "Start" : "End", tick);
    }

    private static String getStationStates(TrainRoute trainRoute)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < trainRoute.getNumberOfStations(); i++)
        {
            stringBuilder.append(String.format("%n     Station %d at index %d%n", trainRoute.getStation(i).getStationID(), trainRoute.getStation(i).getPositionOnTrack()));
            stringBuilder.append(String.format("          Inbound:%n"));
            stringBuilder.append(String.format("               %d Passengers%n", trainRoute.getStation(i).getNumberOfInboundPassengers()));
            stringBuilder.append(String.format("          Outbound:%n"));
            stringBuilder.append(String.format("               %d Passengers%n", trainRoute.getStation(i).getNumberOfOutboundPassengers()));
        }
        return stringBuilder.toString();
    }


    private static String getTrainStates(TrainRoute trainRoute)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%n     Inbound Trains:%n"));
        for (int i = 0; i < trainRoute.getNumberOfInboundTrains(); i++)
        {
            stringBuilder.append(String.format("          Train %d at index %d:%n", trainRoute.getInboundTrains(i).getTrainID(), trainRoute.getInboundTrains(i).getPositionOnTrack()));
            for (int j = 0; j < trainRoute.getInboundTrains(i).numberOfPassengers(); j++)
            {
                stringBuilder.append(String.format("               Passenger %d%n", trainRoute.getInboundTrains(i).getPassenger(j).getPassengerID()));
            }
        }

        stringBuilder.append(String.format("%n     Outbound Trains:%n"));
        for (int i = 0; i < trainRoute.getNumberOfOutboundTrains(); i++)
        {
            stringBuilder.append(String.format("          Train %d at index %d:%n", trainRoute.getOutboundTrains(i).getTrainID(), trainRoute.getOutboundTrains(i).getPositionOnTrack()));
            for (int j = 0; j < trainRoute.getOutboundTrains(i).numberOfPassengers(); j++)
            {
                stringBuilder.append(String.format("               Passenger %d%n", trainRoute.getOutboundTrains(i).getPassenger(j).getPassengerID()));
            }
        }

        return stringBuilder.toString();
    }

}
//---------------------------
//Starting Tick #
//  Station 1:
//      Inbound:
//          inbound pass ID
//      Outbound:
//          outbound pass ID
//  Station 2:
//  ...
//  Train 1:
//  ...
// RUNNING SIMULATION
//  Pass ID arrived at Station ID in # ticks
//  ...
//Ending Tick #
// ...
