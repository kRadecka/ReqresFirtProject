import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
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

         val postCreateUserResponse =  given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201).assertThat()
                .body("name", equalTo("morpheus")).assertThat()
                .body("size()", Matchers.greaterThan(0)).assertThat()
                .contentType(ContentType.JSON).assertThat()
                .header("Content-Length", equalTo("84"))
                .extract().response().asString()
        Payload().log(postCreateUserResponse)

    }

    @Test
    fun postCreateUserEmptyBody() {


        val postCreateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().post("users")
            .then().assertThat().statusCode(201).assertThat()
            .body("size()", Matchers.greaterThan(0)).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", equalTo("51"))
            .extract().response().asString()
        Payload().log(postCreateUserEmptyBodyResponse)

    }

    @Test
    fun putUpdateUser(){

        val putUpdateUserResponse =    given().spec(requestSpecification)
            .body(Payload().updateUser())
            .`when`().put("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("name", equalTo("morpheus")).assertThat()
            .body("job", equalTo("zion resident")).assertThat()
            .body("size()", Matchers.greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(putUpdateUserResponse)

    }

    @Test
    fun putUpdateUserEmptyBody(){

        val putUpdateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().put("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", equalTo("40"))
            .extract().response().asString()
        Payload().log(putUpdateUserEmptyBodyResponse)

    }

    @Test
    fun patchUpdateUser(){


        val patchUpdateUserResponse =    given().spec(requestSpecification)
                .body(Payload().updateUser())
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200).assertThat()
                .body("name", equalTo("morpheus")).assertThat()
                .body("job", equalTo("zion resident")).assertThat()
                .body("size()", Matchers.greaterThan(0)).assertThat()
                .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(patchUpdateUserResponse)

    }

    @Test
    fun patchUpdateUserEmptyBody(){


        val patchUpdateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", equalTo("40"))
            .extract().response().asString()
        Payload().log(patchUpdateUserEmptyBodyResponse)

    }

    @Test
    fun deleteUser(){

        given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)

        val deleteUserResponse =   given().spec(requestSpecification)
            .`when`().delete("users/2")
            .then().assertThat().statusCode(204).assertThat()
            .header("Content-Length", equalTo("0"))
            .extract().response().asString()
        Payload().log(deleteUserResponse)
    }
}