package com.springweb.mvc.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RoleEntity(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val name: String? = null
)