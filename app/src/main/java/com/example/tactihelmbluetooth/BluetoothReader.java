package com.example.tactihelmbluetooth;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class BluetoothReader extends Thread {
    private BluetoothSocket socket;
    private MainActivity mainActivity;
    private InputStream inputStream;

    public BluetoothReader(BluetoothSocket s, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        socket = s;
    }

    public void run() {
        try {
            inputStream = socket.getInputStream();
            inputStream.skip(inputStream.available());


        } catch (IOException e) {
            e.printStackTrace();
        }

           /*

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}
