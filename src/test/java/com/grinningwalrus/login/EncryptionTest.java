package com.grinningwalrus.login;

import junit.framework.TestCase;

public class EncryptionTest extends TestCase {

    public void test_encryption()
    {
        String test_word = "oktest123";
        String expected_out = "pmugtv244";
        assertEquals(Encryption.encrypt_password(test_word), expected_out);
        System.out.println(Encryption.encrypt_password(test_word) + " matches with " + expected_out);
        assertEquals(Encryption.decrypt_password(expected_out), test_word);
        System.out.println(Encryption.decrypt_password(expected_out) + " matches with " + test_word);
    }
}