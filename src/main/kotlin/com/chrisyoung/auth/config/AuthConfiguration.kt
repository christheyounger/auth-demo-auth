package com.chrisyoung.auth.config

import com.chrisyoung.auth.*
import com.chrisyoung.auth.entities.Client
import com.chrisyoung.auth.entities.User
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthConfiguration {
    @Bean
    fun databaseInitializer(
        userRepository: UserRepository,
        clientRepository: ClientRepository
    ) = ApplicationRunner {
        userRepository.save(User("test@test.com", "0412345678", "Test", "User", passwordEncoder().encode("password")))
        clientRepository.save(Client("auth-frontend", "Website", "secret", "http://localhost:3000/login"))
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}