package data
import io.restassured.path.json.JsonPath

import org.slf4j.LoggerFactory

open class Payload {

      private val logger = LoggerFactory.getLogger("reqres.in")
          fun log(s: String) {
          logger.info(s)
    }



    fun rawToJson(resp: String): JsonPath {
        return JsonPath(resp)
    }


    fun addUser(): String {
        val name = "morpheus"
        val job = "leader"

        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\"\n" +
                "}"
    }

        fun updateUser(): String {
            val newJob = "zion resident"
            val newName = "morpheus"

            return "{\n" +
                    "    \"name\": \"" +newName+ "\",\n" +
                    "    \"job\": \"" +newJob+ "\"\n" +
                    "}"
        }

        fun registerUser(): String {
            val newEmail = "eve.holt@reqres.in"
            val newPassword = "pistol"

            return "{\n" +
                    "    \"email\": \""+newEmail+"\",\n" +
                    "    \"password\": \""+newPassword+"\"\n" +
                    "}"
        }

        fun incorrectData(): String {
            val newIncorrectEmail = "sydney@fife"

            return "{\n"+
                    "    \"email\": \""+newIncorrectEmail+"\"\n" +
                    "}"
        }


        fun incorrectData2(): String {
            val newIncorrectPassword = "sydney@fife"

            return "{\n"+
                    "    \"password\": \""+newIncorrectPassword+"\"\n" +
                    "}"
        }


        fun loginSuccessful(): String {
            val login = "eve.holt@reqres.in"
            val password = "cityslicka"
            return "{\n" +
                    "    \"email\": \""+login+"\",\n" +
                    "    \"password\": \""+password+"\"\n" +
                    "}"
        }

        fun loginUnuccessful(): String {
            val badLogin = "eve.holt@reqres.in"

            return "{\n"+
                    "    \"email\": \""+badLogin+"\"\n" +
                    "}"
        }

        fun loginUnuccessful2(): String {
            val badPassword = "cityslicka"
            return "{\n"+
                    "    \"password\": \""+badPassword+"\"\n" +
                    "}"
        }
    }
