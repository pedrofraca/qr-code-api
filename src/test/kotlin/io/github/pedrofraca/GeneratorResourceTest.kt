package io.github.pedrofraca

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test


@QuarkusTest
class GeneratorResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`()
            .contentType(ContentType.JSON)
            .body("{\"data\" : \"patata\"}")
            .post("/generate")
          .then()
             .statusCode(200)
    }

}