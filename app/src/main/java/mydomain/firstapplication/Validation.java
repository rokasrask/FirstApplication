package mydomain.firstapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by moksleivis on 2018-01-24.
 */

public class Validation {

    public static boolean isValidCredentials(String string){
        final String NAME_PATTERN = "^[a-zA-Z0-9._-]{3,12}$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean isValidMail(String string){
        final String MAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
