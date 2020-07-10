package com.mattarama.mcstonks;

import com.mattarama.mcstonks.api.ApiRequestManager;
import com.mattarama.mcstonks.api.ApiServer;
import com.mattarama.mcstonks.commands.McStonksCommand;
import com.mattarama.mcstonks.commands.stocks.StockCommand;
import com.mattarama.mcstonks.commands.eco.BalTopCommand;
import com.mattarama.mcstonks.commands.eco.BalanceCommand;
import com.mattarama.mcstonks.commands.eco.EcoCommand;
import com.mattarama.mcstonks.commands.stocks.ListStocksCommand;
import com.mattarama.mcstonks.commands.stocks.MyStocksCommand;
import com.mattarama.mcstonks.commands.eco.PayCommand;
import com.mattarama.mcstonks.stocks.StockManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class McStonks extends JavaPlugin {

    public static McStonks me;

    public static FileConfiguration playerConfig;
    public static File playerConfigFile;

    static ApiServer api;

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

        this.getCommand("mcstonks").setExecutor(new McStonksCommand());

        //Initializes Scoreboard
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
                "scoreboard objectives add money dummy {\"text\":\"Aussie Bucks\",\"color\":\"red\"}");

        //Initializes Configs
        InitializeConfig();

        //Initializes Stocks
        StockManager.InitializeStocks();

        //Initializes Stock Updater
        StockManager.InitializeUpdater();

        //Initializes API
        ApiRequestManager.InitializeHashTable();
        try {
            api = new ApiServer(565);
            api.Start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLogger().info("McStonks Initialized Successfully.");

    }

    /**
     * Initializes all config files for the plugin
     */
    void InitializeConfig() {

        //Initializes main config
        saveConfig();

        getConfig().addDefault("stocks.vac.value", 420);
        getConfig().addDefault("stocks.vac.name", "Vacuum Corp");
        getConfig().addDefault("stocks.vac.variability", 30);

        getConfig().addDefault("stocks.mat.value", 69);
        getConfig().addDefault("stocks.mat.name", "Matty Fatty Industries");
        getConfig().addDefault("stocks.mat.variability", 10);

        getConfig().addDefault("stocks.mwj.value", 10);
        getConfig().addDefault("stocks.mwj.name", "Mexican Wall Jumpers Inc.");
        getConfig().addDefault("stocks.mwj.variability", 3);

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
        api.Interrupt();

        try {
            playerConfig.save(playerConfigFile);
            saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
