package com.psenchanka.comant.model

import org.mindrot.jbcrypt.BCrypt
import javax.persistence.*

@Entity
@Table(name = "code.users")
class User {
    enum class Role {
        STUDENT,
        TEACHER,
        ADMIN
    }

    enum class Gender {
        MALE,
        FEMALE
    }

    @Id
    lateinit var username: String

    lateinit var password: String

    lateinit var firstname: String
    lateinit var lastname: String

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    lateinit var role: Role

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    lateinit var gender: Gender

    @ManyToMany(mappedBy = "instructors")
    lateinit var coursesInstructed: List<Course>

    @ManyToMany(mappedBy = "listeners")
    lateinit var coursesListened: List<Course>

    fun passwordMatches(plaintext: String) = BCrypt.checkpw(plaintext, password)

    companion object {
        fun encryptPassword(password: String) = BCrypt.hashpw(password, BCrypt.gensalt())
    }
}