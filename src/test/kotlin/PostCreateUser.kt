import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class PostCreateUser: BaseTest() {

    @Test
    fun postCreateUser() {
        val log = PrintStream(FileOutputStream("postCreateUser.txt"))


             given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)
            .extract().response().asString()
    }

}