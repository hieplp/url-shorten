package com.hieplp.url.common.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Integer SALT_SIZE = 64;
    private static final Integer ITERATION_COUNT = 65536;
    private static final Integer KEY_LENGTH = 64 * 8;

    private GenerateUtil() {
        throw new IllegalStateException("Utility class: GenerateUtil");
    }

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    public static byte[] generateSalt() {
        final Random sr = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        sr.nextBytes(salt);
        return salt;
    }

    public static byte[] hash(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }
}
