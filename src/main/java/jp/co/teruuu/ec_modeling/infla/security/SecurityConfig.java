package jp.co.teruuu.ec_modeling.infla.security;

import jp.co.teruuu.ec_modeling.infla.encoder.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    // セキュリティ設定を無視するリクエスト設定
    web.ignoring().antMatchers(
            "/h2-console/**",
            "/product/search/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            // AUTHORIZE
            .authorizeRequests()
            .mvcMatchers("/preflight")
            .permitAll()
            .mvcMatchers("/user/**", "/order/**")
            .hasRole("USER")
            .anyRequest()
            .authenticated()
            .and()
            // EXCEPTION
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())
            .and()
            // LOGIN
            .formLogin()
            .loginProcessingUrl("/login").permitAll()
            .usernameParameter("name")
            .passwordParameter("password")
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
            .and()
            // LOGOUT
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler())
            //.addLogoutHandler(new CookieClearingLogoutHandler())
            .and()
            // CSRF
            .csrf()
            //.disable()
            //.ignoringAntMatchers("/login")
            .ignoringAntMatchers("/h2-console")
            .csrfTokenRepository(new CookieCsrfTokenRepository())
    ;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth,
                              UserAuthlService userDetailsService,
                              PasswordEncoder passwordEncoder) throws Exception {
    auth.eraseCredentials(true)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PassEncoder.encoder();
  }

  AuthenticationEntryPoint authenticationEntryPoint() {
    return new AuthEntoryPoint();
  }

  AccessDeniedHandler accessDeniedHandler() {
    return new UnauthorizedHandler();
  }

  AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new AuthSuccessHandler();
  }

  AuthenticationFailureHandler authenticationFailureHandler() {
    return new AuthFailHandler();
  }

  LogoutSuccessHandler logoutSuccessHandler() {
    return new HttpStatusReturningLogoutSuccessHandler();
  }
}
