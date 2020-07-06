package com.mattarama.mcstonks.api;

import com.mattarama.mcstonks.McStonks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hosts an API server on a specified port
 */
public class ApiServer {

    ServerSocket socket; // Socket for listening for requests
    Thread thread; //Thread for the server to run on

    /**
     * Creates a new ApiServer
     *
     * @param port The port of the server
     * @throws IOException
     */
    public ApiServer(int port) throws IOException {

        //Creates a socket
        socket = new ServerSocket(port);

        //Creates a thread
        thread = new Thread(r);

    }

    /**
     * All thread code for the server
     */
    Runnable r = () -> {

        //Accepts API requests
        try {

            while (true) {

                //Accepts data and processes
                Socket s = socket.accept();
                new ApiServerThread(s).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    };

    /**
     * Starts the API server on a separate thread
     */
    public void Start() {

        thread.start();

    }

    public void Interrupt() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }

}
