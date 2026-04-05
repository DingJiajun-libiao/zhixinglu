package com.ZhiXingLu.Server.support.auth;

import com.ZhiXingLu.Server.biz.auth.AuthContext;
import com.ZhiXingLu.Server.biz.auth.AuthUser;
import com.ZhiXingLu.Server.biz.auth.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *  @author closer
 *  @Description: JWT 认证过滤器，解析 Authorization Header 中的 Bearer Token
 *  @Create: 2026/4/5 19:27
 */
@Slf4j
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseToken(request);
            if (Objects.nonNull(token)) {
                AuthUser authUser = authService.verify(token);
                if (Objects.nonNull(authUser)) {
                    // 将角色转换为 Spring Security 的 GrantedAuthority
                    List<SimpleGrantedAuthority> authorities = authUser.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();

                    var authentication = new UsernamePasswordAuthenticationToken(authUser, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 存入 ThreadLocal
                    AuthContext.setUser(authUser);
                }
            }
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
            AuthContext.clear();
        }
    }

    /**
     * 从 Authorization Header 中解析 Bearer Token
     */
    private String parseToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
