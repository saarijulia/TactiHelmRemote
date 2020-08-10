package com.example.tactihelmbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        initiateBluetooth();

        // Setting onClick listeners for the buttons

        //trying to separate onclick listener into a different class
        northNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "NN"));
        northFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "NF"));
        eastNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "EN"));
        eastFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "EF"));
        southNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "SN"));
        southFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "SF"));
        westNearButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "WN"));
        westFarButton.setOnClickListener(new buttonOnCLickListener(btWriter, this, "WF"));



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


                btWriter = new BluetoothWriter(btSocket);

                btWriter.run();


        }

        public void setStatus(String s){
        statusText.setText(s);
        }
    }
