package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.stocks.Stock;
import com.mattarama.mcstonks.stocks.StockManager;

import java.io.IOException;

public class Cmd$Stock_List extends CommandBase {

    @Override
    public void Execute() throws IOException {

        //Empty string
        String stocks = "";

        //Constructs a list of all available stocks
        for (Stock s : StockManager.stocksList) {

            stocks += s.stockId + ",";

        }

        //Removes last comma
        stocks = stocks.substring(0, stocks.length() - 1);

        //Outputs results to data stream
        dataOut.writeUTF(stocks);

    }

}
