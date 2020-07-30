package com.example.tactihelmbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final UUID hc06_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private byte b;

    // declaring button variables
    Button northNearButton, northFarButton, eastNearButton, eastFarButton, southNearButton,
            southFarButton, westNearButton, westFarButton;

    // declaring objects needed for bluetooth connection
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;
    BluetoothDevice btDevice;
    BluetoothThread btThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateBluetooth();

        // Assign buttons from view
        northNearButton = (Button) findViewById(R.id.button_north_near);
        northFarButton = (Button) findViewById(R.id.button_north_far);
        eastNearButton = (Button) findViewById(R.id.button_east_near);
        eastFarButton = (Button) findViewById(R.id.button_east_far);
        southNearButton = (Button) findViewById(R.id.button_south_near);
        southFarButton = (Button) findViewById(R.id.button_south_far);
        westNearButton = (Button) findViewById(R.id.button_west_near);
        westFarButton = (Button) findViewById(R.id.button_west_far);

        // Setting onClick listeners for the buttons

        //trying to separate onclick listener into a different class
        northNearButton.setOnClickListener(new buttonOnCLickListener(btThread,"NN"));
        northFarButton.setOnClickListener(new buttonOnCLickListener(btThread, "NF"));
        eastNearButton.setOnClickListener(new buttonOnCLickListener(btThread, "EN"));
        eastFarButton.setOnClickListener(new buttonOnCLickListener(btThread, "EF"));
        southNearButton.setOnClickListener(new buttonOnCLickListener(btThread, "SN"));
        southFarButton.setOnClickListener(new buttonOnCLickListener(btThread, "SF"));
        westNearButton.setOnClickListener(new buttonOnCLickListener(btThread, "WN"));
        westFarButton.setOnClickListener(new buttonOnCLickListener(btThread, "WF"));




/*
        eastNearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(50);
                    System.out.println("WRITING CODE EN");
                } else {
                    System.err.println("UNABLE TO SEND CODE EN");
                }

            }
        });

        eastFarButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(51);
                    System.out.println("WRITING CODE EN");
                } else {
                    System.err.println("UNABLE TO SEND CODE EN");
                }

            }
        });

        southNearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(52);
                    System.out.println("WRITING CODE SN");
                } else {
                    System.err.println("UNABLE TO SEND CODE SN");
                }

            }
        });

        southFarButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(53);
                    System.out.println("WRITING CODE SF");
                } else {
                    System.err.println("UNABLE TO SEND CODE SF");
                }

            }
        });

        westNearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(54);
                    System.out.println("WRITING CODE WN");
                } else {
                    System.err.println("UNABLE TO SEND CODE WN");
                }

            }
        });

        westFarButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(btSocket.isConnected() && btThread != null) {
                    // ASCII code for 1
                    btThread.send(55);
                    System.out.println("WRITING CODE WF");
                } else {
                    System.err.println("UNABLE TO SEND CODE WF");
                }

            }
        });


*/


/*
        // connecting to the device's inbuilt bluetooth adapter
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        // prints out the bluetooth paired devices
        System.out.println(btAdapter.getBondedDevices());

        // Specify the hc-06 bluetooth device - hard coded for now
        BluetoothDevice hc06 = btAdapter.getRemoteDevice("98:D3:51:FD:A2:55");

        // Check that we are connected to the right device
        System.out.println(hc06.getName());

        // Creating the Bluetooth socket to connect to the device
        BluetoothSocket btSocket = null;

        // a do-loop to try and connect until a connection is established
        int counter = 0;
        do {

            try {
                btSocket = hc06.createRfcommSocketToServiceRecord(hc06_UUID);
                // connect to the socket - the hc06 module is the server and the app is the client
                btSocket.connect();
                System.out.println(btSocket.isConnected());
                System.out.println(btSocket);

            } catch (IOException e) {
                System.out.println("Unable to connect to socket, attempt nro " + counter);
                e.printStackTrace();
            }

            counter++; // attempts to connect 5 times

        } while (!btSocket.isConnected() && counter < 5);


        // Try to connect to the outputstream
        try {
            OutputStream btOut = btSocket.getOutputStream();
            // OutputStreamWriter writer = new OutputStreamWriter(btOut);
            btOut.write(48); // the code to run the loop in the arduino
            btOut.flush();
            System.out.println("OUTPUT STREAM FLUSHED");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Bluetooth connection:" + btSocket.isConnected());
*/
        /*
        InputStream btIn = null;
        try {
            // Establishing input stream
            btIn = btSocket.getInputStream();
            btIn.skip(btIn.available()); // getting rid of any bytes that may be hanging around

            for (int i = 0; i < 26; i++) {
                // Read one byte from the input stream
                byte b = (byte)btIn.read();
                System.out.println((char) b); // print out the byte as a char

            }


            } catch (IOException ex) {
            ex.printStackTrace();

        }

         */







/*

        //Then try to close the connection
        try {
            btSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(btSocket.isConnected());
    }

 */
    }

    private void initiateBluetooth(){
            btAdapter = BluetoothAdapter.getDefaultAdapter();
            // attempt to connect to the bluetooth module
            btDevice = btAdapter.getRemoteDevice("98:D3:51:FD:A2:55");

            // create the socket
            try {

                btSocket = btDevice.createRfcommSocketToServiceRecord(hc06_UUID);
                btSocket.connect();
                System.out.println("Connected to bluetooth");





            } catch (IOException e) {
                System.out.println("ERROR in connecting to bluetooth socket");
                e.printStackTrace();

            }


                btThread = new BluetoothThread(btSocket);

                btThread.run();



        }
    }
