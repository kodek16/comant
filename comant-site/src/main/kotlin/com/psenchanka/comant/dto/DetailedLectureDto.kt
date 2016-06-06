package com.psenchanka.comant.dto

import com.psenchanka.comant.model.Lecture
import java.time.LocalDateTime

open class DetailedLectureDto(
        id: Int?,
        name: String,
        author: BasicUserDto,
        lastUpdate: LocalDateTime,
        var text: String
) : BasicLectureDto(id, name, author, lastUpdate) {

    companion object {
        fun from(lecture: Lecture) = DetailedLectureDto(
                lecture.id,
                lecture.name,
                BasicUserDto.from(lecture.author),
                lecture.lastUpdate,
                lecture.text)
    }
}