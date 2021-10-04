import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.config.LogConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification
import org.junit.After
import org.junit.Before
import java.io.FileInputStream

import java.util.*

open class BaseTest{

    companion object {
        lateinit var requestSpecification: RequestSpecification
        }

    @Before
    fun setup(){
        val logConfig = LogConfig.logConfig()
            .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
        val config = RestAssuredConfig.config().logConfig(logConfig)


         requestSpecification = RequestSpecBuilder()
            .setBaseUri(getGlobalValue("baseUrl"))
            .setBasePath("/api")
            .setContentType(ContentType.JSON)
            .setConfig(config)
            .build()

    }

    @After
    fun tearDown(){
        RestAssured.reset()
    }

    fun getGlobalValue(key:String): String? {
        var prop: Properties = Properties()
        var fis: FileInputStream = FileInputStream("C:\\Users\\BRITENET\\IdeaProjects\\ReqresFirtProject\\src\\test\\resources\\global.properties")
        prop.load(fis)
        return prop.getProperty(key)
    }

}