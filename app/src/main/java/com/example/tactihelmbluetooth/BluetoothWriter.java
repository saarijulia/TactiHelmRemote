package com.example.tactihelmbluetooth;

import android.bluetooth.BluetoothSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * A thread to handle bluetooth events - send the codes to the HC-06 bluetooth module
 * Each code signals the prototype to trigger a specific tactile cue
 */
public class BluetoothWriter extends Thread {

    private final BluetoothSocket btSocket;
    private OutputStream output;


    // Constructor attempts to connect to the output stream of the bluetooth device
    public BluetoothWriter(BluetoothSocket socket) {
        this.btSocket = socket;

        // creating temporary output streams
        OutputStream tempOut = null;

        // Attempt to connect to the bluetooth servers IO streams
        try {
            tempOut = btSocket.getOutputStream();
            System.out.println("Connected to IO streams");

        } catch (IOException e) {
            System.out.println("ERROR IN CONNECTING TO IO STREAM");
            e.printStackTrace();
        }

        output = tempOut;

        try {
            // Test the connection
            output.flush();

        } catch (IOException e) {
            System.out.println("ERROR IN FLUSHING OUTPUT");
            return;
        }




    }

    // Empty method - needed for thread object
    public void run() {

        }

    /**
     * Method to send codes to the bluetooth module
     * @param code - Code for triggering tactile cues
     */
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
