package br.com.bct.usuario.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.oauth")
public class OAuthAuthorizationProperties {

    private String clientId;
    private String scope;
    private String[] grantTypes;
    private String signingKey;
    private String tokenEncoder;
    private Long expirationToken;
    private Long expirationRefreshToken;

}
