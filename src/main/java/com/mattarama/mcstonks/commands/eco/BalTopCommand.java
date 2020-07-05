package com.mattarama.mcstonks.commands.eco;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.player.MoneyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class BalTopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Creates list of players
        ConfigurationSection sec = McStonks.me.playerConfig.getConfigurationSection("players");
        String[] players = new String[sec.getKeys(false).size()];

        int x = 0;
        for (String s : sec.getKeys(false)) {

            players[x] = s;
            x++;

        }

        //String[] players = (String[]) sec.getKeys(false).toArray();

        //Sorts list
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < players.length - 1; i++) {
                if (MoneyManager.GetMoney(players[i]) < MoneyManager.GetMoney(players[i + 1])) {
                    String temp;
                    temp = players[i];
                    players[i] = players[i + 1];
                    players[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        //Displays
        sender.sendMessage(Print(players));

        return true;

    }

    String Print(String[] players) {

        String str = "\n§6§lAll Balances: ";

        for (int i = 0; i < players.length; i++) {

            str += "\n§6§l" + (i + 1) + ".) §e" + players[i] + "§6§l has §e" + MoneyManager.GetMoney(players[i]);

        }

        return str;

    }

}
