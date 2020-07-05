package com.mattarama.mcstonks.commands;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.player.MoneyManager;
import com.mattarama.mcstonks.player.PlayerStockManager;
import com.mattarama.mcstonks.stocks.Stock;
import com.mattarama.mcstonks.stocks.StockManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Use. The. Correct. Syntax. Or ask matt for help idgaf.");
            return true;
        }

        /*
        Breaks off into different sections
         */

        //Stock info command
        if (args[0].equals("info")) {

            if (args.length != 2) {

                sender.sendMessage("§4Please use correct syntax!");
                return true;

            }

            //Loops through each stock
            for (int i = 0; i < StockManager.stocksList.size(); i++) {

                //Prints out data and returns
                if (args[1].toLowerCase().equals(StockManager.stocksList.get(i).stockId)) {

                    //Constructs return message
                    Stock st = StockManager.stocksList.get(i);

                    String ret = "\n§6§lStock '§e" + st.name + "§6§l'";
                    ret += "\n§6§lStock ID: §e" + st.stockId;
                    ret += "\n§6§lStock Value: §e" + st.value;
                    ret += "\n§6§lVariability: §e" + st.updateVariability;

                    sender.sendMessage(ret);
                    return true;

                }


            }
            //Default failure message
            sender.sendMessage("§4No stock found with id '" + args[1] + "'");

            String stks = StockManager.stocksList.get(0).stockId;

            for (int y = 1; y < StockManager.stocksList.size(); y++) {
                stks += ", " + StockManager.stocksList.get(y).stockId;
            }

            sender.sendMessage("§4Available Stocks: " + stks);
            return true;
        }

        //Stock buy
        if (args[0].equals("buy")) {

            //Checks if the stock exists, executes if it does
            for (Stock s : StockManager.stocksList) {

                //Progresses for buying stock
                if (s.stockId.equals(args[1])) {

                    //Checks if the player has enough money
                    int quantity = Math.abs(Integer.parseInt(args[2]));
                    int cost = quantity * StockManager.GetStockById(args[1]).value;

                    if (cost > MoneyManager.GetMoney(sender.getName())) {
                        sender.sendMessage(ChatColor.RED + "Lol you broke, you cant afford this lmaooooo");
                        return true;
                    }

                    //Buys stock
                    MoneyManager.AddMoney(sender.getName(), cost * -1);
                    PlayerStockManager.AddStocks(sender.getName(), args[1], quantity);
                    sender.sendMessage("§6§lYou bought stocks in '§e" + StockManager.GetStockById(args[1]).name + "§6§l' for $§e" + cost);

                    return true;

                }

            }

            sender.sendMessage(ChatColor.RED + "Couldn't find a stock by that ID.");
            return true;

        }

        //Stock sell
        if (args[0].equals("sell")) {

            //Checks if the stock exists, executes if it does
            for (Stock s : StockManager.stocksList) {

                //Progresses for buying stock
                if (s.stockId.equals(args[1])) {

                    //Checks if the player has enough quantity
                    int quantity = Math.abs(Integer.parseInt(args[2]));
                    int cost = quantity * StockManager.GetStockById(args[1]).value;

                    if (quantity > PlayerStockManager.GetStocks(sender.getName(), StockManager.GetStockById(args[1]).stockId)) {
                        sender.sendMessage(ChatColor.RED + "You dont got that many stocks dawg, try something else");
                        return true;
                    }

                    //Sells stocks
                    MoneyManager.AddMoney(sender.getName(), cost);
                    PlayerStockManager.AddStocks(sender.getName(), args[1], -1 * quantity);
                    sender.sendMessage("§6§lYou sold stocks in '§e" + StockManager.GetStockById(args[1]).name + "§6§l' and got $§e" + cost);

                    return true;

                }

            }

            sender.sendMessage(ChatColor.RED + "Couldn't find a stock by that ID.");
            return true;

        }

        sender.sendMessage(ChatColor.RED + "Use. The. Correct. Syntax. Or ask matt for help idgaf.");
        return true;

    }

}
