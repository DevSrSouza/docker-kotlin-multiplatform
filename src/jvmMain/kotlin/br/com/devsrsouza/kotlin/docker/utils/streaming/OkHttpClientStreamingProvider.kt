package br.com.devsrsouza.kotlin.docker.utils.streaming

import br.com.devsrsouza.kotlin.docker.utils.UnixSocketFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientStreamingProvider {
    private lateinit var client: OkHttpClient

    fun get(): OkHttpClient {
        if(OkHttpClientStreamingProvider::client.isInitialized.not()) {
            val unixFactory = UnixSocketFactory()

            client = OkHttpClient.Builder()
                .socketFactory(unixFactory)
                .dns(unixFactory)
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .connectTimeout(0, TimeUnit.MILLISECONDS)
                .callTimeout(0, TimeUnit.MILLISECONDS)
                .build()
        }

        return client
    }
}