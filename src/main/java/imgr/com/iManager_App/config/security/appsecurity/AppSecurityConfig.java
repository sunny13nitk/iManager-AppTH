package imgr.com.iManager_App.config.security.appsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import imgr.com.iManager_App.config.security.srv.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AppSecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        // @formatter:off
        // h2-console is a servlet
        // https://github.com/spring-projects/spring-security/issues/12310
        /*
         * In this examplw we would be using Oauth2 JWT based Asymmetric Encrption RSA256 based authentication
         */
        httpSecurity.authorizeHttpRequests(
                                    authorize -> {
                                                        // Permit access to static resources and login, home, and error pages    
                                                        authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                                                        authorize.requestMatchers("/login", "/error/**", "/logout", "/", "/home").permitAll();
                                                        // Restrict access to admin and user pages based on roles
                                                        authorize.requestMatchers("/admin/**").hasAuthority("ADMIN");
                                                        authorize.requestMatchers("/user/**").hasAuthority("GENERAL_USER");
                                                        authorize.anyRequest().authenticated();
                                               })

                                                .formLogin((form) -> form
                                                                    .loginPage("/login")
                                                                    .defaultSuccessUrl("/", true)  // Redirect to home after successful login
                                                                    .permitAll())
                                                .logout((logout) -> logout.logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
                                                .permitAll());

        return httpSecurity.build();
                
               

                // @formatter:on
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsServiceImpl();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
