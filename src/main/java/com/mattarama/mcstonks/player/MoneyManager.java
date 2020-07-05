package com.mattarama.mcstonks.player;

import com.mattarama.mcstonks.McStonks;

/**
 * Manages money transactions
 */
public class MoneyManager {

    /**
     * Gets the amount of money a player has
     *
     * @param username Username of the player
     * @return The monetary value of the player
     */
    public static int GetMoney(String username) {

        return McStonks.playerConfig.getInt("players." + username + ".money");

    }

    /**
     * Sets a player to a specific balance
     *
     * @param username Username of the player
     * @param quantity The monetary value to set the player to
     * @return The monetary value of the player after the operation (should match quantity)
     */
    public static int SetMoney(String username, int quantity) {

        McStonks.playerConfig.set("players." + username + ".money", quantity);

        McStonks.me.SavePlayerConfig();

        return McStonks.playerConfig.getInt("players." + username + ".money");

    }

    /**
     * Adds to a player's balance
     *
     * @param username Username of the player
     * @param quantity The monetary value to add to the player
     * @return The amount of mooney the player has
     */
    public static int AddMoney(String username, int quantity) {

        int current = GetMoney(username);
        current += quantity;
        SetMoney(username, current);
        return GetMoney(username);

    }

}
