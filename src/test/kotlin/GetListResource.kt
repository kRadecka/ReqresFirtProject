import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class GetListResource :BaseTest(){

    @Test
    fun getListResource(){
        val log = PrintStream(FileOutputStream("getListResource.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().get("unknown")
            .then().assertThat().statusCode(200)
            .extract().response().asString()

    }
}