package edu.okstate.executables;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.okstate.frames.PrHeaderFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        System.out.println( "---------------- WELCOME!!! ------------------" );
        
        
        //AdvancedDb2ExcelExporter2 a = new AdvancedDb2ExcelExporter2();
        //a.export(17);
        PrHeaderFrame.prHeaderMain();
        //10.227.249.248
    }
}
