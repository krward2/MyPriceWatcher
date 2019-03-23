package edu.utep.cs.cs4330.mypricewatcher;

/**
 * @author Kenneth Ward
 * @version 1.0
 * @since 2019-2-20
 */

import java.util.Random;

/**
 * PriceFinder class provides a method (and will implement an interface in the future)
 * for finding the current price of an item at a specified url
 *
 */
public class PriceFinder{

    /**
     * Finds the current price from the internet
     * @param url The url of an item
     * @return Returns the price as given by the web page(RNG)
     */
    public static double updatePrice(String url){
        return (new Random().nextDouble()*1500);
    }

}

