package com.example.ganesha.abpv.MainActivities.MainActivities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ganesha on 11/08/2017.
 */
public class Support {
    public Support() {
        // Required empty public constructor
    }

    // validating email id
    public boolean isValidEmail(String email) {String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    public boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 7 && pass.length() <= 21) {
            return true;
        }
        return false;
    }
}
