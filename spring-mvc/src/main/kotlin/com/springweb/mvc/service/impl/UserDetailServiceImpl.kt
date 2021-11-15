package com.springweb.mvc.service.impl

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import javax.annotation.PostConstruct
import com.springweb.mvc.model.entity.RoleEntity
import com.springweb.mvc.model.entity.UserEntity
import com.springweb.mvc.model.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import kotlin.Throws
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class UserDetailServiceImpl : UserDetailsService {
    @Autowired
    val userRepository: UserRepository? = null
    @Autowired
    val passwordEncoder: PasswordEncoder? = null
    @PostConstruct
    fun prepareData() {
        if (userRepository!!.findAll().isEmpty()) {
            val preparedUsers = listOf(
                UserEntity(1, "user1", passwordEncoder!!.encode("1234"), null),
                UserEntity(
                    2, "user2", passwordEncoder!!.encode("4321"),
                    RoleEntity(1, "ROLE_ADMIN")
                ),
                UserEntity(
                    3, "user3", passwordEncoder!!.encode("qwer"),
                    RoleEntity(2, "ROLE_USER")
                )
            )
            userRepository!!.saveAll(preparedUsers)
        }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository!!.findByUsername(username)
            ?.orElseThrow(Supplier {
                UsernameNotFoundException(
                    String.format(
                        "User not found by username: %s",
                        username
                    )
                )
            })!!
        return User(
            userEntity.username, userEntity.password, true, true,
            true, true, getAuthorities(userEntity.role)
        )
    }

    fun getAuthorities(role: RoleEntity?): Collection<GrantedAuthority?> {
        return if (role == null) emptyList() else listOf(SimpleGrantedAuthority(role.name))
    }
}