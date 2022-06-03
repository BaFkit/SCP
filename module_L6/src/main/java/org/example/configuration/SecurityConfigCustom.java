package org.example.configuration;

import org.example.services.security.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(2)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {

    private CustomerAuthService customerAuthService;

    @Autowired
    public void setCustomerAuthService(CustomerAuthService customerAuthService) {
        this.customerAuthService = customerAuthService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customerAuthService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*.css", "/*.js").anonymous()
                .antMatchers("/").hasRole("GUEST")
                .antMatchers("/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/customer/**").hasRole("SUPER_ADMIN")
                .antMatchers("/customer/**").hasRole("ADMIN")
                .and()
                .formLogin();
    }
}