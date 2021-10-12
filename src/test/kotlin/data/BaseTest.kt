package data

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.LogConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
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
            .setBaseUri("https://reqres.in/")
            .setBasePath("/api")
            .setContentType(ContentType.JSON)
            .setConfig(config)
            .build()

    }

    @After
    fun tearDown(){
        RestAssured.reset()
    }


}