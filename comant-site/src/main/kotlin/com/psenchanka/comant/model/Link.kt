package com.psenchanka.comant.model

import javax.persistence.*

@Entity
@Table(name = "code.links")
class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    lateinit var url: String
    lateinit var name: String
    var comment: String? = null

    @ManyToOne
    @JoinColumn(name = "class_id")
    lateinit var class_: Class

    var order: Int = 0
}