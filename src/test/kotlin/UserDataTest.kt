import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.*
import org.junit.Test

class UserDataTest :BaseTest() {

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
    fun getEmptyPageUserList() {

        val getUserListResponse =  given().spec(requestSpecification)
            .queryParam("page", "")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getUserListResponse)
    }

    @Test
    fun getEmptyUserList() {

        val getEmptyUserListResponse =  given().spec(requestSpecification)
            .queryParam("page", "8888888888888888888888888888888888888")
            .`when`().get("users")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , equalTo(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getEmptyUserListResponse)
    }

    @Test
    fun getToLongUserList() {

        val getToLongUserListResponse =  given().spec(requestSpecification)
            .queryParam("page", Payload().veryLongPage())
            .`when`().get("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(getToLongUserListResponse)
    }

    @Test
    fun getSingleUser(){

        val getSingleUserResponse =  given().spec(requestSpecification)
            .`when`().get("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()" , greaterThan(0)).assertThat()
            .body("data.size()", equalTo(5)).assertThat()
            .body("data.id", equalTo(2)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getSingleUserResponse)
    }

    @Test
    fun getZeroPageSingleUser(){

        val getSingleUserResponse =  given().spec(requestSpecification)
            .`when`().get("users/0")
            .then().assertThat().statusCode(404).assertThat()
            .body("size()", equalTo(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getSingleUserResponse)
    }


    @Test
    fun getToLongSingleUserList() {

        val getToLongSingleUserListResponse =  given().spec(requestSpecification)
            .`when`().get(Payload().veryLongPageForSingleUser())
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(getToLongSingleUserListResponse)
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

