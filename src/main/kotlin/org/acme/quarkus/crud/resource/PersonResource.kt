package org.acme.quarkus.crud.resource

import org.acme.quarkus.crud.entity.Person
import java.net.URI
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.Response


@Path("/persons")
class PersonResource {

    @GET
    fun list(): List<Person> {
        return Person.listAll()
    }

    @GET
    @Path("/{id}")
    operator fun get(@PathParam("id") id: Long): Person {
        return Person.findById(id) ?: throw NotFoundException()
    }

    @POST
    @Transactional
    fun create(person: Person): Response {
        person.persist()
        return Response.created(URI.create("/persons/" + person.id)).build()
    }

    @PUT
    @Path("/{id}")
    @Transactional
    fun update(@PathParam("id") id: Long, person: Person): Person {
        val entity: Person = Person.findById(id) ?: throw NotFoundException()

        // map all fields from the person parameter to the existing entity
        entity.name = person.name
        entity.birth = person.birth
        return entity
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long) {
        val entity: Person = Person.findById(id) ?: throw NotFoundException()
        entity.delete()
    }

    @GET
    @Path("/search/{name}")
    fun search(@PathParam("name") name: String): Person? {
        return Person.findByName(name)
    }

    @GET
    @Path("/count")
    fun count(): Long {
        return Person.count()
    }
}

