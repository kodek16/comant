package com.psenchanka.comant.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor : HandlerInterceptorAdapter() {

    @Value("\${comant.secret}")
    private lateinit var secret: String

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val authorizationHeader = request.getHeader("Authorization")

        val jwtToken =
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    authorizationHeader.substring("Bearer ".length)
                } else {
                    null
                }

        val userName =
                if (jwtToken == null) {
                    null
                } else try {
                    Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).body.subject
                } catch (e: SignatureException) {
                    null
                }

        request.setAttribute("com.psenchanka.comant.authenticatedUser", userName)

        //Request should be passed further.
        return true
    }
}
