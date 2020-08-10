package com.example.tactihelmbluetooth;

import android.bluetooth.BluetoothSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * A thread to handle bluetooth events
 */
public class BluetoothWriter extends Thread {

    private final BluetoothSocket btSocket;
    private InputStream input;
    private OutputStream output;


    public static final int NORTH_NEAR = 48;

    public BluetoothWriter(BluetoothSocket socket) {
        this.btSocket = socket;

        // creating temporary input and output streams
        InputStream tempIn = null;
        OutputStream tempOut = null;

        // Attempt to connect to the bluetooth servers IO streams
        try {
            tempIn = btSocket.getInputStream();
            tempOut = btSocket.getOutputStream();
            System.out.println("Connected to IO streams");

        } catch (IOException e) {
            System.out.println("ERROR IN CONNECTING TO IO STREAMS");
            e.printStackTrace();
        }

        input = tempIn;
        output = tempOut;

        try {
            output.flush();

        } catch (IOException e) {
            System.out.println("ERROR IN FLUSHING OUTPUT");
            return;
        }




    }

    public void run() {

        }
       /* while(true) {
            try {
                String btMessage = btReader.readLine();
                System.out.println(btMessage);
            } catch (IOException e) {
                System.out.println("ERROR IN READING FROM INPUT STREAM");
                e.printStackTrace();
                break;
            }
        } */



    public void send(int code) {

        if (btSocket.isConnected()) {

            try {
                OutputStreamWriter outputWrite = new OutputStreamWriter(output);
                output.write(code);
                output.flush();
                System.out.println("Sending code to bluetooth");

            } catch (IOException e) {
                System.out.println("ERROR IN WRITING TO OUTPUT STREAM");
                e.printStackTrace();
            }
        }
    }

    // close the bluetooth connection
    public void cancel() {
        try {
            btSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}