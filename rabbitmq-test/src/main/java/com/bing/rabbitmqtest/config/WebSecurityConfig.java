package com.bing.rabbitmqtest.config;

import com.bing.rabbitmqtest.middleware.filter.JwtAuthenticationTokenFilter;
import com.bing.rabbitmqtest.middleware.service.UserService;
import com.bing.rabbitmqtest.security.CustomAuthenticationFailureHandler;
import com.bing.rabbitmqtest.security.CustomAuthenticationSuccessHandler;
import com.bing.rabbitmqtest.security.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * spring security 配置 (认证授权)
 * @author sunyibing
 * @date 2024/4/4
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 允许放行
                .and().authorizeRequests().antMatchers("/login/**").permitAll()
                // 除login 其余都要验证
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authenticationProvider()
                .formLogin().loginPage("/login").permitAll()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler).permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        System.out.println(bCryptPasswordEncoder
                .matches("123456", "$2a$10$.5PyYJcuNiph5JCKwwBdDuixlvT7q3rPVWDmRpOS.nnIi.0UwAF/a"));
    }
}
