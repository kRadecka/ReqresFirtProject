import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.junit.Test
import org.testng.Assert
import java.io.FileOutputStream
import java.io.PrintStream

class PostLoginUnsuccessful: BaseTest() {

    @Test
    fun postLoginUnsuccessful(){
        var log = PrintStream(FileOutputStream("postLoginUnsuccessful.txt"))

        val postLoginUnsuccessfulResponse = given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().loginUnuccessful())
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js1 : JsonPath = Payload().rawToJson(postLoginUnsuccessfulResponse)
        val actualError :String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError,"Missing password")

        val postLoginUnsuccessfulPasswordResponse = given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().loginUnuccessful2())
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js2 : JsonPath = Payload().rawToJson(postLoginUnsuccessfulPasswordResponse)
        val actualError2 :String = js2.getString("error")
        println(actualError2)

        Assert.assertEquals(actualError2,"Missing email or username")

        val postLoginUnsuccessfulEmptyResponse = given().spec(requestSpecification)
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