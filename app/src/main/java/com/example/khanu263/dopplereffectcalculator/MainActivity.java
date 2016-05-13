package com.example.khanu263.dopplereffectcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declare programmable UI components
    EditText edit_freq, edit_temp, edit_vel_o, edit_vel_s;
    RadioButton check_vel_o_t, check_vel_s_t, check_metric, check_imperial;
    Button button_calculate_doppler;
    TextView text_results_freq, text_results_shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect UI elements to UI components in content_main
        edit_freq = (EditText) findViewById(R.id.edit_freq);
        edit_temp = (EditText) findViewById(R.id.edit_temp);
        edit_vel_o = (EditText) findViewById(R.id.edit_vel_o);
        edit_vel_s = (EditText) findViewById(R.id.edit_vel_s);
        check_vel_o_t = (RadioButton) findViewById(R.id.check_vel_o_t);
        check_vel_s_t = (RadioButton) findViewById(R.id.check_vel_s_t);
        check_metric = (RadioButton) findViewById(R.id.check_metric);
        check_imperial = (RadioButton) findViewById(R.id.check_imperial);
        button_calculate_doppler = (Button) findViewById(R.id.button_calculate_doppler);
        text_results_freq = (TextView) findViewById(R.id.text_results_freq);
        text_results_shift = (TextView) findViewById(R.id.text_results_shift);

        check_metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_temp.setHint(getString(R.string.edit_hint_temperature));
                edit_vel_o.setHint(getString(R.string.edit_hint_vel));
                edit_vel_s.setHint(getString(R.string.edit_hint_vel));
            }
        });

        check_imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_temp.setHint(getString(R.string.edit_hint_temperature_imperial));
                edit_vel_o.setHint(getString(R.string.edit_hint_vel_imperial));
                edit_vel_s.setHint(getString(R.string.edit_hint_vel_imperial));
            }
        });

        button_calculate_doppler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results;
                String results2;
                double dopplerFreq = Model.calculateDoppler(edit_freq, edit_temp, edit_vel_o, edit_vel_s, check_vel_o_t, check_vel_s_t);

                if (dopplerFreq == 0.0) {
                    results = "There was an error with your inputs.";
                    results2 = "Check them!";
                } else {
                    results = "New frequency: " + dopplerFreq + " Hz";
                    results2 = Model.calculateShift(edit_freq, dopplerFreq);
                }

                text_results_freq.setText(results);
                text_results_shift.setText(results2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
