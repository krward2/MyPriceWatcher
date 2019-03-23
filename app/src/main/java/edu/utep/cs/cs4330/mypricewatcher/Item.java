package edu.utep.cs.cs4330.mypricewatcher;

/**
 * @author Kenneth Ward
 * @version 1.0
 * @since 2019-2-20
 */

/**
 * Item class stores the name, url, initial price, and current price of an
 * item and provides methods for accessing these fields and for computing
 * the percent difference between the initial and current price.
 */
public class Item {
    private String name;
    private String url;
    private double priceInitial;
    private double priceCurrent;

    /**
     *Item Constructor
     * @param name The name of an item to be monitored.
     * @param url The url of the item to be monitored.
     */
    public Item(String name, String url) {
        this.name = name;
        this.url = url;
        this.priceInitial = PriceFinder.updatePrice(this.url);
        this.priceCurrent = this.priceInitial;
    }

    /**
     * Calls the PriceFinder class to search for the current price of the item
     * from a given url, and updates the "currentPrice" field.
     */
    public void updatePrice(){
        priceCurrent = PriceFinder.updatePrice(url);
    }

    /**
     * Computes the percent difference between initial and current price
     * @return Returns the calculated percent difference between the intial and current
     * prices
     */
    public double getPriceChange() {
        double difference = priceCurrent - priceInitial;
        double percentChange = difference / priceInitial;
        return percentChange*100;
    }


    /**
     * Getter method for "priceCurrent" field
     * @return Returns the current price of the item
     */
    public double getPriceCurrent(){
        return priceCurrent;
    }

    /**
     * Getter method for "priceInitial" field
     * @return Returns the initial price of the item
     */
    public double getPriceInitial() {
        return priceInitial;
    }

    /**
     * Getter method for "name" field
     * @return Returns the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the "url" field
     * @return Returns the url of the item
     */
    public String getUrl() {
        return url;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUrl(String url){
        this.url = url;
    }
}

