import io.restassured.path.json.JsonPath

open class Payload {

    fun rawToJson(resp:String): JsonPath {
        val js1: JsonPath = JsonPath(resp)
        return js1
    }


    fun addUser(): String {
        var name = "morpheus"
        var job = "leader"

        return "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}"
    }

    fun updateUser(): String {
       var newJob = "zion resident"
        var newName = "morpheus"

        return "{\n" +
                "    \"name\": \""+newName+"\",\n" +
                "    \"job\": \""+newJob+"\"\n" +
                "}"
    }

    fun registerUser(): String{
        var newEmail = "eve.holt@reqres.in"
        var newPassword = "pistol"

        return "{\n" +
                "    \"email\": \""+newEmail+"\",\n" +
                "    \"password\": \""+newPassword+"\"\n" +
                "}"
    }

    fun incorrectData(): String {
        var newIncorrectEmail = "sydney@fife"
        return "{\n" +
                "    \"email\": \""+newIncorrectEmail+"\"\n" +
                "}"
    }

    fun incorrectData2(): String {
        var newIncorrectPassword = "sydney@fife"
        return "{\n" +
                "    \"password\": \""+newIncorrectPassword+"\"\n" +
                "}"
    }


    fun loginSuccessful():String{
        var login = "eve.holt@reqres.in"
        var password = "cityslicka"
        return "{\n" +
                "    \"email\": \""+login+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}"
    }

    fun loginUnuccessful():String{
        var badLogin = "eve.holt@reqres.in"
        return "{\n" +
                "    \"email\": \""+badLogin+"\"\n" +
                "}"
    }

    fun loginUnuccessful2():String{
        var badPassword = "cityslicka"
        return "{\n" +
                "    \"password\": \""+badPassword+"\"\n" +
                "}"
    }


}