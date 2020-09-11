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
import android.view.View;
import android.view.ViewGroup;
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
    private View loadingBar;
    private TextView hostTextView;
    private Button goBackButton;


    // -------------------------------------
    // Global variables
    // -------------------------------------
    private int width;
    private String ip = "192.168.0.";
    private InetAddress inetAddress;
    private boolean kill;


    // -------------------------------------
    // Gui methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        loadingBar = findViewById(R.id.loadingBar);
        hostTextView = findViewById(R.id.hostTextView);
        goBackButton = findViewById(R.id.goBackButtonH);



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
        width = loadingBar.getWidth();
        kill = false;

        ViewGroup.LayoutParams params = loadingBar.getLayoutParams();
        params.width = 1;
        loadingBar.setLayoutParams(params);
        searchHosts();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = loadingBar.getWidth();
    }
    // -------------------------------------
    // Logic methods
    // -------------------------------------
    public void searchHosts(){

        new Thread(

                ()->{

                    String hosts = "";
                    for(int i = 1; i<=254 && !kill; i++){

                        String currentIp = ip+i;

                        try {

                            inetAddress = InetAddress.getByName(currentIp);

                            if(inetAddress.isReachable(100)){
                                hosts+=currentIp+"\n";
                                String finalHosts = hosts;
                                runOnUiThread(()-> hostTextView.setText(finalHosts));
                            }

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int finalI = i;
                        runOnUiThread(

                                ()->{

                                    ViewGroup.LayoutParams params = loadingBar.getLayoutParams();
                                    int x = width* finalI /254;
                                    params.width=x;
                                    loadingBar.setLayoutParams(params);

                                }
                        );

                    }




                }

        ).start();

    }
}