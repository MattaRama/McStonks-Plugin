package com.mattarama.mcstonks.stocks;

import com.mattarama.mcstonks.McStonks;

import java.util.Random;

/**
 * Keeps track of individual stocks
 */
public class Stock {

    public String name;
    public String stockId;
    public int value;
    public int updateVariability;

    /**
     * Constructs a new stock
     *
     * @param name The display name of the stock
     * @param initValue The value of the stock on plugin initialization
     * @param stockId The id of the stock
     * @param updateVariability The amount a stock can drop or raise
     */
    public Stock(String name, int initValue, String stockId, int updateVariability) {

        //Stores arguments
        this.name = name;
        this.stockId = stockId;
        this.value = initValue;
        this.updateVariability = updateVariability;

    }

    /**
     * Changes the value of the stock, raising or lowering it
     */
    public void UpdateValue() {

        //Generates a random number to increment/decrement by
        Random rnd = new Random();
        int changeValue = rnd.nextInt(updateVariability + 1) - (updateVariability/2);

        //Updates value
        value += changeValue;

        //If value is below zero, it's set to 1 as a minimum
        if (value <= 0)
            value = 1;

        //Updates value in config
        McStonks.me.getConfig().set("stocks." + stockId + ".value", value);
        McStonks.me.saveConfig();

    }

}
