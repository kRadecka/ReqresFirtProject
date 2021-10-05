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

class LoginOptions :BaseTest() {
    var log = PrintStream(FileOutputStream("postLoginSuccessful.txt"))

    @Test
    fun postLoginSuccessful() {


        val postLoginSuccessfulResponse = RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().loginSuccessful())
            .`when`().post("login")
            .then().assertThat().statusCode(200)
            .extract().response().asString()


        val js1: JsonPath = Payload().rawToJson(postLoginSuccessfulResponse)
        val actualToken: String = js1.getString("token")
        println(actualToken)

        Assert.assertEquals(actualToken, "QpwL5tke4Pnpja7X4")
    }
    @Test
    fun postLoginUnsuccessful() {
        var log = PrintStream(FileOutputStream("postLoginUnsuccessful.txt"))

        val postLoginUnsuccessfulResponse = RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().loginUnuccessful())
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js1: JsonPath = Payload().rawToJson(postLoginUnsuccessfulResponse)
        val actualError: String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError, "Missing password")
    }

    @Test
    fun postLoginUnsuccessfulPasswordResponse() {

        val postLoginUnsuccessfulPasswordResponse = RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().loginUnuccessful2())
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js2: JsonPath = Payload().rawToJson(postLoginUnsuccessfulPasswordResponse)
        val actualError2: String = js2.getString("error")
        println(actualError2)

        Assert.assertEquals(actualError2, "Missing email or username")
    }

    @Test
    fun postLoginUnsuccessfulEmptyResponse(){
        val postLoginUnsuccessfulEmptyResponse = RestAssured.given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body("")
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js3 : JsonPath = Payload().rawToJson(postLoginUnsuccessfulEmptyResponse)
        val actualError3 :String = js3.getString("error")
        println(actualError3)

        Assert.assertEquals(actualError3,"Missing email or username")

    }
}