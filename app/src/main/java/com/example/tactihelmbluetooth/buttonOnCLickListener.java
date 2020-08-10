package com.example.tactihelmbluetooth;

import android.view.View;
import android.widget.TextView;

public class buttonOnCLickListener implements View.OnClickListener {
    private BluetoothWriter btWriter;
    private int sendCode;
    private MainActivity mainActivity;
    private String code;


    private static final int NORTH_NEAR = 48;
    private static final int NORTH_FAR = 49;
    private static final int EAST_NEAR = 50;
    private static final int EAST_FAR = 51;
    private static final int SOUTH_NEAR = 52;
    private static final int SOUTH_FAR = 53;
    private static final int WEST_NEAR = 54;
    private static final int WEST_FAR = 55;


    public buttonOnCLickListener(BluetoothWriter btWriter, MainActivity mainActivity, String code) {
        this.btWriter = btWriter; // assigning the bluetoothThread
        this.mainActivity = mainActivity;
        this.code = code;

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
        this.btWriter.send(sendCode);
        System.out.println("buttonListener sending code");
        mainActivity.setStatus("Sending cue " + this.code);


   }
}
