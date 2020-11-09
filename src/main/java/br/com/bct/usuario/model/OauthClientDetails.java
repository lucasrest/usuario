package br.com.bct.usuario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "oauth_client_details", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_oauth_client_details", name = "id", schema = "bct", allocationSize = 1)
public class OauthClientDetails extends EntidadeBase {

    @NotNull
    @Column(nullable = false, unique = true)
    private String clientId;
    private String resourceIds;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String secret;

}
