package com.exposit.my_taxi.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private DataSource dataSource;
    private LogoutSuccessHandler restLogoutSuccessHandler;

    @Autowired
    public ResourceServerConfig(DataSource dataSource, LogoutSuccessHandler restLogoutSuccessHandler) {
        this.dataSource = dataSource;
        this.restLogoutSuccessHandler = restLogoutSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/user/**").hasRole("USER")
                .antMatchers(GET, "/resources/admin/**").hasRole("ADMIN")
                .antMatchers(GET, "/resources/customer_and_operator/**").hasAnyRole("OPERATOR", "CUSTOMER")
                .antMatchers(GET, "/resources/common/**").permitAll()
                .antMatchers("/users/**").permitAll()
//                .antMatchers( "/logout").authenticated()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .clearAuthentication(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource);
//                .inMemoryAuthentication()
// prefix in secret means that password is not encoded; see https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#pe-dpe
//                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    // It isn't necessary method.
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("Taxi"); // Set resource server name
    }
}
