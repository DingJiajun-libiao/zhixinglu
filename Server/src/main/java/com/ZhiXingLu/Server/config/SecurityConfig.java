package com.ZhiXingLu.Server.config;

import com.ZhiXingLu.Server.biz.auth.service.AuthService;
import com.ZhiXingLu.Server.support.auth.AuthAccessDeniedHandler;
import com.ZhiXingLu.Server.support.auth.AuthEntryPointJwt;
import com.ZhiXingLu.Server.support.auth.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author closer
 * @Description: Spring Security 配置
 * @Create: 2026/4/5 18:36
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final AuthAccessDeniedHandler authAccessDeniedHandler;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(authService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authEntryPointJwt)
                        .accessDeniedHandler(authAccessDeniedHandler))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 注册放行
                        .requestMatchers("/user/sign-up").permitAll()
                        // 认证接口放行（登录、刷新令牌）
                        .requestMatchers("/auth/**").permitAll()
                        // Swagger / OpenAPI 放行
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // 管理端接口需要 ADMIN 角色
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT")
                        // 其余接口需要认证
                        .anyRequest().authenticated()
                );

        http.headers(AbstractHttpConfigurer::disable);

        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
