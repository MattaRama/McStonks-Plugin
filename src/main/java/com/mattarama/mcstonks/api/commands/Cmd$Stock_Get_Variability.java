package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.stocks.Stock;
import com.mattarama.mcstonks.stocks.StockManager;

import java.io.IOException;

public class Cmd$Stock_Get_Variability extends CommandBase {

    @Override
    public void Execute() throws IOException {

        //Checks for valid argument count
        if (values.size() != 1) {

            dataOut.writeUTF("[004]");
            return;

        }

        //Returns if possible
        Stock s = StockManager.GetStockById(values.get(0));

        if (s == null) {

            dataOut.writeUTF("[004]");
            return;

        }

        dataOut.writeUTF(Integer.toString(s.updateVariability));

    }

}
