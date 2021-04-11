import br.com.devsrsouza.kotlin.docker.apis.SystemApi
import br.com.devsrsouza.kotlin.docker.utils.UnixSocketFactory
import io.ktor.client.engine.okhttp.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

fun main(): Unit = runBlocking<Unit> {
    val unixFactory = UnixSocketFactory()

    val okhttp = OkHttp.create {
        config {
            socketFactory(unixFactory)
            dns(unixFactory)
            readTimeout(0, TimeUnit.MILLISECONDS)
            connectTimeout(0, TimeUnit.MILLISECONDS)
            callTimeout(0, TimeUnit.MILLISECONDS)
        }
    }

    val json = Json {
        ignoreUnknownKeys = true
    }

    val systemApi = SystemApi(
        httpClientEngine = okhttp,
        serializer = json
    )

    systemApi.systemEvents(null, null, null).collect {
        println(it)
    }
}