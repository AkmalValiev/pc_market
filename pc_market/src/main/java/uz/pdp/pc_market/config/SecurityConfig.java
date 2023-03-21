package uz.pdp.pc_market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests((auth)->auth
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails superAdmin = User.withDefaultPasswordEncoder()
                .username("superAdmin")
                .password("superAdmin")
                .roles("SUPER_ADMIN").authorities("READ_ALL_PRODUCT", "READ_ONE_PRODUCT","ADD_PRODUCT","EDIT_PRODUCT","DELETE_PRODUCT")
                .build();

        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("moderator")
                .password("moderator")
                .roles("MODERATOR").authorities("READ_ALL_PRODUCT", "READ_ONE_PRODUCT","ADD_PRODUCT","EDIT_PRODUCT")
                .build();

        UserDetails operator = User.withDefaultPasswordEncoder()
                .username("operator")
                .password("operator")
                .roles("OPERATOR").authorities("READ_ALL_PRODUCT", "READ_ONE_PRODUCT","ADD_PRODUCT")
                .build();
        return new InMemoryUserDetailsManager(superAdmin, moderator, operator);
    }

}
