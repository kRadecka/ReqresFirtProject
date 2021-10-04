import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class GetSingleUser: BaseTest() {

    @Test
    fun getSingleUser(){

        val log = PrintStream(FileOutputStream("getSingleUser.txt"))
        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("users/2")
            .then().assertThat().statusCode(200)
            .extract().response().asString()
    }
}