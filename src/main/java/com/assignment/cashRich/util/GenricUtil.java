package com.assignment.cashRich.util;

import java.security.SecureRandom;
import java.util.Base64;

public class GenricUtil {


    public static String generateRandomString() {
        byte[] randomBytes = new byte[24]; // 24 bytes => 32 characters
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        return base64Encoder.encodeToString(randomBytes);
    }

    public static String generateToken(String username) {
        return generateRandomString() + username + generateRandomString();

    }
}
