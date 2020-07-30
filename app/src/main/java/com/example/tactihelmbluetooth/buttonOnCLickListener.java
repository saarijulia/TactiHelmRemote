package com.example.tactihelmbluetooth;

import android.view.View;

public class buttonOnCLickListener implements View.OnClickListener {
    BluetoothThread btThread;
    private int sendCode;

    private static final int NORTH_NEAR = 48;
    private static final int NORTH_FAR = 49;
    private static final int EAST_NEAR = 50;
    private static final int EAST_FAR = 51;
    private static final int SOUTH_NEAR = 52;
    private static final int SOUTH_FAR = 53;
    private static final int WEST_NEAR = 54;
    private static final int WEST_FAR = 55;


    public buttonOnCLickListener(BluetoothThread btThread, String code) {
        this.btThread = btThread; // assigning the bluetoothThread

        // Assigning the String codes to ints
        if (code.equals("NN")) {
            this.sendCode = NORTH_NEAR;
        } else if (code.equals("NF")) {
            this.sendCode = NORTH_FAR;
        } else if (code.equals("EN")) {
            this.sendCode = EAST_NEAR;
        } else if (code.equals("EF")) {
            this.sendCode = EAST_FAR;
        } else if (code.equals("SN")) {
            this.sendCode = SOUTH_NEAR;
        } else if (code.equals("SF")) {
            this.sendCode = SOUTH_FAR;
        } else if (code.equals("WN")) {
            this.sendCode = WEST_NEAR;
        } else if (code.equals("WF")) {
            this.sendCode = WEST_FAR;
        }

    }

   public void onClick(View view) {

        // trying to send the code via the bluetoothThread
        btThread.send(sendCode);
        System.out.println("buttonListener sending code");


   }
}
