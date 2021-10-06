import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.Test

class GetDelayedResponse: BaseTest() {

    @Test
    fun getDelayedResponse(){
        val getDelayedResponse =  given().spec(requestSpecification)
            .queryParam("delay","3")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
            Payload().log(getDelayedResponse)
    }
}