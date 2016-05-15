package com.psenchanka.comant.model

import org.mindrot.jbcrypt.BCrypt
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "code.users")
class User {
    @Id
    lateinit var username: String;

    lateinit var password: String;

    fun passwordMatches(plaintext: String) = BCrypt.checkpw(plaintext, password);

    companion object {
        fun encryptPassword(password: String) = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}