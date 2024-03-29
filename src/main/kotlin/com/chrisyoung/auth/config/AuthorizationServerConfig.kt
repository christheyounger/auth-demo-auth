package com.chrisyoung.auth.config

import com.chrisyoung.auth.service.ClientUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer

@EnableAuthorizationServer()
@Configuration(proxyBeanMethods = false)
class AuthorizationServerConfig(
    private val passwordEncoder: BCryptPasswordEncoder,
    val clientUserDetailsService: ClientUserDetailsService
): AuthorizationServerConfigurerAdapter() {

    private val clientID = "auth-frontend"
    private val clientSecret = "secret"
    private val redirectURLs = "http://localhost:3000/login"

    @Throws(Exception::class)
    override fun configure(
        oauthServer: AuthorizationServerSecurityConfigurer
    ) {
        oauthServer.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(clientUserDetailsService)
    }
}