/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.simple_connectivity_test;

import androidx.appcompat.app.AppCompatActivity;

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

    }
}