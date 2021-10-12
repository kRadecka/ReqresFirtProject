import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.json.JsonPath
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test

class LoginOptionsTest :BaseTest() {

    @Test
    fun postLoginSuccessful() {

        val postLoginSuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().loginSuccessful())
            .`when`().post("login")
            .then().assertThat().statusCode(200).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("29"))
            .extract().response().asString()
        Payload().log(postLoginSuccessfulResponse)


        val js1: JsonPath = Payload().rawToJson(postLoginSuccessfulResponse)
        val actualToken: String = js1.getString("token")
        println(actualToken)

        Assert.assertEquals(actualToken, "QpwL5tke4Pnpja7X4")
    }

    @Test
    fun postLoginUnsuccessfulEmptyBody(){
        val postLoginUnsuccessfulEmptyResponse = given().spec(requestSpecification)
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("37"))
            .extract().response().asString()
        Payload().log(postLoginUnsuccessfulEmptyResponse)

        val js3 : JsonPath = Payload().rawToJson(postLoginUnsuccessfulEmptyResponse)
        val actualError3 :String = js3.getString("error")
        println(actualError3)

        Assert.assertEquals(actualError3,"Missing email or username")

    }

    @Test
    fun postLoginIncorrectBodyFormatting() {

        val postLoginIncorrectBodyFormattingResponse = given().spec(requestSpecification)
            .body(Payload().incorrectFormatting())
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postLoginIncorrectBodyFormattingResponse)
    }

    @Test
    fun postLoginIncorrectBodyFormatting2() {

        val postLoginIncorrectBodyFormatting2Response = given().spec(requestSpecification)
            .body(Payload().incorrectFormatting2())
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postLoginIncorrectBodyFormatting2Response)
    }

    @Test
    fun postLoginVeryLongBody() {

        val postLoginVeryLongBodyResponse = given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().post("login")
            .then().assertThat().statusCode(413)
            .extract().response().asString()
        Payload().log(postLoginVeryLongBodyResponse)
    }

    @Test
    fun postLoginSpecialSymbols() {

        val postLoginSpecialSymbolsResponse = given().spec(requestSpecification)
            .body(Payload().specialSymbols())
            .`when`().post("login")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(postLoginSpecialSymbolsResponse)
    }

    @Test
    fun postLoginWithIncorrectContentType() {

        val postLoginWithIncorrectContentTypeResponse = given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postLoginWithIncorrectContentTypeResponse)
    }

    @Test
    fun postLoginUnsuccessful() {

        val postLoginUnsuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().loginUnuccessful())
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("28"))
            .extract().response().asString()
        Payload().log(postLoginUnsuccessfulResponse)

        val js1: JsonPath = Payload().rawToJson(postLoginUnsuccessfulResponse)
        val actualError: String = js1.getString("error")
        println(actualError)

        Assert.assertEquals(actualError, "Missing password")
    }

    @Test
    fun postLoginUnsuccessfulPasswordResponse() {

        val postLoginUnsuccessfulPasswordResponse = given().spec(requestSpecification)
            .body(Payload().loginUnuccessful2())
            .`when`().post("login")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", Matchers.equalTo("37"))
            .extract().response().asString()
        Payload().log(postLoginUnsuccessfulPasswordResponse)

        val js2: JsonPath = Payload().rawToJson(postLoginUnsuccessfulPasswordResponse)
        val actualError2: String = js2.getString("error")
        println(actualError2)

        Assert.assertEquals(actualError2, "Missing email or username")
    }


}