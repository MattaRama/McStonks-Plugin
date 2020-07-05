package com.mattarama.mcstonks.commands.eco;

import com.mattarama.mcstonks.player.MoneyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            //Grabs sender balance
            sender.sendMessage("§6§lBalance: §e" + MoneyManager.GetMoney(sender.getName()));
            return true;
        }
        //Grabs sender balance
        sender.sendMessage("§6§lBalance of §e" + args[0] + "§6§l: §e" + MoneyManager.GetMoney(args[0]));

        return true;

    }

}
