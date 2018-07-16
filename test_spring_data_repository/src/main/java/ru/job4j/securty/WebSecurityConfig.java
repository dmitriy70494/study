package ru.job4j.securty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()) // должны хранится в памяти, можно хранить в БД или л-дап соединения
                .withUser("User").password("123").roles("USER");//есть юзер, у него есть пароль кридентиал, и есть оль ЮЗЕР
    }

    protected void configure(HttpSecurity http) throws Exception {
        http//настройки каким образом проверять запросы
                .authorizeRequests()//все запросы должны быть авторизованы
                .anyRequest().authenticated()//
                .and()
                .formLogin()//появление формы, если упустим то будеет неудобное всплывающее окно
                .and()
                .httpBasic()//с помощбю ключа сессшион кей
                .and()
                .csrf().disable();//избежать переборы ключей, форма дает сделать 1 запрос
    }


}
