package com.chrisyoung.auth.service

import com.chrisyoung.auth.ClientRepository
import com.chrisyoung.auth.entities.Client
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.stereotype.Service

@Service
class ClientUserDetailsService(
    private val clientRepository: ClientRepository
): RegisteredClientRepository {

    override fun findById(id: String): RegisteredClient? {
        clientRepository.findByIdOrNull(id)?.also { client ->
            return this.makeClient(client)
        }
        return null
    }

    override fun findByClientId(clientId: String): RegisteredClient? {
        clientRepository.findByIdOrNull(clientId)?.also {
            return this.makeClient(it)
        }
        return null
    }

    private fun makeClient(client: Client): RegisteredClient {
        return RegisteredClient.withId(client.id)
            .clientId(client.id)
            .clientSecret(client.secret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri(client.redirectUrl)
            .tokenSettings { t ->
                t.enableRefreshTokens(true)
                t.reuseRefreshTokens(false)
            }
            .scope("read")
            .build()
    }
}