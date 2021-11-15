package com.springweb.mvc.model.repo

import com.springweb.mvc.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity?, Int?> {
    fun findByUsername(username: String?): Optional<UserEntity?>?
}