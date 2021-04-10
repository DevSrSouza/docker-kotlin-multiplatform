/**
* Docker Engine API
* The Engine API is an HTTP API served by Docker Engine. It is the API the Docker client uses to communicate with the Engine, so everything the Docker client can do can be done with the API.  Most of the client's commands map directly to API endpoints (e.g. `docker ps` is `GET /containers/json`). The notable exception is running containers, which consists of several API calls.  # Errors  The API uses standard HTTP status codes to indicate the success or failure of the API call. The body of the response will be JSON in the following format:  ``` {   \"message\": \"page not found\" } ```  # Versioning  The API is usually changed in each release, so API calls are versioned to ensure that clients don't break. To lock to a specific version of the API, you prefix the URL with its version, for example, call `/v1.30/info` to use the v1.30 version of the `/info` endpoint. If the API version specified in the URL is not supported by the daemon, a HTTP `400 Bad Request` error message is returned.  If you omit the version-prefix, the current version of the API (v1.41) is used. For example, calling `/info` is the same as calling `/v1.41/info`. Using the API without a version-prefix is deprecated and will be removed in a future release.  Engine releases in the near future should support this version of the API, so your client will continue to work even if it is talking to a newer Engine.  The API uses an open schema model, which means server may add extra properties to responses. Likewise, the server will ignore any extra query parameters and request body properties. When you write clients, you need to ignore additional properties in responses to ensure they do not break when talking to newer daemons.   # Authentication  Authentication for registries is handled client side. The client has to send authentication details to various endpoints that need to communicate with registries, such as `POST /images/(name)/push`. These are sent as `X-Registry-Auth` header as a [base64url encoded](https://tools.ietf.org/html/rfc4648#section-5) (JSON) string with the following structure:  ``` {   \"username\": \"string\",   \"password\": \"string\",   \"email\": \"string\",   \"serveraddress\": \"string\" } ```  The `serveraddress` is a domain/IP without a protocol. Throughout this structure, double quotes are required.  If you have already got an identity token from the [`/auth` endpoint](#operation/SystemAuth), you can just pass this instead of credentials:  ``` {   \"identitytoken\": \"9cbaf023786cd7...\" } ``` 
*
* The version of the OpenAPI document: 1.41
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.apis

import org.openapitools.client.models.ErrorResponse
import org.openapitools.client.models.InlineObject5
import org.openapitools.client.models.InlineObject6
import org.openapitools.client.models.InlineObject7
import org.openapitools.client.models.Swarm
import org.openapitools.client.models.SwarmSpec
import org.openapitools.client.models.UnlockKeyResponse

import org.openapitools.client.infrastructure.*
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

class SwarmApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    serializer: Json
) : ApiClient(baseUrl, httpClientEngine, serializer) {

    /**
     * Initialize a new swarm
     * 
     * @param body  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun swarmInit(body: InlineObject5): HttpResponse<kotlin.String> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = body

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/swarm/init",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



    /**
     * Inspect swarm
     * 
     * @return Swarm
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun swarmInspect(): HttpResponse<Swarm> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/swarm",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }


    /**
     * Join an existing swarm
     * 
     * @param body  
     * @return void
     */
    suspend fun swarmJoin(body: InlineObject6): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = body

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/swarm/join",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



    /**
     * Leave a swarm
     * 
     * @param force Force leave swarm, even if this is the last manager or that it will break the cluster.  (optional, default to false)
     * @return void
     */
    suspend fun swarmLeave(force: kotlin.Boolean?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        force?.apply { localVariableQuery["force"] = listOf("$force") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/swarm/leave",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }


    /**
     * Unlock a locked manager
     * 
     * @param body  
     * @return void
     */
    suspend fun swarmUnlock(body: InlineObject7): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = body

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/swarm/unlock",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



    /**
     * Get the unlock key
     * 
     * @return UnlockKeyResponse
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun swarmUnlockkey(): HttpResponse<UnlockKeyResponse> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/swarm/unlockkey",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }


    /**
     * Update a swarm
     * 
     * @param version The version number of the swarm object being updated. This is required to avoid conflicting writes.  
     * @param body  
     * @param rotateWorkerToken Rotate the worker join token. (optional, default to false)
     * @param rotateManagerToken Rotate the manager join token. (optional, default to false)
     * @param rotateManagerUnlockKey Rotate the manager unlock key. (optional, default to false)
     * @return void
     */
    suspend fun swarmUpdate(version: kotlin.Long, body: SwarmSpec, rotateWorkerToken: kotlin.Boolean?, rotateManagerToken: kotlin.Boolean?, rotateManagerUnlockKey: kotlin.Boolean?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = body

        val localVariableQuery = mutableMapOf<String, List<String>>()
        version?.apply { localVariableQuery["version"] = listOf("$version") }
        rotateWorkerToken?.apply { localVariableQuery["rotateWorkerToken"] = listOf("$rotateWorkerToken") }
        rotateManagerToken?.apply { localVariableQuery["rotateManagerToken"] = listOf("$rotateManagerToken") }
        rotateManagerUnlockKey?.apply { localVariableQuery["rotateManagerUnlockKey"] = listOf("$rotateManagerUnlockKey") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/swarm/update",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



}