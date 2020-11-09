package br.com.bct.usuario.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.cryptography")
public class CryptographyProperties {

    private String algorithmKey;
    private String algorithm;
    @Value("${security.cryptography.key.private}")
    private String privateKey;
    @Value("${security.cryptography.key.public}")
    private String publicKey;

}
