import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.junit.Test
import org.testng.Assert
import java.io.FileOutputStream
import java.io.PrintStream

class PutUpdateUser :BaseTest() {

    @Test
    fun putUpdateUser(){

        val log = PrintStream(FileOutputStream("putUpdateUser.txt"))


       val putUpdateUserResponse:String = given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().updateUser())
            .`when`().put("users/2")
            .then().assertThat().statusCode(200)
            .extract().response().asString()


        val js1:JsonPath = rawToJson(putUpdateUserResponse)
        val actualJob:String = js1.getString("job")
        println(actualJob)

        val js2:JsonPath = rawToJson(Payload().updateUser())
        val newJob: String = js2.getString("job")
        println(newJob)
        Assert.assertEquals(actualJob,newJob)
    }
    fun rawToJson(resp:String): JsonPath{
        val js1:JsonPath = JsonPath(resp)
        return js1
    }
}