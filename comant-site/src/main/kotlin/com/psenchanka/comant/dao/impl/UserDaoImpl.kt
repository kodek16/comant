package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.UserDao
import com.psenchanka.comant.model.User
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("userDao")
open class UserDaoImpl @Autowired constructor(sessionFactory: SessionFactory)
         : UserDao, AbstractDaoImpl<User, String>(sessionFactory, User::class.java)