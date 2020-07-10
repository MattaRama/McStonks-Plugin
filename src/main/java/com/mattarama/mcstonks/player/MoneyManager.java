package com.mattarama.mcstonks.player;

import com.mattarama.mcstonks.McStonks;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

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

        int money = McStonks.playerConfig.getInt("players." + username + ".money");

        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "scoreboard players set " + username + " money " + money);
        return money;

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

        return GetMoney(username);

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

        int moneyFinal = GetMoney(username);

        return moneyFinal;

    }

}
