package com.projeto.CarrosSpringBoot.seguranca;

import com.projeto.CarrosSpringBoot.configuracaocors.CorsConfig;
import com.projeto.CarrosSpringBoot.seguranca.jwt.JwtAuthenticationFilter;
import com.projeto.CarrosSpringBoot.seguranca.jwt.JwtAuthorizationFilter;
import com.projeto.CarrosSpringBoot.seguranca.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  //habilita a seguranca por metodo
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable(); //tira aquele formulario de login e deixa apenas uma caixa de gialogo para digitar login e senha
        //deixar sempre desabilitadoo csrf
    } */
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager();

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new CorsConfig())
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint((AuthenticationEntryPoint) unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //Metodo para criar dois usuario um administrador/usuario e outro apenas usuario com suas senhas 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);

        /*  auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("user")
                .password(encoder.encode("123")).roles("USER").and().withUser("admin")
                .password(encoder.encode("admin")).roles("USER","ADMIN"); */
    }

    
}
