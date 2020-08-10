package com.example.tactihelmbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // UUID of HC-06 bluetooth module is always the same
    private static final UUID hc06_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    // declaring button variables
    Button northNearButton, northFarButton, eastNearButton, eastFarButton, southNearButton,
            southFarButton, westNearButton, westFarButton;

    TextView statusText;

    // declaring objects needed for bluetooth connection
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;
    BluetoothDevice btDevice;
    BluetoothWriter btWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign buttons from view
        northNearButton = (Button) findViewById(R.id.button_north_near);
        northFarButton = (Button) findViewById(R.id.button_north_far);
        eastNearButton = (Button) findViewById(R.id.button_east_near);
        eastFarButton = (Button) findViewById(R.id.button_east_far);
        southNearButton = (Button) findViewById(R.id.button_south_near);
        southFarButton = (Button) findViewById(R.id.button_south_far);
        westNearButton = (Button) findViewById(R.id.button_west_near);
        westFarButton = (Button) findViewById(R.id.button_west_far);

        statusText = (TextView) findViewById(R.id.statusText) ;

        initiateBluetooth(); // method call to start bluetooth connection

        // Setting onClick listeners for the buttons

        //Button onClick listeners seperated into a different class - it takes in a mainActivity and String code as parameters
        northNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "NN"));
        northFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "NF"));
        eastNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "EN"));
        eastFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "EF"));
        southNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "SN"));
        southFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "SF"));
        westNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "WN"));
        westFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "WF"));

    }

    private void initiateBluetooth(){
            btAdapter = BluetoothAdapter.getDefaultAdapter();
            // attempt to connect to the bluetooth module

        // The address of the bluetooth module is hard coded
            btDevice = btAdapter.getRemoteDevice("98:D3:51:FD:A2:55");

            // create the socket and connect to bluetooth
            try {

                btSocket = btDevice.createRfcommSocketToServiceRecord(hc06_UUID);
                btSocket.connect();
                System.out.println("Connected to bluetooth");

            } catch (IOException e) {
                System.out.println("ERROR in connecting to bluetooth socket");
                e.printStackTrace();

            }

                // creating and running a BluetoothWriter thread - this handles output to bluetooth module
                btWriter = new BluetoothWriter(btSocket);
                btWriter.run();


        }

        // Method to change the status textview in application view
        public void setStatus(String s){
        statusText.setText(s);
        }
    }
