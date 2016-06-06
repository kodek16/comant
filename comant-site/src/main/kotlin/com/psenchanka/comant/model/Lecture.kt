package com.psenchanka.comant.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "code.lectures")
class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    lateinit var author: User

    @Column
    lateinit var name: String

    @Column(name = "last_update")
    lateinit var lastUpdate: LocalDateTime

    @Column
    lateinit var text: String
}