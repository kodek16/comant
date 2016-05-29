package com.psenchanka.comant.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "code.class_grades")
//@IdClass(ClassGrade.PrimaryKey::class)
class ClassGrade {

    @Embeddable
    class PrimaryKey : Serializable {
        @Column(name = "class_id")
        var classId: Int = 0;

        @Column(name = "listener_username")
        lateinit var listenerUsername: String;
    }

    @EmbeddedId
    lateinit var id: PrimaryKey

    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    lateinit var class_: Class;

    @ManyToOne
    @MapsId("listenerUsername")
    @JoinColumn(name = "listener_username", referencedColumnName = "username")
    lateinit var listener: User;

    lateinit var grade: String;
}