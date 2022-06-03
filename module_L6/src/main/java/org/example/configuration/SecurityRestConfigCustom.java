package org.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Order(1)
public class SecurityRestConfigCustom extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest()
                .hasAnyRole("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint((reg, resp, exception) -> {
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().println("{\"error\": " + exception.getMessage() + "\n }");
                })
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
