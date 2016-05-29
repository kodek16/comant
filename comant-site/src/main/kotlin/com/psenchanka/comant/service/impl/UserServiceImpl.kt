package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.UserDao
import com.psenchanka.comant.model.User
import com.psenchanka.comant.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service("userService")
open class UserServiceImpl @Autowired constructor(dao: UserDao)
         : UserService, AbstractServiceImpl<User, String>(dao) {

    @Value("\${comant.secret}")
    private lateinit var secret: String

    override fun generateAccessToken(user: User)
            = Jwts.builder()
            .setSubject(user.username)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
}