package com.example.khanu263.dopplereffectcalculator;

import android.widget.EditText;

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

    static boolean isInRange(int data, int lower, int upper) {
        if (data >= lower && data <= upper) {
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
            int data = Integer.parseInt(field.getText().toString());
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
            int data = Integer.parseInt(field.getText().toString());
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
            field.setError("Field is empty.");
            return false;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            int data = Integer.parseInt(field.getText().toString());
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
    static int calculateSound (EditText edit_temp) {
        int temperature = Integer.parseInt(edit_temp.getText().toString());
        return temperature;
    }

}
