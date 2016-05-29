package com.psenchanka.comant.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "code.classes")
class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "starts_on")
    lateinit var startsOn: LocalDateTime
    @Column(name = "ends_on")
    lateinit var endsOn: LocalDateTime

    var name: String? = null
    var description: String? = null

    @ManyToOne
    lateinit var course: Course

    @ManyToMany
    @JoinTable(name = "code.class_absences",
               joinColumns = arrayOf(JoinColumn(name = "class_id", nullable = false)),
               inverseJoinColumns = arrayOf(JoinColumn(name = "listener_username", nullable = false)))
    lateinit var absences: MutableList<User>

    @OneToMany(mappedBy = "class_")
    lateinit var links: MutableList<Link>

    @OneToMany(mappedBy = "class_")
    lateinit var grades: MutableList<ClassGrade>
}