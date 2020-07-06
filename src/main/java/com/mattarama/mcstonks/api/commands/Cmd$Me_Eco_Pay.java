package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.api.PlayerAPI;
import com.mattarama.mcstonks.player.MoneyManager;
import com.mattarama.mcstonks.stocks.StockManager;

import java.io.IOException;

public class Cmd$Me_Eco_Pay extends CommandBase {

    @Override
    public void Execute() throws IOException {

        //Checks for valid argument count
        if (values.size() != 2) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Performs transaction
        MoneyManager.AddMoney(PlayerAPI.GetUsernameByToken(token), -1 * Integer.parseInt(values.get(1)));
        MoneyManager.AddMoney(values.get(0), Integer.parseInt(values.get(1)));

        //Returns OK code
        dataOut.writeUTF("[100]");

    }
}
