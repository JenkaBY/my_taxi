package com.exposit.my_taxi.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private AuthenticationManager authenticationManager;
    //    private UserApprovalHandler userApprovalHandler;
    private TokenStore tokenStore;


    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager,
//                                     UserApprovalHandler userApprovalHandler,
                                     TokenStore tokenStore) {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
//        this.userApprovalHandler = userApprovalHandler;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("taxi")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                // prefix in secret means that password is not encoded; see https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#pe-dpe
                .secret("{noop}taxisecret")
                // generated tokens will valid only for specified resource server names
                .resourceIds("Taxi")
                .accessTokenValiditySeconds(60000)
                .refreshTokenValiditySeconds(60000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
//                .prefix("/api")
//                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
//                .tokenEnhancer(tokenEnhancer());
    }
}
