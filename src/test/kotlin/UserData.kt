import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.*
import org.junit.Test

class UserData :BaseTest() {

    @Test
    fun getUserList() {

        val getUserListResponse =  given().spec(requestSpecification)
            .queryParam("page", "2")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
                 .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getUserListResponse)

    }

    @Test
    fun getSingleUser(){

        val getSingleUserResponse =  given().spec(requestSpecification)
            .`when`().get("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getSingleUserResponse)

    }

    @Test
    fun getSingleUserNotFound(){

       val getSingleUserNotFoundResponse = given().spec(requestSpecification)
            .`when`().get("users/23")
            .then().assertThat().statusCode(404).assertThat()
            .body("size()" , equalTo(0)).assertThat()
           .contentType(ContentType.JSON).assertThat()
           .header("Content-Length", equalTo("2"))
            .extract().response().asString()
        Payload().log(getSingleUserNotFoundResponse)
    }



}

