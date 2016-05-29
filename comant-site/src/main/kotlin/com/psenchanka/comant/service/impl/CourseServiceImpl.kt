package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.CourseDao
import com.psenchanka.comant.model.Course
import com.psenchanka.comant.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("courseService")
open class CourseServiceImpl @Autowired constructor(dao: CourseDao)
         : CourseService, AbstractServiceImpl<Course, Int>(dao)