package com.wms.global.config;

import com.wms.domain.token.repository.TokenRepository;
import com.wms.global.handler.CustomAccessDeniedHandler;
import com.wms.global.util.jwt.CustomLogoutFilter;
import com.wms.global.util.jwt.JWTFilter;
import com.wms.global.util.jwt.JwtUtil;
import com.wms.global.util.jwt.LoginFilter;
import com.wms.global.util.oauth2.CustomOAuth2UserService;
import com.wms.global.util.oauth2.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity // security 활성화 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http
                // CSRF 비활성화 (token을 사용하는 방식이기 때문에 csrf.token이 필요 없음)
                .csrf(CsrfConfigurer::disable)
                //시큐리티 폼 로그인 방식 사용 x
                .formLogin(auth -> auth.disable())
                //httpBasic방식 사용 x -> Bearer 방식
                .httpBasic(auth -> auth.disable())
                //oauth2
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)).successHandler(customSuccessHandler))
                // corsConfig 사용
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                //세션 사용 하지 않음(ALWAYS, IF_REQUIRED, NEVER등)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(this::configureAuthorization) //http별 커스텀 권한 설정
                .exceptionHandling(exceptionHandling -> exceptionHandling //권한이 없으면 해당 커스텀 핸들러로 이동
                        .accessDeniedHandler(customAccessDeniedHandler))
                .addFilterAt(new JWTFilter(jwtUtil), LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, tokenRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, tokenRepository), LogoutFilter.class);


        return http.build();

    }

    private void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.dispatcherTypeMatchers().permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/v1/refresh-token").permitAll()
                // 회원
                .requestMatchers("/api/v1/users/register").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/users/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/users/list").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 부서
                .requestMatchers("/api/v1/department/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/department/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/department/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 직급
                .requestMatchers("/api/v1/position/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/position/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/position/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 거래처
                .requestMatchers("/api/v1/client/register").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/client/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/client/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 상품
                .requestMatchers("/api/v1/item/register").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/item/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/item/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 창고
                .requestMatchers("/api/v1/warehouse/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/warehouse/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/warehouse/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 구역
                .requestMatchers("/api/v1/area/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/area/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/area/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 랙
                .requestMatchers("/api/v1/rack/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/rack/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/rack/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 셀
                .requestMatchers("/api/v1/cell/register").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/cell/delete/**").hasAnyAuthority("MANAGER","ADMIN")
                .requestMatchers("/api/v1/cell/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 로트
                .requestMatchers("/api/v1/lot/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/lot/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 재고
                .requestMatchers("/api/v1/inventory/register").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/inventory/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/inventory/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 수주서
                .requestMatchers("/api/v1/orderSheet/register").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/orderSheet/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/orderSheet/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 수주서 상세
                .requestMatchers("/api/v1/orderDetail/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")
                // 발주서
                .requestMatchers("/api/v1/sell/register").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/sell/delete/**").hasAnyAuthority("USER","MANAGER","ADMIN")
                .requestMatchers("/api/v1/sell/**").hasAnyAuthority("GUEST","USER","MANAGER","ADMIN")


                .anyRequest().authenticated();
    }
}

