package com.mattarama.mcstonks.commands.eco;

import com.mattarama.mcstonks.player.MoneyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EcoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Protects against 0 arguments
        if (args.length == 0) {

            sender.sendMessage(ChatColor.RED + "Cmon man do a valid command!");
            return true;

        }

        //Sets balance
        if (args[0].equals("set")) {

            try {
                MoneyManager.SetMoney(args[1], Integer.parseInt(args[2]));
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "YOU BITCH YOU TRIED TO MAKE ME CRASH");
                return true;
            }

            sender.sendMessage(ChatColor.GOLD + "§7§lThe balance of '§e" + args[1] + "§7§l' was set to §e" + args[2]);

            return true;

        }

        //Adds balance
        if (args[0].equals("add")) {

            MoneyManager.AddMoney(args[1], Integer.parseInt(args[2]));
            sender.sendMessage(ChatColor.GOLD + "§7§lThe balance of '§e" + args[1] + "§7§l' was appended by §e" + args[2]);
            return true;

        }

        sender.sendMessage(ChatColor.RED + "Cmon man do a valid command!");
        return true;

    }

}
