package com.llycloud.lms.filter;

import com.llycloud.lms.constants.SecurityConstants;
import com.llycloud.lms.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @author Akide Liu
 * @date 2021-01-28 23:30
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // fetch authorization token from http request
        String tokenFromHttpRequest = this.getTokenFromHttpRequest(request);

        // check token is exist and validate token
        if (StringUtils.hasText(tokenFromHttpRequest) && JwtUtils.validateToken(tokenFromHttpRequest)) {

            Authentication authentication = JwtUtils.getAuthentication(tokenFromHttpRequest);

            // save authentication into spring context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);


    }


    /**
     * Discretion : fetch authorization token from http request
     *
     * @author Akide Liu
     * @param request HTTP request
     * @return token
     */
    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if (authenticationHeader == null || !authenticationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return null;
        }

        return authenticationHeader.replace(SecurityConstants.TOKEN_PREFIX, "");
    }

}
