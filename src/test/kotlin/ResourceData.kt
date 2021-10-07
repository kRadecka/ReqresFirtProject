import data.BaseTest
import data.Payload
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.junit.Test

class ResourceData : BaseTest(){

    @Test
    fun getListResource(){

      val getListResourceResponse =  given().spec(requestSpecification)
            .`when`().get("unknown")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()", greaterThan(0)).assertThat()
          .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getListResourceResponse)
    }


    @Test
    fun getSingleResource(){

        val getSingleResourceResponse = given().spec(requestSpecification)
            .`when`().get("unknown/2")
            .then().assertThat().statusCode(200).assertThat()
            .body("data.size()", greaterThan(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getSingleResourceResponse)
    }

    @Test
    fun getZeroPageSingleResource(){

        val getZeroPageSingleResourceResponse = given().spec(requestSpecification)
            .`when`().get("unknown/0")
            .then().assertThat().statusCode(404).assertThat()
            .body("size()", equalTo(0)).assertThat()
            .contentType(ContentType.JSON)
            .extract().response().asString()
        Payload().log(getZeroPageSingleResourceResponse)
    }

    @Test
    fun getToLongSingleResource(){

        val getToLongSingleResourceResponse = given().spec(requestSpecification)
            .`when`().get(Payload().veryLongPageForSingleUser())
            .then().assertThat().statusCode(400)
            .extract().response().asString()
        Payload().log(getToLongSingleResourceResponse)
    }

    @Test
    fun getSingleResourceNotFound(){

        val getSingleResourceNotFoundResponse =  given().spec(requestSpecification)
            .`when`().get("unknown/23")
            .then().assertThat().statusCode(404).assertThat()
            .body("size()", equalTo(0)).assertThat()
            .contentType(ContentType.JSON).assertThat()
            .header("Content-Length", equalTo("2"))
            .extract().response().asString()
        Payload().log(getSingleResourceNotFoundResponse)
    }
}