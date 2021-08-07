package com.chrisyoung.auth.entities

import org.springframework.security.oauth2.core.AuthenticationMethod
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.config.ClientSettings
import org.springframework.security.oauth2.server.authorization.config.TokenSettings
import java.time.Duration

class ClientDetails(val client: Client): RegisteredClient() {
    override fun getAuthorizationGrantTypes(): MutableSet<AuthorizationGrantType> {
        return mutableSetOf(
            AuthorizationGrantType.AUTHORIZATION_CODE,
            AuthorizationGrantType.REFRESH_TOKEN
        )
    }

    override fun getClientId(): String {
        return client.id
    }

    override fun getId(): String {
        return client.id
    }

    override fun getClientSecret(): String {
        return client.secret
    }

    override fun getRedirectUris(): MutableSet<String> {
        return mutableSetOf(client.redirectUrl)
    }

    override fun getClientAuthenticationMethods(): MutableSet<ClientAuthenticationMethod> {
        return mutableSetOf(ClientAuthenticationMethod.BASIC, ClientAuthenticationMethod.POST)
    }

    override fun getScopes(): MutableSet<String> {
        return mutableSetOf("read")
    }

    override fun getClientSettings(): ClientSettings {
        return ClientSettings(
            mapOf(
                ClientSettings.REQUIRE_USER_CONSENT to !client.autoApprove,
                ClientSettings.REQUIRE_PROOF_KEY to false
            )
        )
    }

    override fun getTokenSettings(): TokenSettings {
        return TokenSettings(
            mapOf(
                TokenSettings.ENABLE_REFRESH_TOKENS to true,
                TokenSettings.REUSE_REFRESH_TOKENS to false,
                TokenSettings.ACCESS_TOKEN_TIME_TO_LIVE to Duration.ofMinutes(client.accessTokenLifeMinutes),
                TokenSettings.REFRESH_TOKEN_TIME_TO_LIVE to Duration.ofDays(client.refreshTokenLifeDays)
            )
        )
    }
}