package com.springweb.mvc.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class AddressBookModel {
    @Id
    @GeneratedValue
    val id: Int? = null

    @Column(unique = true, nullable = false)
    val name: String? = null
    val address: String? = null
    val telephone: String? = null
}