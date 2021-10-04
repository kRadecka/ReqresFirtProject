open class Payload {


    fun addUser(): String {

        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
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

}