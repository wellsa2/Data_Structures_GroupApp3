package edu.wit.dcsn.comp2000.queueapp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger
{
    private PrintWriter logWriter;

    public Logger() throws FileNotFoundException, java.io.UnsupportedEncodingException
    {

        String filePath = System.getProperty("user.dir") + "/DS ‐ App 3 Queue – Group 37/src/edu/wit/dcsn/comp2000/queueapp/Log.txt";

        File logFile = new File(filePath);

        logWriter = new PrintWriter(logFile,"utf-8");
    }

    public void print(String s)
    {
        logWriter.println(s);
    }

    public void close()
    {
        logWriter.close();
    }

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
