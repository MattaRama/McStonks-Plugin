package com.mattarama.mcstonks.player;

import com.mattarama.mcstonks.McStonks;

public class PlayerStockManager {

    /**
     * Set the quantity of stocks that a player has
     *
     * @param username Username of the player
     * @param stockname Name of the stock
     * @param quantity Quantity of the stock to set to
     */
    public static void SetStocks(String username, String stockname, int quantity) {

        McStonks.playerConfig.set("players." + username + ".stocks." + stockname, quantity);
        McStonks.me.SavePlayerConfig();

    }

    /**
     * Gets the quantity of a particular stock that a player has
     *
     * @param username Username of the player
     * @param stockname Name of the stock
     * @return Quantity of the stock the player has
     */
    public static int GetStocks(String username, String stockname) {

        return McStonks.playerConfig.getInt("players." + username + ".stocks." + stockname);

    }

    /**
     * Add a quantity of stocks to a player
     *
     * @param username Username of the player
     * @param stockname Name of the stock
     * @param quantity Quantity of the stock to add
     */
    public static void AddStocks(String username, String stockname, int quantity) {

        SetStocks(username, stockname, GetStocks(username, stockname) + quantity);

    }

}
