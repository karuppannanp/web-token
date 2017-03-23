package com.homedepot.webtoken;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.*;

/**
 * The Algorithm class represents an algorithm to be used in the Signing or Verification process of a Token.
 */
@SuppressWarnings("WeakerAccess")
public abstract class Algorithm {

    private final String name;
    private final String description;

    /**
     * Creates a new Algorithm instance using HmacSHA256. Tokens specify this as "HS256".
     *
     * @param secret the secret to use in the verify or signing instance.
     * @return a valid HMAC256 Algorithm.
     * @throws IllegalArgumentException     if the provided Secret is null.
     * @throws UnsupportedEncodingException if the current Java platform implementation doesn't support the UTF-8 character encoding.
     */
    public static Algorithm HMAC256(String secret) throws IllegalArgumentException, UnsupportedEncodingException {
        return new HMACAlgorithm("HS256", "HmacSHA256", secret);
    }

    /**
     * Creates a new Algorithm instance using HmacSHA384. Tokens specify this as "HS384".
     *
     * @param secret the secret to use in the verify or signing instance.
     * @return a valid HMAC384 Algorithm.
     * @throws IllegalArgumentException     if the provided Secret is null.
     * @throws UnsupportedEncodingException if the current Java platform implementation doesn't support the UTF-8 character encoding.
     */
    public static Algorithm HMAC384(String secret) throws IllegalArgumentException, UnsupportedEncodingException {
        return new HMACAlgorithm("HS384", "HmacSHA384", secret);
    }

    /**
     * Creates a new Algorithm instance using HmacSHA512. Tokens specify this as "HS512".
     *
     * @param secret the secret to use in the verify or signing instance.
     * @return a valid HMAC512 Algorithm.
     * @throws IllegalArgumentException     if the provided Secret is null.
     * @throws UnsupportedEncodingException if the current Java platform implementation doesn't support the UTF-8 character encoding.
     */
    public static Algorithm HMAC512(String secret) throws IllegalArgumentException, UnsupportedEncodingException {
        return new HMACAlgorithm("HS512", "HmacSHA512", secret);
    }

    /**
     * Creates a new Algorithm instance using HmacSHA256. Tokens specify this as "HS256".
     *
     * @param secret the secret bytes to use in the verify or signing instance.
     * @return a valid HMAC256 Algorithm.
     * @throws IllegalArgumentException if the provided Secret is null.
     */
    public static Algorithm HMAC256(byte[] secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS256", "HmacSHA256", secret);
    }

    /**
     * Creates a new Algorithm instance using HmacSHA384. Tokens specify this as "HS384".
     *
     * @param secret the secret bytes to use in the verify or signing instance.
     * @return a valid HMAC384 Algorithm.
     * @throws IllegalArgumentException if the provided Secret is null.
     */
    public static Algorithm HMAC384(byte[] secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS384", "HmacSHA384", secret);
    }

    /**
     * Creates a new Algorithm instance using HmacSHA512. Tokens specify this as "HS512".
     *
     * @param secret the secret bytes to use in the verify or signing instance.
     * @return a valid HMAC512 Algorithm.
     * @throws IllegalArgumentException if the provided Secret is null.
     */
    public static Algorithm HMAC512(byte[] secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS512", "HmacSHA512", secret);
    }


    public static Algorithm none() {
        return new NoneAlgorithm();
    }

    protected Algorithm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for the name of this Algorithm, as defined in the JWT Standard. i.e. "HS256"
     *
     * @return the algorithm name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of this Algorithm, required when instantiating a Mac or Signature object. i.e. "HmacSHA256"
     *
     * @return the algorithm description.
     */
    String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Verify the given content using this Algorithm instance.
     *
     * @param contentBytes   an array of bytes representing the base64 encoded content to be verified against the signature.
     * @param signatureBytes an array of bytes representing the base64 encoded signature to compare the content against.
     * @throws SignatureVerificationException if the Token's Signature is invalid, meaning that it doesn't match the signatureBytes, or if the Key is invalid.
     */
    public abstract void verify(byte[] contentBytes, byte[] signatureBytes) throws SignatureVerificationException;

    /**
     * Sign the given content using this Algorithm instance.
     *
     * @param contentBytes an array of bytes representing the base64 encoded content to be verified against the signature.
     * @return the signature in a base64 encoded array of bytes
     * @throws SignatureGenerationException if the Key is invalid.
     */
    public abstract byte[] sign(byte[] contentBytes) throws SignatureGenerationException;
}
