package com.exposit.my_taxi.app.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@Component("restLogoutSuccessHandler")
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {
    private final HttpStatus httpStatusToReturn;
    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    public RestLogoutSuccessHandler(HttpStatus httpStatusToReturn) {
        Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
        this.httpStatusToReturn = httpStatusToReturn;
    }

    public RestLogoutSuccessHandler() {
        this.httpStatusToReturn = OK;
    }

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setStatus(httpStatusToReturn.value());
        response.setContentType("application/json");
        response.getWriter().flush();

        // Extract authentication from request. The solution was taken from  OAuth2Filter
        Authentication auth = tokenExtractor.extract(request);
        if (auth != null && auth.getPrincipal() != null) {
            // Set auth for SecurityContext because it is empty
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            revokeToken(auth.getPrincipal());
            auth.setAuthenticated(false);
        }
        SecurityContextHolder.clearContext();
    }

    private void revokeToken(Object principal) {
        if (principal instanceof String) {
            defaultTokenServices.revokeToken(principal.toString());
            return;
        }
//        if (principal instanceof Principal){
//            ((Principal) principal).getName();
//            defaultTokenServices.revokeToken(principal.toString());
//        }

    }
}