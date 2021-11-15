package com.springweb.mvc.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.Exception
import javax.sql.DataSource

@EnableWebSecurity(debug = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val dataSource: DataSource? = null

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/app/{\\d+}/edit", "/app/{\\d+}/delete", "/app/add",
                "/api/{\\d+}/edit", "/api/{\\d+}/delete", "/api/add"
            ).hasRole("ADMIN")
            .antMatchers("/login*").not().authenticated()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/app/list")
    }
}
