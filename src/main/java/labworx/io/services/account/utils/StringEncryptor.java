package labworx.io.services.account.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StringEncryptor {
    private static BCryptPasswordEncoder _passwordEncoder;

    @Autowired
    public StringEncryptor(BCryptPasswordEncoder passwordEncoder) {
        StringEncryptor._passwordEncoder = passwordEncoder;
    }

    public static String Encrypt(String string) {
        if (string == null) return "String cannot be empty";
        _passwordEncoder.encode(string);
        try {
            byte[] encrypted = _hashString(string);
            return Arrays.toString(encrypted);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException noSuchAlgorithmException) {
            return noSuchAlgorithmException.getMessage();
        }
    }

    private static byte[] _hashString(String string) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(string.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }

}
