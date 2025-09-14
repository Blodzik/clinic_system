package clinic_system.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails nazar = User.builder()
                .username("nazar")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        UserDetails adrian = User.builder()
                .username("adrian")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails vitalik = User.builder()
                .username("vitalik")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(nazar, adrian, vitalik);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configuer ->
                configuer
                        .requestMatchers(HttpMethod.GET, "/patient/patients").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/patient/patients/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "patient/patients").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "patient/patients").hasRole("Manager")
                        .requestMatchers(HttpMethod.DELETE, "patient/patients/**").hasRole("Admin")
                );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
