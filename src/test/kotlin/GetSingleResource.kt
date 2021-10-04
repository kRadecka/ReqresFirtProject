import io.restassured.RestAssured.filters
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class GetSingleResource: BaseTest() {

    @Test
    fun getSingleResource(){
        val log = PrintStream(FileOutputStream("getSingleResource.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("unknown/2")
            .then().assertThat().statusCode(200)
            .extract().response().toString()
    }
}