package br.com.bct.usuario.config;

import br.com.bct.usuario.exception.CryptographyException;
import br.com.bct.usuario.security.CryptoUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder;

    private final CryptoUtil cryptoUtil;

    public CustomPasswordEncoder(BCryptPasswordEncoder passwordEncoder, CryptoUtil cryptoUtil) {
        this.passwordEncoder = passwordEncoder;
        this.cryptoUtil = cryptoUtil;
    }

    /**
     * enconda a senha usando o BCrypt
     *
     * @param rawPassword senha real conforme digitada pelo usuário
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        String hashedPass;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(rawPassword.toString().getBytes(), 0, rawPassword.toString().length());
            hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }
        } catch (Exception ex) {
            throw new CryptographyException(ex.getMessage());
        }
        return hashedPass;
    }

    /**
     * verificar se uma senha corresponde a senha encodada
     *
     * @param rawPassword     senha real não encodada
     * @param encodedPassword senha encodada conforme está na base
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String newEncodedPassword = encode(rawPassword);
        return MessageDigest.isEqual(newEncodedPassword.getBytes(), encodedPassword.getBytes());
    }
}
