package com.wordpro.studentproject;

import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

public class DateValidation {

    public static boolean validateDate() {
        Calendar validatationDate = (Calendar) Calendar.getInstance().clone();
        validatationDate.set(Calendar.MONTH, 6); //indexing of month starts from zero (if feb then put 1)
//        validatationDate.set(Calendar.YEAR, 2021);
        validatationDate.set(Calendar.YEAR, 2023);
        validatationDate.set(Calendar.DAY_OF_MONTH, 30);
        if (new Date().getTime() > validatationDate.getTime().getTime()) {
            System.out.println(">>Not letting go beyond the expiry date");

            return false;
        }
        return true;
    }
}
