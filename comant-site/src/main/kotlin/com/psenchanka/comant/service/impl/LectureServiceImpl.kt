package com.psenchanka.comant.service.impl

import com.psenchanka.comant.dao.LectureDao
import com.psenchanka.comant.model.Lecture
import com.psenchanka.comant.service.LectureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("lectureService")
open class LectureServiceImpl @Autowired constructor(dao: LectureDao)
         : LectureService, AbstractServiceImpl<Lecture, Int>(dao)