package com.chrisyoung.auth

import com.chrisyoung.auth.entities.Client
import com.chrisyoung.auth.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}

interface ClientRepository : CrudRepository<Client, String> {

}