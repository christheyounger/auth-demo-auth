package com.chrisyoung.auth.service

import com.chrisyoung.auth.ClientRepository
import com.chrisyoung.auth.MyClientDetails
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service

@Service
class ClientUserDetailsService(
    private val clientRepository: ClientRepository
): ClientDetailsService {
    public override fun loadClientByClientId(clientId: String): MyClientDetails? {
        return clientRepository.findByIdOrNull(clientId)?.let { MyClientDetails(it) }
    }
}