/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import java.security.InvalidKeyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class Utility {

    /**
     * getRandomNumberInRange(5, 10), this will generates a random integer between 5 (inclusive) and 10 (inclusive).
     * @param min
     * @param max
     * @return 
     */
    public static int getRandomNumberInRange(int min, int max) {

	if (min >= max) {
		throw new IllegalArgumentException("max must be greater than min");
	}

	Random r = new Random();
	return r.nextInt((max - min) + 1) + min;
}
    
    public static String GenerateRandomNumber(int charLength) {
        return RandomStringUtils.randomNumeric(charLength);
    }

    public static Optional<AccountType> getAccountFromFileKey(String key) {
        Pattern p = Pattern.compile("^users/([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6})/.*$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(key);
        AccountType accountType = null;
        if (m.find()) {
            accountType = AccountType.APP_USER;
            accountType.setId(m.group(1));
        } else {
            p = Pattern.compile("^organizations/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})/.*$", Pattern.CASE_INSENSITIVE);
            m = p.matcher(key);
            if (m.find()) {
                accountType = AccountType.ORGANIZATION;
                accountType.setId(m.group(1));
            }
        }
        return Optional.ofNullable(accountType);
    }

    //http://www.mkyong.com/java/how-to-execute-shell-command-from-java/
    public static String executeCommand(String command, boolean waitFor) throws IOException, InterruptedException {

        StringBuffer output = new StringBuffer();

        Process p;
        p = Runtime.getRuntime().exec(command);
        if (!waitFor) {
            return null;
        }
        p.waitFor();
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        return output.toString();

    }

    public static Integer parseInt(String s) {
        Integer result = null;
        try {
            result = Integer.parseInt(s);
        } catch (Exception e) {
        }
        return result;
    }

    public static String generateMD5(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(content.getBytes(), 0, content.length());
        return "md5:" + new BigInteger(1, md.digest()).toString(16);
    }

    public static String runCommand(String... commands) throws IOException {
        StringBuffer output = new StringBuffer();
        Process proc = new ProcessBuilder(commands).start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // read the output from the command
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            output.append(s);
        }

// read any errors from the attempted command
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
        return output.toString();
    }

    public static String sign(String toSign) {
        if (toSign == null) {
            throw new RuntimeException("toSign is null");
        }
        toSign = toSign.replace("/" + Constants.BUCKET_NAME, "/" + Constants.BUCKET_NAME + "/" + Constants.BUCKET_NAME);
        String signed;
        try {
            signed = AmazonEncode.calculateRFC2104HMAC(toSign, "ki5RVRG3nfrl4AKk4H9Un4pjGRIqEG7uqHBP2i1Y");
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {

            throw new RuntimeException(ex);
        }

        return signed;
    }
}
