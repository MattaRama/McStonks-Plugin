package com.mattarama.mcstonks.commands.eco;

import com.mattarama.mcstonks.player.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Checks if payment can execute
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Not... NOT ENOUGH ARGUMENTS YOU LOSER. TRY IT WITH NAME AND PLAYER!!!!!!");
            return true;
        }

        //Checks if player has enough money to pay
        if (Math.abs(Integer.parseInt(args[1])) > MoneyManager.GetMoney(sender.getName())) {

            sender.sendMessage(ChatColor.RED + "FUCK YOU! YOU DONT HAVE ENOUGH MONEY!!!!");
            return true;

        }

        //Pays other player
        MoneyManager.AddMoney(sender.getName(), -1 * Math.abs(Integer.parseInt(args[1]))); // Takes away this player's money
        MoneyManager.AddMoney(args[0], Math.abs(Integer.parseInt(args[1]))); // Gives this player money

        //Sends payment notifications
        sender.sendMessage("§6§lYou paid §e" + args[0] + " §6§l$§e" + args[1]);

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (p.getName().equals(args[0])) {

                p.sendMessage("§6§lYou received $§e" + args[1] + " §6§lfrom §e" + sender.getName());
                return true;

            }

        }

        return true;

    }

}
