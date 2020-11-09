package br.com.bct.usuario.config;

import br.com.bct.usuario.model.CustomUserDetails;
import br.com.bct.usuario.model.constants.EXCEPTION;
import br.com.bct.usuario.security.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Component;


/**
 * Componente para validar a autenticação do spring security
 * Os dados de autenticação seram repassados pelo provider de autenticação
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CryptoUtil cryptoUtil;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    /**
     * Método usado para realizar a autenticação do spring security
     *
     * @param authentication credenciais do usuário
     * @return Authentication com credenciais do usuário logado
     * @throws AuthenticationException qualquer que ocorra na falha do login
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            //descripografa a senha com a chave privada
            String passwordDecr;
            passwordDecr = cryptoUtil.decrypt(password);
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
            //valida username e senha, a senha cadastrada deve está com encode bcrypt
            //a validação da senha deve ser usando o matches do passwordEnconder
            if (userDetails.getUsername().equalsIgnoreCase(username) &&
                    customPasswordEncoder.matches(passwordDecr, userDetails.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(username, passwordDecr, userDetails.getAuthorities());
                return new UsernamePasswordAuthenticationToken(userDetails, passwordDecr, userDetails.getAuthorities());
            } else {
                //se as credenciais forem inválidas deve lançar a exception
                throw new UnauthorizedClientException(EXCEPTION.FALHA_NA_AUTENTICACAO);
            }
        } catch (Exception ex) {
            throw new UnauthorizedClientException(EXCEPTION.FALHA_NA_AUTENTICACAO);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
