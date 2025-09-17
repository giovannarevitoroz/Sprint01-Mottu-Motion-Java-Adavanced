package br.com.fiap.sprint1.security;

import br.com.fiap.sprint1.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Bean de criptografia
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Provedor de autenticação que usa o UserDetailsService
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuração de segurança
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF ATIVO (padrão)
                .csrf(csrf -> {
                    // não precisa configurar nada aqui, apenas deixa ativo
                })
                // Configuração de autorização
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso a páginas públicas e recursos estáticos
                        .requestMatchers("/login", "/error", "/css/**", "/js/**", "/webjars/**").permitAll()

                        // Páginas para ROLE_FUNCIONARIO e ROLE_GERENTE
                        .requestMatchers("/clientes/**", "/motos/**", "/movimentacoes/**")
                        .hasAnyAuthority("ROLE_FUNCIONARIO", "ROLE_GERENTE")

                        // Páginas apenas para GERENTE
                        .requestMatchers("/funcionarios/**", "/cargos/**", "/patios/**",
                                "/setores/**", "/vagas/**", "/gerentes/**")
                        .hasAuthority("ROLE_GERENTE")

                        // Qualquer outra requisição deve ser autenticada
                        .anyRequest().authenticated()
                )
                // Configuração de login
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                // Configuração de logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                // Página de acesso negado
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                )
                // Provedor de autenticação
                .authenticationProvider(authenticationProvider());

        return http.build();
    }
}
