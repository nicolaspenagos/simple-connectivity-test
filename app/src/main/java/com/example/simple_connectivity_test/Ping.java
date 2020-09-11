/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.simple_connectivity_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private TextView pingTextView;
    private Button goBackButton;

    // -------------------------------------
    // Global variables
    // -------------------------------------
    private boolean kill;
    private String ip;
    private InetAddress inetAddress;
    private String msg;

    // -------------------------------------
    // Gui methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        pingTextView = findViewById(R.id.pingTextView);
        goBackButton = findViewById(R.id.goBackButtonP);

        pingTextView.setMovementMethod(new ScrollingMovementMethod());

        goBackButton.setOnClickListener(

                (view) -> {

                    kill = true;
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);

                }

        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        kill = false;
        ip = getSharedPreferences("bin", MODE_PRIVATE).getString("ip", "NO_STRING");

        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        startThread();

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------
    private void startThread() {

        new Thread(

                () -> {

                    while(!kill){

                        try {

                            Thread.sleep(2000);
                            connected();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }

        ).start();

    }

    public void connected(){

        try {

            String connected = (inetAddress.isReachable(500))?"Received":"Lost";

            runOnUiThread(
                    ()->{
                        msg = pingTextView.getText().toString();
                        msg += connected;
                        pingTextView.setText(msg+"\n");
                    }
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}