import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.*

class UserOptions: BaseTest() {

    @Test
    fun postCreateUser() {
        val log = PrintStream(FileOutputStream("postCreateUser.txt"))


            given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("size()", Matchers.greaterThan(0))
                .extract().response()

    }

    @Test
    fun postCreateUserEmptyBody() {
        val log = PrintStream(FileOutputStream("postCreateUser.txt"))


        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().post("users")
            .then().assertThat().statusCode(201)
            .body("size()", Matchers.greaterThan(0))
            .extract().response()

    }

    @Test
    fun putUpdateUser(){

        val log = PrintStream(FileOutputStream("putUpdateUser.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().updateUser())
            .`when`().put("users/2")
            .then().assertThat().statusCode(200)
            .body("name", equalTo("morpheus"))
            .body("job", equalTo("zion resident"))
            .body("size()", Matchers.greaterThan(0))
            .extract().response()

    }

    @Test
    fun putUpdateUserEmptyBody(){

        val log = PrintStream(FileOutputStream("putUpdateUser.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().put("users/2")
            .then().assertThat().statusCode(200)
            .body("size()", greaterThan(0))
            .extract().response()

    }

    @Test
    fun patchUpdateUser(){

        val log = PrintStream(FileOutputStream("patchUpdateUser.txt"))


            given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
                .body(Payload().updateUser())
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("size()", Matchers.greaterThan(0))
            .extract().response()

    }

    @Test
    fun patchUpdateUserEmptyBody(){

        val log = PrintStream(FileOutputStream("patchUpdateUser.txt"))


        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200)
            .body("size()", greaterThan(0))
            .extract().response()

    }

    @Test
    fun deleteUser(){
        val log = PrintStream(FileOutputStream("deleteUser.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)
            .extract().response().asString()

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().delete("users/2")
            .then().assertThat().statusCode(204)
            .extract().response()
    }
}