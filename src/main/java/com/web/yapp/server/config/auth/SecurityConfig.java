package com.web.yapp.server.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import static org.springframework.security.config.oauth2.client.CommonOAuth2Provider.GOOGLE;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                //.antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and()
                .defaultSuccessUrl("/")
                .failureUrl("/loginSuccess");
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/index"));



    }
}
