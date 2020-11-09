package br.com.bct.usuario.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
class CryptoUtilTest {

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private CryptoUtil cryptoUtil;

    @Test
    public void shouldReturnPassordDecryptedWhenPasswordValid() {
        String password = "1mM@teste";
        String encrypt = cryptoUtil.encrypt(password);
        System.out.println("Encrypted password: " + encrypt);
        String decrypt = cryptoUtil.decrypt(encrypt);
        System.out.println(decrypt);
    }


}