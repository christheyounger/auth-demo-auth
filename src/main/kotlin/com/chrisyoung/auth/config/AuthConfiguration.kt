package com.chrisyoung.auth.config

import com.chrisyoung.auth.*
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AuthConfiguration {
    @Bean
    fun databaseInitializer(
        userRepository: UserRepository,
        clientRepository: ClientRepository
    ) = ApplicationRunner {
        val user = userRepository.save(User("test@test.com", "0412345678", "Test", "User", passwordEncoder().encode("password")))
        val client = clientRepository.save(Client("auth-frontend", "Website", passwordEncoder().encode("secret"), "http://localhost:3000/login"))
    }
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}