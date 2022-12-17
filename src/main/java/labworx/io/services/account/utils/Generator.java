package labworx.io.services.account.utils;

import org.springframework.stereotype.Service;

@Service
public class Generator {

    public static String getRandomNumbers(int length) {
        String numbers = "0123456789";
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (numbers.length() * Math.random());
            builder.append(numbers.charAt(index));
        }

        return builder.toString();
    }
}
