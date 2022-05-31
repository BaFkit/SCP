package org.example.configuration;

import org.example.services.security.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigCustom {

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth, PasswordEncoder encoder, CustomerAuthService customerAuthService) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("test_user")
                .password(encoder.encode("password"))
                .roles("ADMIN");

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerAuthService);
        provider.setPasswordEncoder(encoder);
        auth.authenticationProvider(provider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}