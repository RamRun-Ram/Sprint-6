package com.springweb.mvc.model

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val login: String = "",
    val password: String = "",
    val role: String = "",
)