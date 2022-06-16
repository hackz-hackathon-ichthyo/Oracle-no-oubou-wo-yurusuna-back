package tech.notchman.infra

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.github.com/";
    }

    private val objectMapper: ObjectMapper = ObjectMapper()
    private val client = OkHttpClient.Builder().build()
    private val token = "hogehoge"
    fun getIssues(): Issues {
        val request = Request.Builder().url(BASE_URL + "").header("Authorization", "Bearer $token").build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                //エラーログを確認
                throw IOException("Unexpected code $response")
            }
            val responseString = response.body?.string().orEmpty()

            return objectMapper.readValue(responseString, Issues::class.java)
        }

    }
}
