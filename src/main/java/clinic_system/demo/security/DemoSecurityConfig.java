package clinic_system.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for jdbc
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configuer ->
                configuer
                        .requestMatchers(HttpMethod.GET, "/patient/patients").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/patient/patients/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/patient/patients").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/patient/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/doctor/doctors").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/doctor/doctors/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/doctor/doctors").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/doctor/doctors/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/appointment/appointments").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/appointment/appointments/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/appointment/appointments").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/appointment/appointments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/appointment/appointments/check-availability").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                        .anyRequest().permitAll()
                );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }



//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails nazar = User.builder()
//                .username("nazar")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        UserDetails adrian = User.builder()
//                .username("adrian")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails vitalik = User.builder()
//                .username("vitalik")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        return new InMemoryUserDetailsManager(nazar, adrian, vitalik);
//    }
}
