package com.computers.sun.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Hex;

import com.computers.sun.security.SunUser;

public class UtilFacadeImpl {

    public static String getEncryptedPassword(String inputText)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String encryptedPassword = new String(
                Hex.encode(md.digest(inputText.getBytes("UTF-8"))));
        return encryptedPassword;
    }

    public static boolean isUserTypePresent(
            Collection<GrantedAuthority> authorities, String userType) {
        boolean isUserTypePresent = false;
        for (GrantedAuthority grantedAuthority : authorities) {
            isUserTypePresent = grantedAuthority.getAuthority()
                    .equals(userType);
            if (isUserTypePresent)
                break;
        }
        return isUserTypePresent;
    }

    public static Long getUserIdFromSession() {
        SunUser SunUser = (SunUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return SunUser.getUserId();
    }

    public static String getUserNameFromSession() {
        SunUser SunUser = (SunUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userName = SunUser.getUsername();
        return userName;
    }

    public static String getPasswordFromSession() {
        SunUser SunUser = (SunUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return SunUser.getPassword();
    }

    public static String getFullName() {
        SunUser SunUser = (SunUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return SunUser.getFullName();
    }

    public static String getEmail() {
        SunUser SunUser = (SunUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return SunUser.getEmail();
    }

    public static String getEncodedImage(byte[] bytesImage) {
        return DatatypeConverter.printBase64Binary(bytesImage);
    }

    public static String toTitleCase(String s) {
        String output = "";
        StringTokenizer t = new StringTokenizer(s);
        int count = t.countTokens();
        int counter = 0;
        while (t.hasMoreTokens()) {
            String temp = t.nextToken();
            for (int i = 0; i < temp.length(); i++) {
                if (i == 0) {
                    output += Character.toUpperCase(temp.charAt(0));
                } else {
                    output += temp.charAt(i);
                }
            }
            if (counter != count - 1) {
                output += " ";
            }
            counter++;
        }
        return output;
    }

}
