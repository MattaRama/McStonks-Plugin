package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.api.PlayerAPI;
import com.mattarama.mcstonks.player.MoneyManager;
import com.mattarama.mcstonks.player.PlayerStockManager;
import com.mattarama.mcstonks.stocks.StockManager;

import java.io.IOException;

public class Cmd$Me_Stock_Sell extends CommandBase {

    @Override
    public void Execute() throws IOException {

        //Checks for valid argument count
        if (values.size() != 2) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Checks if stock exists
        if (StockManager.GetStockById(values.get(0)) == null) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Checks if player has enough stocks
        int quantity = Math.abs(Integer.parseInt(values.get(1)));
        int cost = quantity * StockManager.GetStockById(values.get(0)).value;

        if (PlayerStockManager.GetStocks(PlayerAPI.GetUsernameByToken(token), values.get(0)) < quantity) {

            dataOut.writeUTF("[011]");
            return;

        }

        //Performs transaction
        MoneyManager.AddMoney(PlayerAPI.GetUsernameByToken(token), cost);
        PlayerStockManager.AddStocks(PlayerAPI.GetUsernameByToken(token), values.get(0), -1 * quantity);
        dataOut.writeUTF("[100]");

    }

}
