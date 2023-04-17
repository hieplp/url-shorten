package com.hieplp.url.common.util;

import com.hieplp.url.common.exception.EncryptionException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
public class RsaUtil {

    public RsaUtil() {
        throw new IllegalStateException("Utility class: RsaUtil");
    }

    public static PublicKey getPublicKey(PrivateKey privateKey) {
        try {
            log.info("Get public key from private key");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            RSAPrivateCrtKey rsaPrivateCrtKey = (RSAPrivateCrtKey) privateKey;
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(rsaPrivateCrtKey.getModulus(), rsaPrivateCrtKey.getPublicExponent());
            return kf.generatePublic(rsaPublicKeySpec);
        } catch (Exception e) {
            log.error("Error when get public key from private key: {}", e.getMessage());
            throw new EncryptionException("Error when get public key");
        }
    }


    public static byte[] decrypt(byte[] buffer, PrivateKey privateKey) {
        try {
            log.info("Start decrypt");

            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, privateKey);

            return rsa.doFinal(buffer);
        } catch (Exception e) {
            log.error("Error when decrypt RSA string caused by {}", e.getMessage());
            throw new EncryptionException("Error on decrypt password.");
        }
    }

    public static byte[] encrypt(byte[] buffer, PublicKey publicKey) {
        try {
            log.info("Start encrypt");

            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, publicKey);

            return rsa.doFinal(buffer);
        } catch (Exception e) {
            log.error("Error when encrypt RSA string caused by {}", e.getMessage());
            throw new EncryptionException("Error on encrypt password.");
        }
    }

    public static byte[] generatePassword(String password, PrivateKey privateKey, byte[] salt) {
        try {
            log.info("Start generate password");

            byte[] rawPassword = decrypt(Base64.getDecoder().decode(password), privateKey);
            rawPassword = GenerateUtil.hash(ConverterUtil.toCharArray(rawPassword), salt);

            return rawPassword;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error when generate password: {}", e.getMessage());
            throw new EncryptionException("Error on generate password");
        }
    }
}
