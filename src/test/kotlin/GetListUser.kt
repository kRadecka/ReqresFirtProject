import org.junit.Test
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import java.io.FileOutputStream
import java.io.PrintStream


class GetListUser() :BaseTest() {

    @Test
    fun getUserList() {

        val log = PrintStream(FileOutputStream("getUserList.txt"))

            given()
                .spec(requestSpecification)
                .filters(RequestLoggingFilter.logRequestTo(log))
                .filters(ResponseLoggingFilter.logResponseTo(log))
            .queryParam("page", "2")
            .`when`().get("users")
            .then().assertThat().statusCode(200)
            .extract().response().asString()

    }



    fun getJsonPath(response: Response, key: String): String?{
        val resp: String = response.asString()
        val js: JsonPath = JsonPath(resp)
        return js.get<String?>(key).toString()
    }

    }
