package com.exposit.my_taxi.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static String REALM = "TAXI_REALM";
    private AuthenticationManager authenticationManager;
    private UserApprovalHandler userApprovalHandler;
    private TokenStore tokenStore;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                     UserApprovalHandler userApprovalHandler,
                                     TokenStore tokenStore,
                                     PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.userApprovalHandler = userApprovalHandler;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
//                .realm(REALM + "/client");
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("taxi")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret("taxisecret")
                .resourceIds("Resource")
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
