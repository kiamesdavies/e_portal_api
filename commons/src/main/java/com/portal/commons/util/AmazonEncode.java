/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class AmazonEncode {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * http://docs.aws.amazon.com/AmazonSimpleDB/latest/DeveloperGuide/HMACAuth.html#AuthJavaSampleHMACSignature
     *
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data The data to be signed.
     * @param key The signing key.
     * @return The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws ServletException
     * @throws java.security.SignatureException when signature generation fails
     */
    public static String calculateRFC2104HMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        String result;
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

        // get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);

        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(data.getBytes());

        // base64-encode the hmac
        result = new String(Base64.encodeBase64(rawHmac));
        return result;
    }
}
