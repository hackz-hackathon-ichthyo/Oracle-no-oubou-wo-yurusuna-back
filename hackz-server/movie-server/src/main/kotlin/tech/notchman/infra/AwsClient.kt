package tech.notchman.infra

data class Props(
    val secret:String,
    val url:String,
)

class AwsClient {
//    companion object{
//        endpoint = "https//example.com"
//    }
    fun getLiveUrl(clientId:String):Props{
        return Props(secret = "", url = "")
    }
}