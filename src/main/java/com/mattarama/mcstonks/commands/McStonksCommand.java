package com.mattarama.mcstonks.commands;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class McStonksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args[0].toLowerCase().equals("api")) {

            switch (args[1]) {

                case "newtoken":

                    PlayerAPI.AssignIDToPlayer(sender.getName());
                    sender.sendMessage("Your ID: " + McStonks.playerConfig.getString("players." + sender.getName() + ".api.usertoken"));

                    break;

            }

        }

        return true;

    }

}
