package edu.okstate.executables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import edu.okstate.entities.Log;
import edu.okstate.frames.PrHeaderFrame;


public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException{
    	new Log();
		Log.printLog().log(Level.INFO, "Application Launched Successfully!!!");
    	PrHeaderFrame.prHeaderMain();
    }
}
