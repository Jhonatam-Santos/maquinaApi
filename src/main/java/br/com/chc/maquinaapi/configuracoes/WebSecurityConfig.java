package br.com.chc.maquinaapi.configuracoes;

import br.com.chc.maquinaapi.dados.repositorios.UsuarioRepositorio;
import br.com.chc.maquinaapi.providers.JwtAuthProvider;
import br.com.chc.maquinaapi.servicos.CustomDetalhesUsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthProvider jwtAuthProvider;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userDetailsServices =new CustomDetalhesUsuarioServico();
        auth.userDetailsService(userDetailsServices).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO - Aqui voce consegue alterar as configuracoes de permissoes de acesso, como por exemplo, permitir ou nao acesso ao login, permitir ou nao acesso ao caminho de recuperacao de senha, etc.
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/roles/nova").permitAll()
                .antMatchers(HttpMethod.GET, "/api/roles").permitAll()
                .antMatchers(HttpMethod.POST, "/api/autenticar/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().apply(new JwtConfiguracoes(jwtAuthProvider));
    }

}
