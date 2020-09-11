/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.simple_connectivity_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private EditText ping1EditText;
    private EditText ping2EditText;
    private EditText ping3EditText;
    private EditText ping4EditText;
    private Button pingButton;
    private Button searchHostButton;

    // -------------------------------------
    // Gui methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ping1EditText = findViewById(R.id.ping1EditText);
        ping2EditText = findViewById(R.id.ping2EditText);
        ping3EditText = findViewById(R.id.ping3EditText);
        ping4EditText = findViewById(R.id.ping4EditText);
        pingButton = findViewById(R.id.pingButton);
        searchHostButton = findViewById(R.id.searchHostButton);

        pingButton.setOnClickListener(

                (view)->{

                    Intent i = new Intent(this, Ping.class);
                    startActivity(i);

                    String ip = ping1EditText.getText().toString()+"."+ping2EditText.getText().toString()+"."+ping3EditText.getText().toString()+"."+ping4EditText.getText().toString();
                    getSharedPreferences("bin", MODE_PRIVATE).edit().putString("ip", ip).apply();;

                }

        );

        searchHostButton.setOnClickListener(

                (view)->{

                    Intent i = new Intent(this, Host.class);
                    startActivity(i);

                }

        );

    }
}