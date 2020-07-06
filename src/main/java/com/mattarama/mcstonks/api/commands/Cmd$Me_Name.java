package com.mattarama.mcstonks.api.commands;

import com.mattarama.mcstonks.api.PlayerAPI;

import java.io.IOException;

public class Cmd$Me_Name extends CommandBase {

    @Override
    public void Execute() throws IOException {

        dataOut.writeUTF(PlayerAPI.GetUsernameByToken(token));

    }
}
