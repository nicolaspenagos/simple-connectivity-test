/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.simple_connectivity_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private TextView hostTextView;
    private Button goBackButton;

    // -------------------------------------
    // Global variables
    // -------------------------------------
    private String ip = "192.168.0.";
    private InetAddress inetAddress;

    // -------------------------------------
    // Gui methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        hostTextView = findViewById(R.id.hostTextView);
        goBackButton = findViewById(R.id.goBackButtonH);

        goBackButton.setOnClickListener(

                (view) -> {

                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);

                }

        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        searchHosts();

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------
    public void searchHosts(){

        new Thread(

                ()->{

                    String hosts = "";
                    for(int i = 1; i<=254; i++){

                        String currentIp = ip+i;

                        try {

                            inetAddress = InetAddress.getByName(currentIp);

                            if(inetAddress.isReachable(100)){
                                hosts+=currentIp+"\n";
                            }

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                    String finalHosts = hosts;
                    runOnUiThread(
                            ()->{
                                hostTextView.setText(finalHosts);
                            }
                    );

                }

        ).start();

    }
}