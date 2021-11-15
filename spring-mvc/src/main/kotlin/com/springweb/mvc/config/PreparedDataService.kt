package com.springweb.mvc.config

import com.springweb.mvc.model.User
import com.springweb.mvc.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PreparedDataService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    @PostConstruct
    fun onApplicationEvent() {

        val user1 = User(1, "a", passwordEncoder.encode("1"), "ROLE_ADMIN")
        val user2 = User(2, "b", passwordEncoder.encode("2"), "ROLE_API")
        val user3 = User(3, "c", passwordEncoder.encode("3"), "ROLE_APP")

        userRepository.saveAll(listOf(user1, user2, user3))
    }
}
