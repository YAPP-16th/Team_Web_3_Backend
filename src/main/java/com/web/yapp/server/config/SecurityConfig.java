package com.web.yapp.server.config;

import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@CrossOrigin("*")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final long MAX_AGE_SECS = 3600;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(MAX_AGE_SECS);
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000", "http://localhost:8080")
//                        .allowedMethods("*")
//                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("localhost:8080", "localhost:3000","13.209.105.111:3000","13.209.105.111:8080","ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:8080","ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000").permitAll()
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**,/musicians/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .antMatchers("/").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and()
                .defaultSuccessUrl("ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000")
                .failureUrl("ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000");
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/index"));
    }
}
