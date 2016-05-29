package com.psenchanka.comant.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "code.courses")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    lateinit var name: String
    lateinit var description: String

    lateinit var startsOn: LocalDate
    lateinit var endsOn: LocalDate

    @ManyToMany
    @JoinTable(name = "code.course_instructors",
               joinColumns = arrayOf(JoinColumn(name = "course_id", nullable = false)),
               inverseJoinColumns = arrayOf(JoinColumn(name = "user_username", nullable = false)))
    lateinit var instructors: MutableList<User>

    @ManyToMany
    @JoinTable(name = "code.course_listeners",
               joinColumns = arrayOf(JoinColumn(name = "course_id", nullable = false)),
               inverseJoinColumns = arrayOf(JoinColumn(name = "user_username", nullable = false)))
    lateinit var listeners: MutableList<User>

    @OneToMany(mappedBy = "course")
    lateinit var classes: MutableList<Class>

    fun isOngoing() = startsOn <= LocalDate.now() && LocalDate.now() <= endsOn
    fun isPast() = endsOn < LocalDate.now()
    fun isNew() = LocalDate.now() < startsOn
}
