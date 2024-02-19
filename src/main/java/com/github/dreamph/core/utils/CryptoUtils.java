package com.github.dreamph.core.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;

public class CryptoUtils {

    public static String fileToSHA256(File file) throws Exception {
        byte[] data = Files.readAllBytes(file.toPath());
        return fileToSHA256(data);
    }

    public static String fileToSHA256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(data);
        return bytesToHex(digest.digest());
    }

    public static String stringToSHA256(String value) throws Exception {
        if (ValidationUtils.isEmpty(value)) {
            return null;
        }
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(data);
        return bytesToHex(digest.digest());
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generatePassword(int length) {
        return RandomStringUtils.random(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\\\|;:\\'\\\",<.>/?");

    }
}
