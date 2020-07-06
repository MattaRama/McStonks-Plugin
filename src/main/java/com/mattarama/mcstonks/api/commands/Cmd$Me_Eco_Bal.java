package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.api.PlayerAPI;
import com.mattarama.mcstonks.player.MoneyManager;

import java.io.IOException;

public class Cmd$Me_Eco_Bal extends CommandBase {

    @Override
    public void Execute() throws IOException {

        dataOut.writeUTF(Integer.toString(MoneyManager.GetMoney(PlayerAPI.GetUsernameByToken(token))));

    }
}
