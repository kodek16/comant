package com.psenchanka.comant.dao.impl

import com.psenchanka.comant.dao.LectureDao
import com.psenchanka.comant.model.Lecture
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("lectureDao")
open class LectureDaoImpl @Autowired constructor(sessionFactory: SessionFactory)
         : LectureDao, AbstractDaoImpl<Lecture, Int>(sessionFactory, Lecture::class.java)