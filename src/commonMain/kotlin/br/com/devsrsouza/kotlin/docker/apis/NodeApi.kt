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
package br.com.devsrsouza.kotlin.docker.apis

import br.com.devsrsouza.kotlin.docker.models.ErrorResponse
import br.com.devsrsouza.kotlin.docker.models.Node
import br.com.devsrsouza.kotlin.docker.models.NodeSpec

import br.com.devsrsouza.kotlin.docker.infrastructure.*
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

class NodeApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    serializer: Json
) : ApiClient(baseUrl, httpClientEngine, serializer) {

    /**
     * Delete a node
     * 
     * @param id The ID or name of the node 
     * @param force Force remove a node from the swarm (optional, default to false)
     * @return void
     */
    suspend fun nodeDelete(id: kotlin.String, force: kotlin.Boolean?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        force?.apply { localVariableQuery["force"] = listOf("$force") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.DELETE,
            "/nodes/{id}".replace("{" + "id" + "}", "$id"),
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
     * Inspect a node
     * 
     * @param id The ID or name of the node 
     * @return Node
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun nodeInspect(id: kotlin.String): HttpResponse<Node> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/nodes/{id}".replace("{" + "id" + "}", "$id"),
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
     * List nodes
     * 
     * @param filters Filters to process on the nodes list, encoded as JSON (a &#x60;map[string][]string&#x60;).  Available filters: - &#x60;id&#x3D;&lt;node id&gt;&#x60; - &#x60;label&#x3D;&lt;engine label&gt;&#x60; - &#x60;membership&#x3D;&#x60;(&#x60;accepted&#x60;|&#x60;pending&#x60;)&#x60; - &#x60;name&#x3D;&lt;node name&gt;&#x60; - &#x60;node.label&#x3D;&lt;node label&gt;&#x60; - &#x60;role&#x3D;&#x60;(&#x60;manager&#x60;|&#x60;worker&#x60;)&#x60;  (optional)
     * @return kotlin.collections.List<Node>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun nodeList(filters: kotlin.String?): HttpResponse<kotlin.collections.List<Node>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/nodes",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse>().map { value }
    }

    @Serializable
    private class NodeListResponse(val value: List<Node>) {
        @Serializer(br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse::class)
        companion object : KSerializer<br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse> {
            private val serializer: KSerializer<List<Node>> = serializer<List<Node>>()
            override val descriptor = br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse.Companion.serializer.descriptor
            override fun serialize(encoder: Encoder, obj: br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse) = br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse.Companion.serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse(
                br.com.devsrsouza.kotlin.docker.apis.NodeApi.NodeListResponse.Companion.serializer.deserialize(decoder)
            )
        }
    }

    /**
     * Update a node
     * 
     * @param id The ID of the node 
     * @param version The version number of the node object being updated. This is required to avoid conflicting writes.  
     * @param body  (optional)
     * @return void
     */
    suspend fun nodeUpdate(id: kotlin.String, version: kotlin.Long, body: NodeSpec?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = body

        val localVariableQuery = mutableMapOf<String, List<String>>()
        version?.apply { localVariableQuery["version"] = listOf("$version") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/nodes/{id}/update".replace("{" + "id" + "}", "$id"),
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
