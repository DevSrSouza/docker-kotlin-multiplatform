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
import org.openapitools.client.models.InlineObject
import org.openapitools.client.models.Plugin
import org.openapitools.client.models.PluginPrivilegeItem

import org.openapitools.client.infrastructure.*
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

class PluginApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    serializer: Json
) : ApiClient(baseUrl, httpClientEngine, serializer) {

    /**
     * Get plugin privileges
     * 
     * @param remote The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @return kotlin.collections.List<PluginPrivilegeItem>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun getPluginPrivileges(remote: kotlin.String): HttpResponse<kotlin.collections.List<PluginPrivilegeItem>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        remote?.apply { localVariableQuery["remote"] = listOf("$remote") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/plugins/privileges",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<GetPluginPrivilegesResponse>().map { value }
    }

    @Serializable
    private class GetPluginPrivilegesResponse(val value: List<PluginPrivilegeItem>) {
        @Serializer(GetPluginPrivilegesResponse::class)
        companion object : KSerializer<GetPluginPrivilegesResponse> {
            private val serializer: KSerializer<List<PluginPrivilegeItem>> = serializer<List<PluginPrivilegeItem>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: GetPluginPrivilegesResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = GetPluginPrivilegesResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Create a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @param tarContext Path to tar containing plugin rootfs and manifest (optional)
     * @return void
     */
    suspend fun pluginCreate(name: kotlin.String, tarContext: org.openapitools.client.infrastructure.OctetByteArray?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = tarContext

        val localVariableQuery = mutableMapOf<String, List<String>>()
        name?.apply { localVariableQuery["name"] = listOf("$name") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/create",
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
     * Remove a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @param force Disable the plugin before removing. This may result in issues if the plugin is in use by a container.  (optional, default to false)
     * @return Plugin
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun pluginDelete(name: kotlin.String, force: kotlin.Boolean?): HttpResponse<Plugin> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        force?.apply { localVariableQuery["force"] = listOf("$force") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.DELETE,
            "/plugins/{name}".replace("{" + "name" + "}", "$name"),
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
     * Disable a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @return void
     */
    suspend fun pluginDisable(name: kotlin.String): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/{name}/disable".replace("{" + "name" + "}", "$name"),
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
     * Enable a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @param timeout Set the HTTP client timeout (in seconds) (optional, default to 0)
     * @return void
     */
    suspend fun pluginEnable(name: kotlin.String, timeout: kotlin.Int?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        timeout?.apply { localVariableQuery["timeout"] = listOf("$timeout") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/{name}/enable".replace("{" + "name" + "}", "$name"),
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
     * Inspect a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @return Plugin
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun pluginInspect(name: kotlin.String): HttpResponse<Plugin> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/plugins/{name}/json".replace("{" + "name" + "}", "$name"),
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
     * List plugins
     * Returns information about installed plugins.
     * @param filters A JSON encoded value of the filters (a &#x60;map[string][]string&#x60;) to process on the plugin list.  Available filters:  - &#x60;capability&#x3D;&lt;capability name&gt;&#x60; - &#x60;enable&#x3D;&lt;true&gt;|&lt;false&gt;&#x60;  (optional)
     * @return kotlin.collections.List<Plugin>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun pluginList(filters: kotlin.String?): HttpResponse<kotlin.collections.List<Plugin>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/plugins",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<PluginListResponse>().map { value }
    }

    @Serializable
    private class PluginListResponse(val value: List<Plugin>) {
        @Serializer(PluginListResponse::class)
        companion object : KSerializer<PluginListResponse> {
            private val serializer: KSerializer<List<Plugin>> = serializer<List<Plugin>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: PluginListResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = PluginListResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Install a plugin
     * Pulls and installs a plugin. After the plugin is installed, it can be enabled using the [&#x60;POST /plugins/{name}/enable&#x60; endpoint](#operation/PostPluginsEnable). 
     * @param remote Remote reference for plugin to install.  The &#x60;:latest&#x60; tag is optional, and is used as the default if omitted.  
     * @param name Local name for the pulled plugin.  The &#x60;:latest&#x60; tag is optional, and is used as the default if omitted.  (optional)
     * @param xRegistryAuth A base64url-encoded auth configuration to use when pulling a plugin from a registry.  Refer to the [authentication section](#section/Authentication) for details.  (optional)
     * @param body  (optional)
     * @return void
     */
    suspend fun pluginPull(remote: kotlin.String, name: kotlin.String?, xRegistryAuth: kotlin.String?, body: kotlin.collections.List<InlineObject>?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = PluginPullRequest(body)

        val localVariableQuery = mutableMapOf<String, List<String>>()
        remote?.apply { localVariableQuery["remote"] = listOf("$remote") }
        name?.apply { localVariableQuery["name"] = listOf("$name") }

        val localVariableHeaders = mutableMapOf<String, String>()
        xRegistryAuth?.apply { localVariableHeaders["X-Registry-Auth"] = this.toString() }

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/pull",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }

    @Serializable
    private class PluginPullRequest(val value: List<InlineObject>) {
        @Serializer(PluginPullRequest::class)
        companion object : KSerializer<PluginPullRequest> {
            private val serializer: KSerializer<List<InlineObject>> = serializer<List<InlineObject>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: PluginPullRequest) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = PluginPullRequest(serializer.deserialize(decoder))
        }
    }

    /**
     * Push a plugin
     * Push a plugin to the registry. 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @return void
     */
    suspend fun pluginPush(name: kotlin.String): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/{name}/push".replace("{" + "name" + "}", "$name"),
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
     * Configure a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @param body  (optional)
     * @return void
     */
    suspend fun pluginSet(name: kotlin.String, body: kotlin.collections.List<kotlin.String>?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = PluginSetRequest(body)

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/{name}/set".replace("{" + "name" + "}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }

    @Serializable
    private class PluginSetRequest(val value: List<kotlin.String>) {
        @Serializer(PluginSetRequest::class)
        companion object : KSerializer<PluginSetRequest> {
            private val serializer: KSerializer<List<kotlin.String>> = serializer<List<kotlin.String>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: PluginSetRequest) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = PluginSetRequest(serializer.deserialize(decoder))
        }
    }

    /**
     * Upgrade a plugin
     * 
     * @param name The name of the plugin. The &#x60;:latest&#x60; tag is optional, and is the default if omitted.  
     * @param remote Remote reference to upgrade to.  The &#x60;:latest&#x60; tag is optional, and is used as the default if omitted.  
     * @param xRegistryAuth A base64url-encoded auth configuration to use when pulling a plugin from a registry.  Refer to the [authentication section](#section/Authentication) for details.  (optional)
     * @param body  (optional)
     * @return void
     */
    suspend fun pluginUpgrade(name: kotlin.String, remote: kotlin.String, xRegistryAuth: kotlin.String?, body: kotlin.collections.List<InlineObject>?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = PluginUpgradeRequest(body)

        val localVariableQuery = mutableMapOf<String, List<String>>()
        remote?.apply { localVariableQuery["remote"] = listOf("$remote") }

        val localVariableHeaders = mutableMapOf<String, String>()
        xRegistryAuth?.apply { localVariableHeaders["X-Registry-Auth"] = this.toString() }

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/plugins/{name}/upgrade".replace("{" + "name" + "}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }

    @Serializable
    private class PluginUpgradeRequest(val value: List<InlineObject>) {
        @Serializer(PluginUpgradeRequest::class)
        companion object : KSerializer<PluginUpgradeRequest> {
            private val serializer: KSerializer<List<InlineObject>> = serializer<List<InlineObject>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: PluginUpgradeRequest) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = PluginUpgradeRequest(serializer.deserialize(decoder))
        }
    }

}