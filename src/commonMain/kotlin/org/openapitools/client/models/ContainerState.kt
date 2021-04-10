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

import org.openapitools.client.models.Health

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * ContainerState stores container's running state. It's part of ContainerJSONBase and will be returned by the \"inspect\" command. 
 * @param status String representation of the container state. Can be one of \"created\", \"running\", \"paused\", \"restarting\", \"removing\", \"exited\", or \"dead\". 
 * @param running Whether this container is running.  Note that a running container can be _paused_. The `Running` and `Paused` booleans are not mutually exclusive:  When pausing a container (on Linux), the freezer cgroup is used to suspend all processes in the container. Freezing the process requires the process to be running. As a result, paused containers are both `Running` _and_ `Paused`.  Use the `Status` field instead to determine if a container's state is \"running\". 
 * @param paused Whether this container is paused.
 * @param restarting Whether this container is restarting.
 * @param ooMKilled Whether this container has been killed because it ran out of memory. 
 * @param dead 
 * @param pid The process ID of this container
 * @param exitCode The last exit code of this container
 * @param error 
 * @param startedAt The time when this container was last started.
 * @param finishedAt The time when this container last exited.
 * @param health 
 */
@Serializable
data class ContainerState (
    /* String representation of the container state. Can be one of \"created\", \"running\", \"paused\", \"restarting\", \"removing\", \"exited\", or \"dead\".  */
    @SerialName(value = "Status") val status: ContainerState.Status? = null,
    /* Whether this container is running.  Note that a running container can be _paused_. The `Running` and `Paused` booleans are not mutually exclusive:  When pausing a container (on Linux), the freezer cgroup is used to suspend all processes in the container. Freezing the process requires the process to be running. As a result, paused containers are both `Running` _and_ `Paused`.  Use the `Status` field instead to determine if a container's state is \"running\".  */
    @SerialName(value = "Running") val running: kotlin.Boolean? = null,
    /* Whether this container is paused. */
    @SerialName(value = "Paused") val paused: kotlin.Boolean? = null,
    /* Whether this container is restarting. */
    @SerialName(value = "Restarting") val restarting: kotlin.Boolean? = null,
    /* Whether this container has been killed because it ran out of memory.  */
    @SerialName(value = "OOMKilled") val ooMKilled: kotlin.Boolean? = null,
    @SerialName(value = "Dead") val dead: kotlin.Boolean? = null,
    /* The process ID of this container */
    @SerialName(value = "Pid") val pid: kotlin.Int? = null,
    /* The last exit code of this container */
    @SerialName(value = "ExitCode") val exitCode: kotlin.Int? = null,
    @SerialName(value = "Error") val error: kotlin.String? = null,
    /* The time when this container was last started. */
    @SerialName(value = "StartedAt") val startedAt: kotlin.String? = null,
    /* The time when this container last exited. */
    @SerialName(value = "FinishedAt") val finishedAt: kotlin.String? = null,
    @SerialName(value = "Health") val health: Health? = null
) {

    /**
     * String representation of the container state. Can be one of \"created\", \"running\", \"paused\", \"restarting\", \"removing\", \"exited\", or \"dead\". 
     * Values: created,running,paused,restarting,removing,exited,dead
     */
    @Serializable
    enum class Status(val value: kotlin.String) {
        @SerialName(value = "created") created("created"),
        @SerialName(value = "running") running("running"),
        @SerialName(value = "paused") paused("paused"),
        @SerialName(value = "restarting") restarting("restarting"),
        @SerialName(value = "removing") removing("removing"),
        @SerialName(value = "exited") exited("exited"),
        @SerialName(value = "dead") dead("dead");
    }
}

