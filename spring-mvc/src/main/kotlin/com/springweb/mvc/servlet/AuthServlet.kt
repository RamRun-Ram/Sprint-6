package com.springweb.mvc.servlet

import java.time.LocalDateTime
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AuthServlet", urlPatterns = ["/login"])
class AuthServlet : HttpServlet() {
    private val login = "admin"
    private val password = "12345"

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.getRequestDispatcher("login.html").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        if (login == req?.getParameter("login") && password == req?.getParameter("password")) {
            val cookie = Cookie("auth", LocalDateTime.now().toString())
            resp!!.addCookie(cookie)
            resp.sendRedirect("/app/add")
        } else {
            resp!!.sendRedirect("/login")
        }
    }
}