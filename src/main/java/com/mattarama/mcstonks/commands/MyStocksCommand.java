package com.mattarama.mcstonks.commands;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.player.PlayerStockManager;
import com.mattarama.mcstonks.stocks.StockManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class MyStocksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Creates a collection of a player's stocks
        ConfigurationSection player = McStonks.playerConfig.getConfigurationSection("players." + sender.getName() + ".stocks");

        //Empty string and formatting
        String str = "\n§6§lYour Stocks:";

        //Gets all stocks
        for (String s : player.getKeys(false)) {

            str += "\n§6Name: §e" + StockManager.GetStockById(s).name + "§6 | Quantity: §e" + PlayerStockManager.GetStocks(sender.getName(), s) + " §6| Value: §e" + (PlayerStockManager.GetStocks(sender.getName(), s) * StockManager.GetStockById(s).value);

        }

        sender.sendMessage(str);

        return true;

    }

}
