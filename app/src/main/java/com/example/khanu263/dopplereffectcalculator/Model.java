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
    private static int lowerTempRangeImperial = -523;
    private static int upperTempRangeImperial = 523;
    private static int lowerVelRange = 0;
    private static int upperVelRange = 999;
    private static int upperVelRangeImperial = 3278;

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

    // Conversion methods
    static double convertFahrToCel(EditText edit_temp) {
        double fahrenheit = Double.parseDouble(edit_temp.getText().toString());
        double celsius = 0.5555 * (fahrenheit - 32);
        return celsius;
    }

    static double convertFPStoMS (EditText edit_vel) {
        double feet = Double.parseDouble(edit_vel.getText().toString());
        return feet * 0.3048;
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

    static boolean isValidTempImperial(EditText field) {

        // Check if empty
        if (isEmpty(field)) {
            field.setError("Field is empty.");
            return false;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            double data = Double.parseDouble(field.getText().toString());
            if (isInRange(data, lowerTempRangeImperial, upperTempRangeImperial)) {
                return true;
            } else {
                field.setError("Must be between " + lowerTempRangeImperial + " & " + upperTempRangeImperial + ".");
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

    static boolean isValidVelocityImperial(EditText field) {

        // Check if empty
        if (isEmpty(field)) {
            field.setText("0");
            return true;
        }

        // Try to parse the field (see if it can be converted to an int)
        try {
            double data = Double.parseDouble(field.getText().toString());
            if (isInRange(data, lowerVelRange, upperVelRangeImperial)) {
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
    static double calculateSound (double temperature) {
        // Sound to the tenths place
        double sound = (Math.round((331.4 + (0.6 * temperature)) * 10));
        sound = sound / 10;
        return sound;
    }

    // Calculate new frequency
    static double calculateDoppler (EditText edit_freq, EditText edit_temp, EditText edit_vel_o, EditText edit_vel_s, RadioButton o_t, RadioButton s_t, boolean isImperial) {

        // Initialize variables
        double dopplerFreq;
        double originalFreq;
        double speedOfSound;
        double observerVel;
        double sourceVel;

        // Correct calculations based on units
        if (isImperial) {

            // If everything is valid
            if (isValidFreq(edit_freq) && isValidTempImperial(edit_temp) && isValidVelocityImperial(edit_vel_o) & isValidVelocityImperial(edit_vel_s)) {

                // If the original frequency is 0, return special value
                if (Integer.parseInt(edit_freq.getText().toString()) == 0) {
                    return -1.1;
                }

                // Get variables, converting imperial to metric
                originalFreq = Double.parseDouble(edit_freq.getText().toString());
                speedOfSound = calculateSound(convertFahrToCel(edit_temp));
                observerVel = convertFPStoMS(edit_vel_o);
                sourceVel = convertFPStoMS(edit_vel_s);

                // Correct for direction
                if (!(o_t.isChecked())) {
                    observerVel = observerVel * -1;
                }

                if (!(s_t.isChecked())) {
                    sourceVel = sourceVel * -1;
                }

                // Doppler effect to the tenths place
                dopplerFreq = (Math.round((originalFreq * ((speedOfSound + observerVel) / (speedOfSound - sourceVel))) * 10));
                dopplerFreq = dopplerFreq / 10;

            } else {
                dopplerFreq = 0.0; // Otherwise, return 0 to indicate failure
            }

        } else {

            // Same, but without imperial corrections
            if (isValidFreq(edit_freq) && isValidTemp(edit_temp) && isValidVelocity(edit_vel_o) & isValidVelocity(edit_vel_s)) {

                if (Integer.parseInt(edit_freq.getText().toString()) == 0) {
                    return -1.1;
                }

                originalFreq = Double.parseDouble(edit_freq.getText().toString());
                speedOfSound = calculateSound(Double.parseDouble(edit_temp.getText().toString()));
                observerVel = Double.parseDouble(edit_vel_o.getText().toString());
                sourceVel = Double.parseDouble(edit_vel_s.getText().toString());

                if (!(o_t.isChecked())) {
                    observerVel = observerVel * -1;
                }

                if (!(s_t.isChecked())) {
                    sourceVel = sourceVel * -1;
                }

                dopplerFreq = (Math.round((originalFreq * ((speedOfSound + observerVel) / (speedOfSound - sourceVel))) * 10));
                dopplerFreq = dopplerFreq / 10;

            } else {
                dopplerFreq = 0.0;
            }

        }

        return dopplerFreq;
    }

    // Calculate shift in frequency
    static String calculateShift (EditText edit_freq, double dopplerFreq) {

        // Initialize variables
        String shift;
        double originalFreq = Double.parseDouble(edit_freq.getText().toString());

        // Calculate shift to tenths place
        double dopplerShift = Math.round((originalFreq - dopplerFreq) * 10);
        dopplerShift = dopplerShift / 10;

        // Return corresponding string
        if (dopplerShift < 0) {
            dopplerShift = dopplerShift * -1;
            shift = "The frequency increased by " + dopplerShift + " Hz.";
            return shift;
        } else if (dopplerShift == 0) {
            return "The frequency did not change.";
        } else {
            shift = "The frequency decreased by " + dopplerShift + " Hz.";
            return shift;
        }

    }

}