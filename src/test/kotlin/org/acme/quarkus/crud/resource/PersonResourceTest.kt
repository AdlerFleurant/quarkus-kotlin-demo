package org.acme.quarkus.crud.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
class PersonResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/persons")
            .then()
            .statusCode(200)
    }
}