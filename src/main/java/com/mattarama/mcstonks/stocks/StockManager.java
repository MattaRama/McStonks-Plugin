package com.mattarama.mcstonks.stocks;

import com.mattarama.mcstonks.McStonks;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages all stocks
 */
public class StockManager {

    public static List<Stock> stocksList = new ArrayList<>(); // List of every stock

    /**
     * Initializes all stocks and their values
     */
    public static void InitializeStocks() {

        //Initializes stocks from config
        ConfigurationSection stocksConfig = McStonks.me.getConfig().getConfigurationSection("stocks");

        for (String s : stocksConfig.getKeys(false)) {

            stocksList.add(new Stock(
                    stocksConfig.getString(s + ".name"),
                    stocksConfig.getInt(s + ".value"),
                    s,
                    stocksConfig.getInt(s + ".variability")
            ));

        }

        //stocksList.add(new Stock("Vacuum Corp", McStonks.me.getConfig().getInt("stocks.vac"), "vac", 30));
        //stocksList.add(new Stock("Matty Fatty Industries", McStonks.me.getConfig().getInt("stocks.mat"), "mat", 10));
        //stocksList.add(new Stock("Mexican Wall Jumpers Inc.", McStonks.me.getConfig().getInt("stocks.mwj"), "mwj", 4));

    }

    /**
     * Initializes stock update timer
     */
    public static void InitializeUpdater() {

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                //Updates stocks
                UpdateStocks();

            }
        }, 1*60*1000, 1*60*1000);

    }

    /**
     * Sends an update to each stock, causing the price to fluctuate
     */
    public static void UpdateStocks() {

        //For every stock
        for (Stock s : stocksList) {

            //Sends a randomize command to the stock
            s.UpdateValue();

        }

    }

    /**
     * Gets a stock and returns it based on it's ID
     *
     * @param ID The ID of the stock
     * @return The stock with the matching ID
     */
    public static Stock GetStockById(String ID) {

        for (Stock s : stocksList) {

            if (s.stockId.equals(ID)) {
                return s;
            }

        }

        return null;

    }

}
