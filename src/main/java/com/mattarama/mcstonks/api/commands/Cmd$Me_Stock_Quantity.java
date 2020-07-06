package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.api.PlayerAPI;
import com.mattarama.mcstonks.player.PlayerStockManager;
import com.mattarama.mcstonks.stocks.Stock;
import com.mattarama.mcstonks.stocks.StockManager;

import java.io.IOException;

public class Cmd$Me_Stock_Quantity extends CommandBase {

    @Override
    public void Execute() throws IOException {

        //Checks for valid argument count
        if (values.size() != 1) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Checks if stock exists
        if (StockManager.GetStockById(values.get(0)) == null) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Returns quantity
        dataOut.writeUTF(Integer.toString(PlayerStockManager.GetStocks(PlayerAPI.GetUsernameByToken(token), values.get(0))));

    }
}
