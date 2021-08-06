package com.chrisyoung.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails

class MyClientDetails(private val client: Client): ClientDetails {
    override fun getAccessTokenValiditySeconds(): Int {
        return(60 * 15)
    }

    override fun getRefreshTokenValiditySeconds(): Int {
        return(60 * 60 * 24)
    }

    override fun getAuthorizedGrantTypes(): MutableSet<String> {
        return mutableSetOf("authorization_code")
    }

    override fun getClientId(): String {
        return client.id
    }

    override fun getClientSecret(): String {
        return client.secret
    }

    override fun isScoped(): Boolean {
        return false
    }

    override fun getScope(): MutableSet<String> {
        return mutableSetOf<String>("read")
    }

    override fun getRegisteredRedirectUri(): MutableSet<String> {
        return mutableSetOf(client.redirectUrl)
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return mutableListOf<GrantedAuthority>()
    }

    override fun isSecretRequired(): Boolean {
        return client.secretRequired
    }

    override fun getResourceIds(): MutableSet<String> {
        return mutableSetOf<String>()
    }

    override fun isAutoApprove(scope: String?): Boolean {
        return client.autoApprove
    }

    override fun getAdditionalInformation(): MutableMap<String, Any> {
        return mutableMapOf()
    }
}