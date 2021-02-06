package com.llycloud.lms.config.security;

import com.llycloud.lms.constants.SecurityConstants;
import com.llycloud.lms.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

/**
 * @author Akide Liu
 * Date 2021-01-28
 */

@Configuration
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private SecurityProblemSupport securityProblemSupport;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Apply the Spring Security selector to ignore the swagger url
     * @param web
     *
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui/**");
    }


    /**
     * Discretion : Apply customized policy for rest api end point
     *
     * 1. Publish login and register api to public
     * 2. left over apis require authorization
     * 3. securityConfigurationAdapter handled JWT generate, validation, destroy lifecycle
     *
     * @author Akide Liu
     */
    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.
                addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                // return 401 when the user is no authority to access the resources
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                // return 403 when the user is not permitted to access the resources due to user level
                .accessDeniedHandler(securityProblemSupport)
                .and()
                // disable CSRF
                .csrf().disable()
                .headers().frameOptions().disable()
                // .and().logout().logoutUrl("/auth/logout")
                .and()
                .authorizeRequests()
                // Set the resources public and not require authorization
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.AUTH_LOGIN_URL).permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                // Set other requests require authentication
                .anyRequest().authenticated()
                // disable session creation
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(securityConfigurationAdapter());

    }

    private JwtConfigurer securityConfigurationAdapter() throws Exception{
        return new JwtConfigurer(new JwtAuthorizationFilter(authenticationManager()));
    }
}
