package com.mattarama.mcstonks.api;

import com.mattarama.mcstonks.McStonks;
import com.mattarama.mcstonks.api.commands.CommandBase;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ApiServerThread extends Thread {

    //Sock and Data Streams
    Socket s;

    DataInputStream dataIn;
    DataOutputStream dataOut;

    /**
     * A thread for communicating with a client
     *
     * @param s
     */
    public ApiServerThread(Socket s) {

        this.s = s;

    }

    /**
     * Processes the ongoing socket connection
     */
    public void run() {

        try {

            //Data channels
            dataIn = new DataInputStream(s.getInputStream());
            dataOut = new DataOutputStream(s.getOutputStream());

            //Listens for requests and processes them
            while (true) {

                if (ProcessInstruction(dataIn.readUTF().split(";"))) {
                    return;
                }

            }

        } catch (Exception e) {

            //Closes server connection due to an exception
            return;

        }

    }

    /**
     * Processes an instruction
     *
     * @return If the thread should end
     */
    public boolean ProcessInstruction(String[] input) throws IOException {

        //Prints debug into console
        McStonks.me.getLogger().info("Received request: " + String.join("", input));

        //Checks if the data is valid
        if (input.length < 2) {

            dataOut.writeUTF("[001]");
            return false;

        }

        //Argument declarations and utilities
        String token, request;
        List<String> values = new ArrayList<>();

        //Breaks apart request
        token = input[0];
        request = input[1];

        for (String s : input[2].split(",")) {

            values.add(s);

        }

        //Checks if token is valid
        if (!PlayerAPI.TokenIsTaken(token)) {
            dataOut.writeUTF("[002]");
            return false;
        }

        //Starts processing instructions
        CommandBase cmd = ApiRequestManager.commandIndex.get(request);

        if (cmd == null) {

            dataOut.writeUTF("[001]");
            return false;

        }

        //Runs Instruction Processor
        cmd
                .ImportEnvironment(token, values, dataIn, dataOut)
                .Execute();

        return false;

    }

}
