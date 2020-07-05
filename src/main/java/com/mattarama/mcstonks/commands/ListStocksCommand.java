package com.mattarama.mcstonks.commands;

import com.mattarama.mcstonks.stocks.StockManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListStocksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Lists all stocks
        sender.sendMessage(TextConstructor());
        return true;

    }



    /**
     * Constructs the stock report message, using all of the stocks
     */
    String TextConstructor() {

        //Return string;
        String message = "";

        //Initial Values
        message += "\n§6§l§uAvailable Stocks";

        for (int i = 0; i < StockManager.stocksList.size(); i++) {

            //Adds line for message
            message += "\n";

            //Adds line
            message += "§6§lName: §e" + StockManager.stocksList.get(i).name;
            message += "§6§l | ID: §e" + StockManager.stocksList.get(i).stockId;
            message += "§6§l | Value: §e" + StockManager.stocksList.get(i).value;

        }

        //Returns final message
        return message;

    }

}
