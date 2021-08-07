package com.chrisyoung.auth.service

import com.chrisyoung.auth.ClientRepository
import com.chrisyoung.auth.entities.Client
import com.chrisyoung.auth.entities.ClientDetails
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class ClientUserDetailsService(
    private val clientRepository: ClientRepository
): RegisteredClientRepository {

    override fun findById(id: String): RegisteredClient? {
        clientRepository.findByIdOrNull(id)?.also { client ->
            return ClientDetails(client)
        }
        return null
    }

    override fun findByClientId(clientId: String): RegisteredClient? {
        clientRepository.findByIdOrNull(clientId)?.also {
            return ClientDetails(it)
        }
        return null
    }
}