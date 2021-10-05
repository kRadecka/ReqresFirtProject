import data.BaseTest
import data.Payload
import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.junit.Assert
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class RegisteOptions: BaseTest() {
    val log = PrintStream(FileOutputStream("postRegisterUnsuccessful.txt"))

    @Test
    fun postRegisterSuccessful(){
        val log = PrintStream(FileOutputStream("postRegisterSuccessful.txt"))

        RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().registerUser())
            .`when`().post("register")
            .then().assertThat().statusCode(200)
            .extract().response()
    }

    @Test
    fun postRegisterUnsuccessful() {


        val postRegisterUnsuccessfulResponse = RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().incorrectData())
            .`when`().post("register")
            .then().assertThat().statusCode(400)
            .extract().response().asString()


        val js1: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulResponse)
        val actualError: String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError, "Missing password")
    }
        @Test
        fun postRegisterUnsuccessfulPasswordResponse() {
            val postRegisterUnsuccessfulPasswordResponse = RestAssured.given().spec(requestSpecification)
                .filters(RequestLoggingFilter.logRequestTo(log))
                .filters(ResponseLoggingFilter.logResponseTo(log))
                .body(Payload().incorrectData2())
                .`when`().post("register")
                .then().assertThat().statusCode(400)
                .extract().response().asString()


            val js2: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulPasswordResponse)
            val actualError2: String = js2.getString("error")
            println(actualError2)

            Assert.assertEquals(actualError2, "Missing email or username")
        }

        @Test
        fun postRegisterUnsuccessfulEmptyResponse() {
            val postRegisterUnsuccessfulEmptyResponse = RestAssured.given().spec(requestSpecification)
                .filters(RequestLoggingFilter.logRequestTo(log))
                .filters(ResponseLoggingFilter.logResponseTo(log))
                .body("")
                .`when`().post("register")
                .then().assertThat().statusCode(400)
                .extract().response().asString()

            val js3: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulEmptyResponse)
            val actualError3: String = js3.getString("error")
            println(actualError3)

            Assert.assertEquals(actualError3, "Missing email or username")

        }
    }