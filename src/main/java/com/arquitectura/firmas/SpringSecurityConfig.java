package com.arquitectura.firmas;

import com.arquitectura.firmas.auth.filter.JWTAuthenticationFilter;
import com.arquitectura.firmas.auth.filter.JWTAuthorizationFilter;
import com.arquitectura.firmas.auth.service.JWTService;
import com.arquitectura.firmas.services.auth.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")	
                .permitAll()
                .antMatchers("/user/**","/user","user","/file/**","/file","/signature_requests/**","/signature_requests")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user/save","/signature_requests/save","/file/save")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService);
    }
}
