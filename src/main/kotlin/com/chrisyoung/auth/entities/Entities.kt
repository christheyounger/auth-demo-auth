package com.chrisyoung.auth.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class User(
        val email: String,
        val mobile: String,
        val firstName: String,
        val lastName: String,
        val password: String,
        @Id @GeneratedValue val id: Long? = null)

@Entity
class Client(
        @Id val id: String,
        val name: String,
        val secret: String,
        val redirectUrl: String,
        val secretRequired: Boolean = true,
        val autoApprove: Boolean = false)

@Entity
class Code(
        val code: String,
        @ManyToOne val client: Client,
        @ManyToOne val user: User,
        @Id @GeneratedValue val id: Long? = null)