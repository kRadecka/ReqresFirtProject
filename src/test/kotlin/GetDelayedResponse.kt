import data.BaseTest
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class GetDelayedResponse: BaseTest() {

    @Test
    fun getDelayedResponse(){
        var log = PrintStream(FileOutputStream("getDelayedResponse.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .queryParam("delay","3")
            .`when`().get("users")
            .then().assertThat().statusCode(200)
            .extract().response().asString()
    }
}