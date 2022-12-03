package labworx.io.services.account.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ValidationUtil {
    final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
    final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isEmailValid(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(stripTags(email));

        return matcher.matches();
    }

    /**
     * String tags off user input
     * @param string String
     * @return string
     */
    public static String stripTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        Matcher matcher = REMOVE_TAGS.matcher(string);
        return matcher.replaceAll("");
    }
}
