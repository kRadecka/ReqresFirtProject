import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.junit.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
class UserOptionsTest: BaseTest() {

    @Test
    fun postCreateUser() {

         val postCreateUserResponse =  given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201).assertThat()
                .body("name", equalTo("morpheus")).assertThat()
                .body("size()", greaterThan(0)).assertThat()
                .contentType(ContentType.JSON)
                .extract().response().asString()
        Payload().log(postCreateUserResponse)
    }

    @Test
    fun postCreateUserEmptyBody() {

        val postCreateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().post("users")
            .then().assertThat().statusCode(201).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .extract().response().asString()
        Payload().log(postCreateUserEmptyBodyResponse)
    }

    @Test
    fun postCreateUserIncorrectBodyFormatting() {

        val postCreateUserIncorrectBodyFormattingResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting())
            .`when`().post("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(postCreateUserIncorrectBodyFormattingResponse)
    }

    @Test
    fun postCreateUserIncorrectBodyFormatting2() {

        val postCreateUserIncorrectBodyFormatting2Response =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting2())
            .`when`().post("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(postCreateUserIncorrectBodyFormatting2Response)
    }

    @Test
    fun postCreateUserVeryLongBody() {

        val postCreateUserVeryLongBodyResponse =   given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().post("users")
            .then().assertThat().statusCode(413)
            .extract().response().asString()
        Payload().log(postCreateUserVeryLongBodyResponse)
    }

    @Test
    fun postCreateUserSpecialSymbols() {

        val postCreateUserSpecialSymbolsResponse =   given().spec(requestSpecification)
            .body(Payload().specialSymbols())
            .`when`().post("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(postCreateUserSpecialSymbolsResponse)
    }

    @Test
    fun postCreateUserWithIncorrectContentType() {

        val postCreateUserWithIncorrectContentTypeResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().post("users")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(postCreateUserWithIncorrectContentTypeResponse)
    }

    @Test
    fun putUpdateUser(){

        val putUpdateUserResponse =    given().spec(requestSpecification)
            .body(Payload().updateUser())
            .`when`().put("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("name", equalTo("morpheus")).assertThat()
            .body("job", equalTo("zion resident")).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(putUpdateUserResponse)
    }

    @Test
    fun putUpdateUserEmptyBody(){

        val putUpdateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().put("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(putUpdateUserEmptyBodyResponse)
    }

    @Test
    fun putCreateUserIncorrectBodyFormatting() {

        val putCreateUserIncorrectBodyFormattingResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting())
            .`when`().put("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(putCreateUserIncorrectBodyFormattingResponse)
    }

    @Test
    fun putCreateUserIncorrectBodyFormatting2() {

        val putCreateUserIncorrectBodyFormatting2Response =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting2())
            .`when`().put("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(putCreateUserIncorrectBodyFormatting2Response)
    }

    @Test
    fun putCreateUserVeryLongBody() {

        val putCreateUserVeryLongBodyResponse =   given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().put("users")
            .then().assertThat().statusCode(413)
            .extract().response().asString()
        Payload().log(putCreateUserVeryLongBodyResponse)
    }

    @Test
    fun putCreateUserSpecialSymbols() {

        val putCreateUserSpecialSymbolsResponse =   given().spec(requestSpecification)
            .body(Payload().specialSymbols())
            .`when`().put("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(putCreateUserSpecialSymbolsResponse)
    }

    @Test
    fun putCreateUserWithIncorrectContentType() {

        val putCreateUserWithIncorrectContentTypeResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().put("users")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(putCreateUserWithIncorrectContentTypeResponse)
    }


    @Test
    fun patchUpdateUser(){

        val patchUpdateUserResponse =    given().spec(requestSpecification)
                .body(Payload().updateUser())
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200).assertThat()
                .body("name", equalTo("morpheus")).assertThat()
                .body("job", equalTo("zion resident")).assertThat()
                .body("size()", greaterThan(0)).assertThat()
                .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(patchUpdateUserResponse)
    }

    @Test
    fun patchUpdateUserEmptyBody(){

        val patchUpdateUserEmptyBodyResponse =   given().spec(requestSpecification)
            .`when`().patch("users/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(patchUpdateUserEmptyBodyResponse)
    }

    @Test
    fun patchCreateUserIncorrectBodyFormatting() {

        val patchCreateUserIncorrectBodyFormattingResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting())
            .`when`().patch("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(patchCreateUserIncorrectBodyFormattingResponse)
    }

    @Test
    fun patchCreateUserIncorrectBodyFormatting2() {

        val patchCreateUserIncorrectBodyFormatting2Response =   given().spec(requestSpecification)
            .body(Payload().incorrectFormatting2())
            .`when`().patch("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(patchCreateUserIncorrectBodyFormatting2Response)
    }

    @Test
    fun patchCreateUserVeryLongBody() {

        val patchCreateUserVeryLongBodyResponse =   given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().patch("users")
            .then().assertThat().statusCode(413)
            .extract().response().asString()
        Payload().log(patchCreateUserVeryLongBodyResponse)
    }

    @Test
    fun patchCreateUserSpecialSymbols() {

        val patchCreateUserSpecialSymbolsResponse =   given().spec(requestSpecification)
            .body(Payload().specialSymbols())
            .`when`().patch("users")
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(patchCreateUserSpecialSymbolsResponse)
    }

    @Test
    fun patchCreateUserWithIncorrectContentType() {

        val patchCreateUserWithIncorrectContentTypeResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().patch("users")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(patchCreateUserWithIncorrectContentTypeResponse)
    }

    @Test
    fun deleteUser(){

        given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)

        val deleteUserResponse =   given().spec(requestSpecification)
            .`when`().delete("users/2")
            .then().assertThat().statusCode(204).assertThat()
            .header("Content-Length", equalTo("0"))
            .extract().response().asString()
        Payload().log(deleteUserResponse)
    }

    @Test
    fun deleteUserWithBody(){

        given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)

        val deleteUserWithBodyResponse =   given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().delete("users/2")
            .then().assertThat().statusCode(204).assertThat()
            .header("Content-Length", equalTo("0"))
            .extract().response().asString()
        Payload().log(deleteUserWithBodyResponse)
    }

    @Test
    fun deleteUserWithIncorrectContentType(){

        given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)

        val deleteUserWithIncorrectContentTypeResponse =   given().spec(requestSpecification)
            .body(Payload().incorrectContentType())
            .`when`().delete("users/2")
            .then().assertThat().statusCode(400).assertThat()
            .contentType("text/html")
            .extract().response().asString()
        Payload().log(deleteUserWithIncorrectContentTypeResponse)
    }

    @Test
    fun deleteUserWithVeryLongBody() {

        given().spec(requestSpecification)
            .body(Payload().addUser())
            .`when`().post("users")
            .then().assertThat().statusCode(201)

        val deleteUserWithVeryLongBodyResponse =   given().spec(requestSpecification)
            .body(Payload().veryLongBody())
            .`when`().delete("users/2")
            .then().assertThat().statusCode(413)
            .extract().response().asString()
        Payload().log(deleteUserWithVeryLongBodyResponse)
    }


}