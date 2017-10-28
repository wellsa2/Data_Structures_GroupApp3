package edu.wit.dcsn.comp2000.queueapp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Creates a logger class
 */
public class Logger
{
    private PrintWriter logWriter;

    /**
     * Opens a log file
     * @throws FileNotFoundException thrown if Log file can't be found
     * @throws java.io.UnsupportedEncodingException thrown if "utf-8" isn't valid encoding
     */
    public Logger() throws FileNotFoundException, java.io.UnsupportedEncodingException
    {

        String filePath = System.getProperty("user.dir") + "/DS ‐ App 3 Queue – Group 37/src/edu/wit/dcsn/comp2000/queueapp/Log.txt";

        File logFile = new File(filePath);

        logWriter = new PrintWriter(logFile,"utf-8");
    }

    /**
     * Prints to the log
     * @param s string to be printed
     */
    public void print(String s)
    {
        logWriter.println(s);
    }

    /**
     * closes the log
     */
    public void close()
    {
        logWriter.close();
    }

    /**
     * Tester for Logger
     * @param args args for main
     * @throws FileNotFoundException thrown if file isn't found
     * @throws java.io.UnsupportedEncodingException thrown if encoding isn't accepted
     */
    public static void main(String[] args) throws FileNotFoundException, java.io.UnsupportedEncodingException
    {
        System.out.println("Testing Logger");
        System.out.println("\n----------\nTesting Constructors");
        Logger logger = new Logger();
        System.out.println("\n----------\nTesting Print");
        logger.print("HELLO");
        logger.print("BYE");
        System.out.println("\n----------\nTesting Close");
        logger.close();
        System.out.print("end");
        System.out.println("\n----------\nTesting Constructors Overwrite");
        Logger newlogger = new Logger();
        newlogger.print("hi");
        newlogger.close();
    }
}
