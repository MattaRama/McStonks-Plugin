package com.mattarama.mcstonks;

import com.mattarama.mcstonks.commands.StockCommand;
import com.mattarama.mcstonks.commands.eco.BalTopCommand;
import com.mattarama.mcstonks.commands.eco.BalanceCommand;
import com.mattarama.mcstonks.commands.eco.EcoCommand;
import com.mattarama.mcstonks.commands.ListStocksCommand;
import com.mattarama.mcstonks.commands.MyStocksCommand;
import com.mattarama.mcstonks.commands.eco.PayCommand;
import com.mattarama.mcstonks.stocks.StockManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class McStonks extends JavaPlugin {

    public static McStonks me;

    public static FileConfiguration playerConfig;
    public static File playerConfigFile;

    @Override
    public void onEnable() {

        //Stores me as a static instance of myself!
        me = this;

        //Sets available commands
        this.getCommand("stocks").setExecutor(new ListStocksCommand());
        this.getCommand("mystocks").setExecutor(new MyStocksCommand());
        this.getCommand("stock").setExecutor(new StockCommand());

        this.getCommand("eco").setExecutor(new EcoCommand());
        this.getCommand("pay").setExecutor(new PayCommand());
        this.getCommand("baltop").setExecutor(new BalTopCommand());
        this.getCommand("balance").setExecutor(new BalanceCommand());

        //Initializes Configs
        InitializeConfig();

        //Initializes Stocks
        StockManager.InitializeStocks();

        //Initializes Stock Updater
        StockManager.InitializeUpdater();

        getLogger().info("McStonks Initialized Successfully.");

    }

    /**
     * Initializes all config files for the plugin
     */
    void InitializeConfig() {

        //Initializes main config
        saveConfig();

        getConfig().addDefault("stocks.vac", 420);
        getConfig().addDefault("stocks.mat", 69);
        getConfig().addDefault("stocks.mwj", 10);

        saveDefaultConfig();

        //Initializes player config
        playerConfigFile = new File(getDataFolder() + "/players.yml");
        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);

    }

    public void SavePlayerConfig() {

        try {
            playerConfig.save(playerConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
