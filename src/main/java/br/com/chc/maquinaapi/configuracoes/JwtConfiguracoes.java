package br.com.chc.maquinaapi.configuracoes;

import br.com.chc.maquinaapi.filters.JwtTokenFilter;
import br.com.chc.maquinaapi.providers.JwtAuthProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfiguracoes extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtAuthProvider jwtProvider;
    public JwtConfiguracoes(JwtAuthProvider jwtTokenProvider) {
        this.jwtProvider = jwtTokenProvider;
    }

    @Override
      public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
