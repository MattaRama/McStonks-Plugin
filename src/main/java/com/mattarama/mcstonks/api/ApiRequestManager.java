package com.mattarama.mcstonks.api;

import com.mattarama.mcstonks.api.commands.*;
import com.mattarama.mcstonks.api.commands.CommandBase;

import java.util.Hashtable;

public class ApiRequestManager {

    //Hash Table
    public static Hashtable<String, CommandBase> commandIndex = new Hashtable<>();

    /**
     * Initializes Hash Table of Commands
     */
    public static void InitializeHashTable() {

        commandIndex.put("stock.list", new Cmd$Stock_List());

        commandIndex.put("stock.get.val", new Cmd$Stock_Get_Val());
        commandIndex.put("stock.get.name", new Cmd$Stock_Get_Name());
        commandIndex.put("stock.get.variability", new Cmd$Stock_Get_Variability());

        commandIndex.put("me.stock.quantity", new Cmd$Me_Stock_Quantity());
        commandIndex.put("me.stock.sell", new Cmd$Me_Stock_Sell());
        commandIndex.put("me.stock.buy", new Cmd$Me_Stock_Buy());

        commandIndex.put("me.eco.bal", new Cmd$Me_Eco_Bal());
        commandIndex.put("me.eco.pay", new Cmd$Me_Eco_Pay());

        commandIndex.put("me.name", new Cmd$Me_Name());

    }

}
