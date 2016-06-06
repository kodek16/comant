package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Lecture
import java.time.LocalDateTime

open class BasicLectureDto(
        var id: Int?,
        var name: String,
        var author: BasicUserDto,
        var lastUpdate: LocalDateTime) {

    companion object {
        fun from(lecture: Lecture) = BasicLectureDto(
                lecture.id,
                lecture.name,
                BasicUserDto.from(lecture.author),
                lecture.lastUpdate)
    }
}