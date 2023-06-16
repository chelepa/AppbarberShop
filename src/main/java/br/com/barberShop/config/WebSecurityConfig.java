package br.com.barberShop.config;

import br.com.barberShop.component.JwtAuthenticationEntryPointComponent;
import br.com.barberShop.component.JwtRequestFilterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPointComponent jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilterComponent jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(HttpMethod.POST,"/v1/Customer")
                .antMatchers(HttpMethod.POST,"/v1/Customer/updatePassword")
                .antMatchers(HttpMethod.GET,"/v1/Customer/changePassword")
                .antMatchers("/swagger-ui/index.html#/","/v3/api-docs/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity  http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/v1/Customer").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.POST,"/v1/Customer/resetPassword").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.PUT,"/v1/Customer/*").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.DELETE,"/v1/Customer/*").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")

                .antMatchers(HttpMethod.GET, "/v1/group").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.GET, "/v1/group/*").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.POST, "/v1/group").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.PUT, "/v1/group/*").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.DELETE, "/v1/group/*").hasAnyAuthority("ROLE_ADM")

                .antMatchers(HttpMethod.GET, "/v1/permission").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.GET, "/v1/permission/*").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.POST, "/v1/permission").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.PUT, "/v1/permission/*").hasAnyAuthority("ROLE_ADM")
                .antMatchers(HttpMethod.DELETE, "/v1/permission/*").hasAnyAuthority("ROLE_ADM")

                .antMatchers(HttpMethod.GET, "/v1/service").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.GET, "/v1/service/*").hasAnyAuthority("ROLE_ADM", "ROLE_USR", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.POST, "/v1/service").hasAnyAuthority("ROLE_ADM", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.PUT, "/v1/service/*").hasAnyAuthority("ROLE_ADM", "ROLE_USR_ADM")
                .antMatchers(HttpMethod.DELETE, "/v1/service/*").hasAnyAuthority("ROLE_ADM", "ROLE_USR_ADM")

                .antMatchers(HttpMethod.POST, "/v1/Authentication").permitAll()
                .antMatchers("/**").authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
