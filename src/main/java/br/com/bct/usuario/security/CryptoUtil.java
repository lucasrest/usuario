package br.com.bct.usuario.security;

import br.com.bct.usuario.config.properties.CryptographyProperties;
import br.com.bct.usuario.exception.CryptographyException;
import br.com.bct.usuario.exception.InvalidPasswordException;
import br.com.bct.usuario.model.constants.EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class CryptoUtil {

    @Autowired
    private CryptographyProperties properties;

    public PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(properties.getAlgorithmKey());
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CryptographyException(e.getMessage());
        }
    }

    public PrivateKey getPrivateKey(String base64PrivateKey) {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(properties.getAlgorithmKey());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException(e.getMessage());
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new CryptographyException(e.getMessage());
        }
        return privateKey;
    }

    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(properties.getPublicKey()));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception ex) {
            throw new CryptographyException(ex.getMessage());
        }
    }

    public String decrypt(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));
        } catch (Exception ex) {
            throw new CryptographyException(ex.getMessage());
        }
    }

    public String decrypt(String data) {
        try {
            return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(properties.getPrivateKey()));
        } catch (Exception e) {
            throw new InvalidPasswordException(EXCEPTION.SENHA_INVALIDA);
        }
    }
}
