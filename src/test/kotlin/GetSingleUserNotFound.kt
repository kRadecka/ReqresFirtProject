import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class GetSingleUserNotFound : BaseTest(){

    @Test
    fun getSingleUserNotFound(){
        val log = PrintStream(FileOutputStream("getSingleUserNotFound.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("users/23")
            .then().assertThat().statusCode(404)
            .extract().response().asString()
    }
}