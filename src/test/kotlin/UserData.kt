import data.BaseTest
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.apache.commons.lang3.ArrayUtils.isNotEmpty
import org.apache.commons.lang3.ObjectUtils.isNotEmpty
import org.apache.commons.lang3.StringUtils.isNotEmpty
import org.hamcrest.Matchers.*
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class UserData :BaseTest() {

    @Test
    fun getUserList() {

        val log = PrintStream(FileOutputStream("getUserList.txt"))

             given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .queryParam("page", "2")
            .`when`().get("users")
            .then().assertThat().statusCode(200)
                 .body("data.size()" , greaterThan(0))
            .extract().response()

    }

    @Test
    fun getSingleUser(){

        val log = PrintStream(FileOutputStream("getSingleUser.txt"))
        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("users/2")
            .then().assertThat().statusCode(200)
            .body("data.size()" , greaterThan(0))
            .extract().response()
    }

    @Test
    fun getSingleUserNotFound(){
        val log = PrintStream(FileOutputStream("getSingleUserNotFound.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("users/23")
            .then().assertThat().statusCode(404)
            .body("size()" , equalTo(0))
            .extract().response()
    }



}

