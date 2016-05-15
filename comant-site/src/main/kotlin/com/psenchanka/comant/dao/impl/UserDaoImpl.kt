package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.UserDao
import com.psenchanka.comant.model.User
import org.hibernate.SessionFactory
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("userDao")
open class UserDaoImpl @Autowired constructor(val sessionFactory: SessionFactory) : UserDao {

    override fun save(user: User)
            = sessionFactory.currentSession.persist(user);

    override fun findAll(): List<User>
            = sessionFactory.currentSession
            .createCriteria(User::class.java)
            .list() as List<User>;

    override fun findByUsername(username: String): User?
            = sessionFactory.currentSession
            .createCriteria(User::class.java)
            .add(Restrictions.eq("username", username))
            .uniqueResult() as User?;

    override fun update(user: User)
            = sessionFactory.currentSession
            .update(user);
}