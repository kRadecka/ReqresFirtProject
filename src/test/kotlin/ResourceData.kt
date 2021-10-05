import data.BaseTest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class ResourceData : BaseTest(){

    @Test
    fun getListResource(){
        val log = PrintStream(FileOutputStream("getListResource.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("unknown")
            .then().assertThat().statusCode(200)
            .body("data.size()", greaterThan(0))
            .extract().response()

    }

    @Test
    fun getSingleResource(){
        val log = PrintStream(FileOutputStream("getSingleResource.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("unknown/2")
            .then().assertThat().statusCode(200)
            .body("data.size()", greaterThan(0))
            .extract().response()
    }

    @Test
    fun getSingleResourceNotFound(){
        val log = PrintStream(FileOutputStream("getSingleResourceNotFound.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("unknown/23")
            .then().assertThat().statusCode(404)
            .body("size()", equalTo(0))
            .extract().response()
    }
}