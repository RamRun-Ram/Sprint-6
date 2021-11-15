package com.springweb.mvc.model.entity

import javax.persistence.*


@Entity
class UserEntity(
    @Id
    @GeneratedValue
    val id: Int? = null,

    val username: String? = null,

    val password: String? = null,
    @ManyToOne
    @JoinColumn(name="role_id")
    val role: RoleEntity? = null,
)