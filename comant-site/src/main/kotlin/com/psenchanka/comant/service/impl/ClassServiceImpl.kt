package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.ClassDao
import com.psenchanka.comant.model.Class
import com.psenchanka.comant.service.ClassService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("classService")
open class ClassServiceImpl @Autowired constructor(dao: ClassDao)
         : ClassService, AbstractServiceImpl<Class, Int>(dao)