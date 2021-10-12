import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.path.json.JsonPath
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
class RegisterOptionsTest: BaseTest() {

    @Test
    fun postRegisterSuccessful(){

        val postRegisterSuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().registerUser())
            .`when`().post("register")
            .then().assertThat().statusCode(200).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(postRegisterSuccessfulResponse)
    }

    @Test
    fun postRegisterEmptyBody(){

        val postRegisterEmptyBodyResponse = given().spec(requestSpecification)
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(postRegisterEmptyBodyResponse)

        val js2: JsonPath = Payload().rawToJson(postRegisterEmptyBodyResponse)
        val actualError2: String = js2.getString("error")
        println(actualError2)

        Assert.assertEquals(actualError2, "Missing email or username")
    }

    @Test
    fun postRegisterIncorrectBodyFormatting(){

        val postRegisterIncorrectBodyFormattingResponse = given().spec(requestSpecification)
            .body(Payload().incorrectFormatting())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postRegisterIncorrectBodyFormattingResponse)
    }

    @Test
    fun postRegisterIncorrectBodyFormatting2(){

        val postRegisterIncorrectBodyFormatting2Response = given().spec(requestSpecification)
            .body(Payload().incorrectFormatting2())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postRegisterIncorrectBodyFormatting2Response)
    }

    @Test
    fun postRegisterVeryLongBody(){

        val postRegisterVeryLongBodyResponse = given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().post("register")
            .then().assertThat().statusCode(413).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postRegisterVeryLongBodyResponse)
    }

    @Test
    fun postRegisterSpecialSymbols(){

        val postRegisterSpecialSymbolsResponse = given().spec(requestSpecification)
            .body(Payload().specialSymbols())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postRegisterSpecialSymbolsResponse)
    }

    @Test
    fun postRegisterWithIncorrectContentType(){

        val postRegisterWithIncorrectContentTypeResponse = given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postRegisterWithIncorrectContentTypeResponse)
    }

    @Test
    fun postRegisterUnsuccessful() {

        val postRegisterUnsuccessfulResponse = given().spec(requestSpecification)
            .body(Payload().incorrectData())
            .`when`().post("register")
            .then().assertThat().statusCode(400).assertThat()
            .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .extract().response().asString()
            Payload().log(postRegisterUnsuccessfulEmptyResponse)

            val js3: JsonPath = Payload().rawToJson(postRegisterUnsuccessfulEmptyResponse)
            val actualError3: String = js3.getString("error")
            println(actualError3)

            Assert.assertEquals(actualError3, "Missing email or username")

        }
    }