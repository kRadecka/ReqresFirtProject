import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class DeleteUser:BaseTest() {

    @Test
    fun deleteUser(){
        val log = PrintStream(FileOutputStream("deleteUser.txt"))

        given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .`when`().delete("users/2")
            .then().assertThat().statusCode(204)
            .extract().response().asString()
    }
}