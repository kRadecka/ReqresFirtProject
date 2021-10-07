import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.hamcrest.Matchers.greaterThan
import org.junit.Test

class GetDelayedResponse: BaseTest() {

    @Test
    fun getDelayedResponse(){
        val getDelayedResponse =  given().spec(requestSpecification)
            .queryParam("delay","3")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
            Payload().log(getDelayedResponse)
    }

    @Test
    fun getEmptyPageDelayedResponse(){
        val getEmptyPageDelayedResponseResponse =  given().spec(requestSpecification)
            .queryParam("delay","")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getEmptyPageDelayedResponseResponse)
    }

    @Test
    fun getEmptyDelayedResponse(){
        val getEmptyDelayedResponseResponse =  given().spec(requestSpecification)
            .queryParam("delay","8888888888888888888888888888888888888")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getEmptyDelayedResponseResponse)
    }

    @Test
    fun getToLongDelayedResponse(){
        val getDelayedResponse =  given().spec(requestSpecification)
            .queryParam("delay",Payload().veryLongPage())
            .`when`().get("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(getDelayedResponse)
    }
}