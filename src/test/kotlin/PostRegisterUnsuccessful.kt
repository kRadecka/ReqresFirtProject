import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import org.junit.Test
import org.testng.Assert
import java.io.FileOutputStream
import java.io.PrintStream

class PostRegisterUnsuccessful: BaseTest() {

    @Test
    fun postRegisterUnsuccessful(){
        val log = PrintStream(FileOutputStream("postRegisterUnsuccessful.txt"))

      val postRegisterUnsuccessfulResponse =   given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().incorrectData())
            .`when`().post("register")
            .then().assertThat().statusCode(400)
            .extract().response().asString()


        val js1 : JsonPath = Payload().rawToJson(postRegisterUnsuccessfulResponse)
        val actualError :String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError,"Missing password")

        val postRegisterUnsuccessfulPasswordResponse =   given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body(Payload().incorrectData2())
            .`when`().post("register")
            .then().assertThat().statusCode(400)
            .extract().response().asString()


        val js2 : JsonPath = Payload().rawToJson(postRegisterUnsuccessfulPasswordResponse)
        val actualError2 :String = js2.getString("error")
        println(actualError2)

        Assert.assertEquals(actualError2,"Missing email or username")

       val postRegisterUnsuccessfulEmptyResponse = given().spec(requestSpecification)
            .filters(RequestLoggingFilter.logRequestTo(log))
            .filters(ResponseLoggingFilter.logResponseTo(log))
            .body("")
            .`when`().post("register")
            .then().assertThat().statusCode(400)
            .extract().response().asString()

        val js3 : JsonPath = Payload().rawToJson(postRegisterUnsuccessfulEmptyResponse)
        val actualError3 :String = js3.getString("error")
        println(actualError3)

        Assert.assertEquals(actualError3,"Missing email or username")

    }
}