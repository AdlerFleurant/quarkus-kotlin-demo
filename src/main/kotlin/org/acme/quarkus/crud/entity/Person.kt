package org.acme.quarkus.crud.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import javax.persistence.Entity

@Entity
class Person: PanacheEntity() {
    companion object: PanacheCompanion<Person> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun deleteByName(name: String) = delete("name", name)
    }

    lateinit var name: String
    lateinit var birth: LocalDate
}