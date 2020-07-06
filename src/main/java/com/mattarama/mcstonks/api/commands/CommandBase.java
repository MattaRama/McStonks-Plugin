package com.mattarama.mcstonks.api.commands;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class CommandBase {

    String token;
    List<String> values;

    DataInputStream dataIn;
    DataOutputStream dataOut;

    public CommandBase ImportEnvironment(String token, List<String> values, DataInputStream dataIn, DataOutputStream dataOut) {

        this.token = token;
        this.values = values;

        this.dataIn = dataIn;
        this.dataOut = dataOut;

        return this;

    }

    public void Execute() throws IOException {}

}
