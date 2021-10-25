package com.springweb.mvc.filter

import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter("/app/*", "/api/*")
class AuthentificationFilter : HttpFilter() {

    override fun doFilter(req: HttpServletRequest?, res: HttpServletResponse?, chain: FilterChain?) {
        val cookies = req!!.cookies
        if (cookies == null) {
            res!!.sendRedirect("/login")
        } else {
            for (cookie in cookies) {
                when {
                    cookie.name != "auth" -> {
                        res!!.sendRedirect("/login")
                    }
                    cookie.value > LocalDateTime.now().toString() -> {
                        res!!.sendRedirect("/login")
                    }
                    else -> {
                        chain!!.doFilter(req, res)
                    }
                }
            }
        }
    }
}