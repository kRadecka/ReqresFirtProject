import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.junit.Test
import org.testng.Assert
import java.io.FileOutputStream
import java.io.PrintStream

class PatchUpdateUser :BaseTest() {


        @Test
        fun patchUpdateUser(){

            val log = PrintStream(FileOutputStream("patchUpdateUser.txt"))


            val putUpdateUserResponse:String = RestAssured.given().spec(requestSpecification)
                .filters(RequestLoggingFilter.logRequestTo(log))
                .filters(ResponseLoggingFilter.logResponseTo(log))
                .body(Payload().updateUser())
                .`when`().patch("users/2")
                .then().assertThat().statusCode(200)
                .extract().response().asString()


            val js1: JsonPath = rawToJson(putUpdateUserResponse)
            val actualJob:String = js1.getString("job")
            val actualName:String = js1.getString("name")
            println(actualJob)
            println(actualName)

            val js2: JsonPath = rawToJson(Payload().updateUser())
            val newJob: String = js2.getString("job")
            val newName: String =js2.getString("name")
            println(newJob)
            println(newName)

            Assert.assertEquals(actualJob,newJob)
            Assert.assertEquals(actualName,newName)
        }
        fun rawToJson(resp:String): JsonPath {
            val js1: JsonPath = JsonPath(resp)
            return js1
        }
    }
