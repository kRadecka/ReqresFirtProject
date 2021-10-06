import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.json.JsonPath
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import java.io.FileOutputStream
import java.io.PrintStream

class RegisterOptions: BaseTest() {


    @Test
    fun postRegisterSuccessful(){
        val log = PrintStream(FileOutputStream("postRegisterSuccessful.txt"))

        val postRegisterSuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().registerUser())
            .`when`().post("register")
            .then().assertThat().statusCode(200).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("36"))
            .extract().response().asString()
        Payload().log(postRegisterSuccessfulResponse)
    }

    @Test
    fun postRegisterUnsuccessful() {


        val postRegisterUnsuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().incorrectData())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("28"))
            .extract().response().asString()
        Payload().log(postRegisterUnsuccessfulResponse)


        val js1: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulResponse)
        val actualError: String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError, "Missing password")
    }
        @Test
        fun postRegisterUnsuccessfulPasswordResponse() {
            val postRegisterUnsuccessfulPasswordResponse = given().spec(requestSpecification)
                .body(Payload().incorrectData2())
                .`when`().post("register")
                .then().assertThat().statusCode(400).assertThat()
                .contentType(ContentType.JSON).assertThat()
                .header("Content-Length", Matchers.equalTo("37"))
                .extract().response().asString()
            Payload().log(postRegisterUnsuccessfulPasswordResponse)


            val js2: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulPasswordResponse)
            val actualError2: String = js2.getString("error")
            println(actualError2)

            Assert.assertEquals(actualError2, "Missing email or username")
        }

        @Test
        fun postRegisterUnsuccessfulEmptyResponse() {
            val postRegisterUnsuccessfulEmptyResponse = given().spec(requestSpecification)
                .body("")
                .`when`().post("register")
                .then().assertThat().statusCode(400).assertThat()
                .contentType(ContentType.JSON).assertThat()
                .header("Content-Length", Matchers.equalTo("37"))
                .extract().response().asString()
            Payload().log(postRegisterUnsuccessfulEmptyResponse)

            val js3: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulEmptyResponse)
            val actualError3: String = js3.getString("error")
            println(actualError3)

            Assert.assertEquals(actualError3, "Missing email or username")

        }
    }