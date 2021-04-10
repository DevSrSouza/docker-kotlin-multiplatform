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
package org.openapitools.client.models

import org.openapitools.client.models.ClusterInfo
import org.openapitools.client.models.JoinTokens
import org.openapitools.client.models.ObjectVersion
import org.openapitools.client.models.SwarmAllOf
import org.openapitools.client.models.SwarmSpec
import org.openapitools.client.models.TLSInfo

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 * @param ID The ID of the swarm.
 * @param version 
 * @param createdAt Date and time at which the swarm was initialised in [RFC 3339](https://www.ietf.org/rfc/rfc3339.txt) format with nano-seconds. 
 * @param updatedAt Date and time at which the swarm was last updated in [RFC 3339](https://www.ietf.org/rfc/rfc3339.txt) format with nano-seconds. 
 * @param spec 
 * @param tlSInfo 
 * @param rootRotationInProgress Whether there is currently a root CA rotation in progress for the swarm 
 * @param dataPathPort DataPathPort specifies the data path port number for data traffic. Acceptable port range is 1024 to 49151. If no port is set or is set to 0, the default port (4789) is used. 
 * @param defaultAddrPool Default Address Pool specifies default subnet pools for global scope networks. 
 * @param subnetSize SubnetSize specifies the subnet size of the networks created from the default subnet pool. 
 * @param joinTokens 
 */
@Serializable
data class Swarm (
    /* The ID of the swarm. */
    @SerialName(value = "ID") val ID: kotlin.String? = null,
    @SerialName(value = "Version") val version: ObjectVersion? = null,
    /* Date and time at which the swarm was initialised in [RFC 3339](https://www.ietf.org/rfc/rfc3339.txt) format with nano-seconds.  */
    @SerialName(value = "CreatedAt") val createdAt: kotlin.String? = null,
    /* Date and time at which the swarm was last updated in [RFC 3339](https://www.ietf.org/rfc/rfc3339.txt) format with nano-seconds.  */
    @SerialName(value = "UpdatedAt") val updatedAt: kotlin.String? = null,
    @SerialName(value = "Spec") val spec: SwarmSpec? = null,
    @SerialName(value = "TLSInfo") val tlSInfo: TLSInfo? = null,
    /* Whether there is currently a root CA rotation in progress for the swarm  */
    @SerialName(value = "RootRotationInProgress") val rootRotationInProgress: kotlin.Boolean? = null,
    /* DataPathPort specifies the data path port number for data traffic. Acceptable port range is 1024 to 49151. If no port is set or is set to 0, the default port (4789) is used.  */
    @SerialName(value = "DataPathPort") val dataPathPort: kotlin.Int? = null,
    /* Default Address Pool specifies default subnet pools for global scope networks.  */
    @SerialName(value = "DefaultAddrPool") val defaultAddrPool: kotlin.collections.List<kotlin.String>? = null,
    /* SubnetSize specifies the subnet size of the networks created from the default subnet pool.  */
    @SerialName(value = "SubnetSize") val subnetSize: kotlin.Int? = null,
    @SerialName(value = "JoinTokens") val joinTokens: JoinTokens? = null
)

