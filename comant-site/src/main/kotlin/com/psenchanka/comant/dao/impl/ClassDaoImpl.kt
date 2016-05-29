package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.ClassDao
import com.psenchanka.comant.model.Class
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("classDao")
open class ClassDaoImpl @Autowired constructor(sessionFactory: SessionFactory)
         : ClassDao, AbstractDaoImpl<Class, Int>(sessionFactory, Class::class.java)