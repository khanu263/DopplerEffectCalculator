package com.example.khanu263.dopplereffectcalculator;

import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by khanu263 on 5/9/2016.
 */
public class Model {

    // Range variables
    private static int lowerFreqRange = 0;
    private static int upperFreqRange = 20000;
    private static int lowerTempRange = -273;
    private static int upperTempRange = 273;
    private static int lowerVelRange = 0;
    private static int upperVelRange = 999;

    // Form validation methods
    static boolean isEmpty(EditText field) {
        if (field.getText().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isInRange(double data, int lower, int upper) {
        if (data >= lower && data <= upper) {
            return true;
        } else {
            return false;
        }
    }

    // Check if towards or away
    static boolean isChecked (RadioButton rb) {
        if (rb.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    // Validate frequency
    static boolean isValidFreq(EditText field) {

        // Check if empty
        if (isEmpty(field)) {
            field.setError("Field is empty.");
            return false;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            double data = Double.parseDouble(field.getText().toString());
            if (isInRange(data, lowerFreqRange, upperFreqRange)) {
                return true;
            } else {
                field.setError("Must be between " + lowerFreqRange + " & " + upperFreqRange + ".");
                return false;
            }
        } catch (Exception e) {
            field.setError("Must be a number.");
            return false;
        }

    }

    // Validate temperature
    static boolean isValidTemp(EditText field) {

        // Check if empty
        if (isEmpty(field)) {
            field.setError("Field is empty.");
            return false;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            double data = Double.parseDouble(field.getText().toString());
            if (isInRange(data, lowerTempRange, upperTempRange)) {
                return true;
            } else {
                field.setError("Must be between " + lowerTempRange + " & " + upperTempRange + ".");
                return false;
            }
        } catch (Exception e) {
            field.setError("Must be a number.");
            return false;
        }

    }

    // Validate velocity
    static boolean isValidVelocity(EditText field) {

        // Check if empty
        if (isEmpty(field)) {
            field.setText("0");
            return true;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            double data = Double.parseDouble(field.getText().toString());
            if (isInRange(data, lowerVelRange, upperVelRange)) {
                return true;
            } else {
                field.setError("Must be between " + lowerVelRange + " & " + upperVelRange + ".");
                return false;
            }
        } catch (Exception e) {
            field.setError("Must be a number.");
            return false;
        }

    }

    // Calculate speed of sound
    static double calculateSound (EditText edit_temp) {
        double temperature = Double.parseDouble(edit_temp.getText().toString());
        double sound = (Math.round((331.4 + (0.6 * temperature)) * 10));
        sound = sound / 10;
        return sound;
    }

    // Calculate new frequency
    static double calculateDoppler (EditText edit_freq, EditText edit_temp, EditText edit_vel_o, EditText edit_vel_s, RadioButton o_t, RadioButton s_t) {

        double dopplerFreq;

        if (isValidFreq(edit_freq) && isValidTemp(edit_temp) && isValidVelocity(edit_vel_o) & isValidVelocity(edit_vel_s)) {

            double originalFreq = Double.parseDouble(edit_freq.getText().toString());
            double speedOfSound = calculateSound(edit_temp);
            double observerVel = Double.parseDouble(edit_vel_o.getText().toString());
            double sourceVel = Double.parseDouble(edit_vel_s.getText().toString());

            if (!(isChecked(o_t))) {
                observerVel = observerVel * -1;
            }

            if (!(isChecked(s_t))) {
                sourceVel = sourceVel * -1;
            }

            dopplerFreq = (Math.round((originalFreq * ((speedOfSound + observerVel) / (speedOfSound - sourceVel))) * 10));
            dopplerFreq = dopplerFreq / 10;

        } else {
            dopplerFreq = 0.0;
        }

        return dopplerFreq;
    }

    // Calculate shift in frequency
    static String calculateShift (EditText edit_freq, double dopplerFreq) {

        String shift;
        double originalFreq = Double.parseDouble(edit_freq.getText().toString());
        double dopplerShift = Math.round((originalFreq - dopplerFreq) * 10);
        dopplerShift = dopplerShift / 10;

        if (dopplerShift < 0) {
            dopplerShift = dopplerShift * -1;
            shift = "The frequency decreased by " + dopplerShift + " Hz.";
            return shift;
        } else {
            shift = "The frequency increased by " + dopplerShift + " Hz.";
            return shift;
        }

    }

}
