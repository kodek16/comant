package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.UserDao
import com.psenchanka.comant.model.User
import com.psenchanka.comant.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("userService")
@Transactional
class UserServiceImpl @Autowired constructor(val userDao: UserDao) : UserService {

    @Value("\${comant.secret}")
    private lateinit var secret: String;

    override fun save(user: User) = userDao.save(user)
    override fun findAll(): List<User> = userDao.findAll()
    override fun findByUsername(username: String) = userDao.findByUsername(username)
    override fun update(user: User) = userDao.update(user)

    override fun generateAccessToken(user: User)
            = Jwts.builder()
            .setSubject(user.username)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
}