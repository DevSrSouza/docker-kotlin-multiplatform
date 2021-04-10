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

import org.openapitools.client.models.BuildPruneResponse
import org.openapitools.client.models.ContainerConfig
import org.openapitools.client.models.ErrorResponse
import org.openapitools.client.models.HistoryResponseItem
import org.openapitools.client.models.IdResponse
import org.openapitools.client.models.Image
import org.openapitools.client.models.ImageDeleteResponseItem
import org.openapitools.client.models.ImagePruneResponse
import org.openapitools.client.models.ImageSearchResponseItem
import org.openapitools.client.models.ImageSummary

import org.openapitools.client.infrastructure.*
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

class ImageApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    serializer: Json
) : ApiClient(baseUrl, httpClientEngine, serializer) {

    /**
     * Delete builder cache
     * 
     * @param keepStorage Amount of disk space in bytes to keep for cache (optional)
     * @param all Remove all types of build cache (optional)
     * @param filters A JSON encoded value of the filters (a &#x60;map[string][]string&#x60;) to process on the list of build cache objects.  Available filters:  - &#x60;until&#x3D;&lt;duration&gt;&#x60;: duration relative to daemon&#39;s time, during which build cache was not used, in Go&#39;s duration format (e.g., &#39;24h&#39;) - &#x60;id&#x3D;&lt;id&gt;&#x60; - &#x60;parent&#x3D;&lt;id&gt;&#x60; - &#x60;type&#x3D;&lt;string&gt;&#x60; - &#x60;description&#x3D;&lt;string&gt;&#x60; - &#x60;inuse&#x60; - &#x60;shared&#x60; - &#x60;private&#x60;  (optional)
     * @return BuildPruneResponse
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun buildPrune(keepStorage: kotlin.Long?, all: kotlin.Boolean?, filters: kotlin.String?): HttpResponse<BuildPruneResponse> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        keepStorage?.apply { localVariableQuery["keep-storage"] = listOf("$keepStorage") }
        all?.apply { localVariableQuery["all"] = listOf("$all") }
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/build/prune",
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
     * Build an image
     * Build an image from a tar archive with a &#x60;Dockerfile&#x60; in it.  The &#x60;Dockerfile&#x60; specifies how the image is built from the tar archive. It is typically in the archive&#39;s root, but can be at a different path or have a different name by specifying the &#x60;dockerfile&#x60; parameter. [See the &#x60;Dockerfile&#x60; reference for more information](https://docs.docker.com/engine/reference/builder/).  The Docker daemon performs a preliminary validation of the &#x60;Dockerfile&#x60; before starting the build, and returns an error if the syntax is incorrect. After that, each instruction is run one-by-one until the ID of the new image is output.  The build is canceled if the client drops the connection by quitting or being killed. 
     * @param dockerfile Path within the build context to the &#x60;Dockerfile&#x60;. This is ignored if &#x60;remote&#x60; is specified and points to an external &#x60;Dockerfile&#x60;. (optional, default to "Dockerfile")
     * @param t A name and optional tag to apply to the image in the &#x60;name:tag&#x60; format. If you omit the tag the default &#x60;latest&#x60; value is assumed. You can provide several &#x60;t&#x60; parameters. (optional)
     * @param extrahosts Extra hosts to add to /etc/hosts (optional)
     * @param remote A Git repository URI or HTTP/HTTPS context URI. If the URI points to a single text file, the file’s contents are placed into a file called &#x60;Dockerfile&#x60; and the image is built from that file. If the URI points to a tarball, the file is downloaded by the daemon and the contents therein used as the context for the build. If the URI points to a tarball and the &#x60;dockerfile&#x60; parameter is also specified, there must be a file with the corresponding path inside the tarball. (optional)
     * @param q Suppress verbose build output. (optional, default to false)
     * @param nocache Do not use the cache when building the image. (optional, default to false)
     * @param cachefrom JSON array of images used for build cache resolution. (optional)
     * @param pull Attempt to pull the image even if an older image exists locally. (optional)
     * @param rm Remove intermediate containers after a successful build. (optional, default to true)
     * @param forcerm Always remove intermediate containers, even upon failure. (optional, default to false)
     * @param memory Set memory limit for build. (optional)
     * @param memswap Total memory (memory + swap). Set as &#x60;-1&#x60; to disable swap. (optional)
     * @param cpushares CPU shares (relative weight). (optional)
     * @param cpusetcpus CPUs in which to allow execution (e.g., &#x60;0-3&#x60;, &#x60;0,1&#x60;). (optional)
     * @param cpuperiod The length of a CPU period in microseconds. (optional)
     * @param cpuquota Microseconds of CPU time that the container can get in a CPU period. (optional)
     * @param buildargs JSON map of string pairs for build-time variables. Users pass these values at build-time. Docker uses the buildargs as the environment context for commands run via the &#x60;Dockerfile&#x60; RUN instruction, or for variable expansion in other &#x60;Dockerfile&#x60; instructions. This is not meant for passing secret values.  For example, the build arg &#x60;FOO&#x3D;bar&#x60; would become &#x60;{\&quot;FOO\&quot;:\&quot;bar\&quot;}&#x60; in JSON. This would result in the query parameter &#x60;buildargs&#x3D;{\&quot;FOO\&quot;:\&quot;bar\&quot;}&#x60;. Note that &#x60;{\&quot;FOO\&quot;:\&quot;bar\&quot;}&#x60; should be URI component encoded.  [Read more about the buildargs instruction.](https://docs.docker.com/engine/reference/builder/#arg)  (optional)
     * @param shmsize Size of &#x60;/dev/shm&#x60; in bytes. The size must be greater than 0. If omitted the system uses 64MB. (optional)
     * @param squash Squash the resulting images layers into a single layer. *(Experimental release only.)* (optional)
     * @param labels Arbitrary key/value labels to set on the image, as a JSON map of string pairs. (optional)
     * @param networkmode Sets the networking mode for the run commands during build. Supported standard values are: &#x60;bridge&#x60;, &#x60;host&#x60;, &#x60;none&#x60;, and &#x60;container:&lt;name|id&gt;&#x60;. Any other value is taken as a custom network&#39;s name or ID to which this container should connect to.  (optional)
     * @param contentType  (optional, default to application/x-tar)
     * @param xRegistryConfig This is a base64-encoded JSON object with auth configurations for multiple registries that a build may refer to.  The key is a registry URL, and the value is an auth configuration object, [as described in the authentication section](#section/Authentication). For example:  &#x60;&#x60;&#x60; {   \&quot;docker.example.com\&quot;: {     \&quot;username\&quot;: \&quot;janedoe\&quot;,     \&quot;password\&quot;: \&quot;hunter2\&quot;   },   \&quot;https://index.docker.io/v1/\&quot;: {     \&quot;username\&quot;: \&quot;mobydock\&quot;,     \&quot;password\&quot;: \&quot;conta1n3rize14\&quot;   } } &#x60;&#x60;&#x60;  Only the registry domain name (and port if not the default 443) are required. However, for legacy reasons, the Docker Hub registry must be specified with both a &#x60;https://&#x60; prefix and a &#x60;/v1/&#x60; suffix even though Docker will prefer to use the v2 registry API.  (optional)
     * @param platform Platform in the format os[/arch[/variant]] (optional)
     * @param target Target build stage (optional)
     * @param outputs BuildKit output configuration (optional)
     * @param inputStream A tar archive compressed with one of the following algorithms: identity (no compression), gzip, bzip2, xz. (optional)
     * @return void
     */
    suspend fun imageBuild(dockerfile: kotlin.String?, t: kotlin.String?, extrahosts: kotlin.String?, remote: kotlin.String?, q: kotlin.Boolean?, nocache: kotlin.Boolean?, cachefrom: kotlin.String?, pull: kotlin.String?, rm: kotlin.Boolean?, forcerm: kotlin.Boolean?, memory: kotlin.Int?, memswap: kotlin.Int?, cpushares: kotlin.Int?, cpusetcpus: kotlin.String?, cpuperiod: kotlin.Int?, cpuquota: kotlin.Int?, buildargs: kotlin.String?, shmsize: kotlin.Int?, squash: kotlin.Boolean?, labels: kotlin.String?, networkmode: kotlin.String?, contentType: kotlin.String?, xRegistryConfig: kotlin.String?, platform: kotlin.String?, target: kotlin.String?, outputs: kotlin.String?, inputStream: org.openapitools.client.infrastructure.OctetByteArray?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = inputStream

        val localVariableQuery = mutableMapOf<String, List<String>>()
        dockerfile?.apply { localVariableQuery["dockerfile"] = listOf("$dockerfile") }
        t?.apply { localVariableQuery["t"] = listOf("$t") }
        extrahosts?.apply { localVariableQuery["extrahosts"] = listOf("$extrahosts") }
        remote?.apply { localVariableQuery["remote"] = listOf("$remote") }
        q?.apply { localVariableQuery["q"] = listOf("$q") }
        nocache?.apply { localVariableQuery["nocache"] = listOf("$nocache") }
        cachefrom?.apply { localVariableQuery["cachefrom"] = listOf("$cachefrom") }
        pull?.apply { localVariableQuery["pull"] = listOf("$pull") }
        rm?.apply { localVariableQuery["rm"] = listOf("$rm") }
        forcerm?.apply { localVariableQuery["forcerm"] = listOf("$forcerm") }
        memory?.apply { localVariableQuery["memory"] = listOf("$memory") }
        memswap?.apply { localVariableQuery["memswap"] = listOf("$memswap") }
        cpushares?.apply { localVariableQuery["cpushares"] = listOf("$cpushares") }
        cpusetcpus?.apply { localVariableQuery["cpusetcpus"] = listOf("$cpusetcpus") }
        cpuperiod?.apply { localVariableQuery["cpuperiod"] = listOf("$cpuperiod") }
        cpuquota?.apply { localVariableQuery["cpuquota"] = listOf("$cpuquota") }
        buildargs?.apply { localVariableQuery["buildargs"] = listOf("$buildargs") }
        shmsize?.apply { localVariableQuery["shmsize"] = listOf("$shmsize") }
        squash?.apply { localVariableQuery["squash"] = listOf("$squash") }
        labels?.apply { localVariableQuery["labels"] = listOf("$labels") }
        networkmode?.apply { localVariableQuery["networkmode"] = listOf("$networkmode") }
        platform?.apply { localVariableQuery["platform"] = listOf("$platform") }
        target?.apply { localVariableQuery["target"] = listOf("$target") }
        outputs?.apply { localVariableQuery["outputs"] = listOf("$outputs") }

        val localVariableHeaders = mutableMapOf<String, String>()
        contentType?.apply { localVariableHeaders["Content-type"] = this.toString() }
        xRegistryConfig?.apply { localVariableHeaders["X-Registry-Config"] = this.toString() }

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/build",
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
     * Create a new image from a container
     * 
     * @param container The ID or name of the container to commit (optional)
     * @param repo Repository name for the created image (optional)
     * @param tag Tag name for the create image (optional)
     * @param comment Commit message (optional)
     * @param author Author of the image (e.g., &#x60;John Hannibal Smith &lt;hannibal@a-team.com&gt;&#x60;) (optional)
     * @param pause Whether to pause the container before committing (optional, default to true)
     * @param changes &#x60;Dockerfile&#x60; instructions to apply while committing (optional)
     * @param containerConfig The container configuration (optional)
     * @return IdResponse
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageCommit(container: kotlin.String?, repo: kotlin.String?, tag: kotlin.String?, comment: kotlin.String?, author: kotlin.String?, pause: kotlin.Boolean?, changes: kotlin.String?, containerConfig: ContainerConfig?): HttpResponse<IdResponse> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = containerConfig

        val localVariableQuery = mutableMapOf<String, List<String>>()
        container?.apply { localVariableQuery["container"] = listOf("$container") }
        repo?.apply { localVariableQuery["repo"] = listOf("$repo") }
        tag?.apply { localVariableQuery["tag"] = listOf("$tag") }
        comment?.apply { localVariableQuery["comment"] = listOf("$comment") }
        author?.apply { localVariableQuery["author"] = listOf("$author") }
        pause?.apply { localVariableQuery["pause"] = listOf("$pause") }
        changes?.apply { localVariableQuery["changes"] = listOf("$changes") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/commit",
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
     * Create an image
     * Create an image by either pulling it from a registry or importing it.
     * @param fromImage Name of the image to pull. The name may include a tag or digest. This parameter may only be used when pulling an image. The pull is cancelled if the HTTP connection is closed. (optional)
     * @param fromSrc Source to import. The value may be a URL from which the image can be retrieved or &#x60;-&#x60; to read the image from the request body. This parameter may only be used when importing an image. (optional)
     * @param repo Repository name given to an image when it is imported. The repo may include a tag. This parameter may only be used when importing an image. (optional)
     * @param tag Tag or digest. If empty when pulling an image, this causes all tags for the given image to be pulled. (optional)
     * @param message Set commit message for imported image. (optional)
     * @param xRegistryAuth A base64url-encoded auth configuration.  Refer to the [authentication section](#section/Authentication) for details.  (optional)
     * @param platform Platform in the format os[/arch[/variant]] (optional)
     * @param inputImage Image content if the value &#x60;-&#x60; has been specified in fromSrc query parameter (optional)
     * @return void
     */
    suspend fun imageCreate(fromImage: kotlin.String?, fromSrc: kotlin.String?, repo: kotlin.String?, tag: kotlin.String?, message: kotlin.String?, xRegistryAuth: kotlin.String?, platform: kotlin.String?, inputImage: kotlin.String?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = inputImage

        val localVariableQuery = mutableMapOf<String, List<String>>()
        fromImage?.apply { localVariableQuery["fromImage"] = listOf("$fromImage") }
        fromSrc?.apply { localVariableQuery["fromSrc"] = listOf("$fromSrc") }
        repo?.apply { localVariableQuery["repo"] = listOf("$repo") }
        tag?.apply { localVariableQuery["tag"] = listOf("$tag") }
        message?.apply { localVariableQuery["message"] = listOf("$message") }
        platform?.apply { localVariableQuery["platform"] = listOf("$platform") }

        val localVariableHeaders = mutableMapOf<String, String>()
        xRegistryAuth?.apply { localVariableHeaders["X-Registry-Auth"] = this.toString() }

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/images/create",
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
     * Remove an image
     * Remove an image, along with any untagged parent images that were referenced by that image.  Images can&#39;t be removed if they have descendant images, are being used by a running container or are being used by a build. 
     * @param name Image name or ID 
     * @param force Remove the image even if it is being used by stopped containers or has other tags (optional, default to false)
     * @param noprune Do not delete untagged parent images (optional, default to false)
     * @return kotlin.collections.List<ImageDeleteResponseItem>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageDelete(name: kotlin.String, force: kotlin.Boolean?, noprune: kotlin.Boolean?): HttpResponse<kotlin.collections.List<ImageDeleteResponseItem>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        force?.apply { localVariableQuery["force"] = listOf("$force") }
        noprune?.apply { localVariableQuery["noprune"] = listOf("$noprune") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.DELETE,
            "/images/{name}".replace("{" + "name" + "}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<ImageDeleteResponse>().map { value }
    }

    @Serializable
    private class ImageDeleteResponse(val value: List<ImageDeleteResponseItem>) {
        @Serializer(ImageDeleteResponse::class)
        companion object : KSerializer<ImageDeleteResponse> {
            private val serializer: KSerializer<List<ImageDeleteResponseItem>> = serializer<List<ImageDeleteResponseItem>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: ImageDeleteResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = ImageDeleteResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Export an image
     * Get a tarball containing all images and metadata for a repository.  If &#x60;name&#x60; is a specific name and tag (e.g. &#x60;ubuntu:latest&#x60;), then only that image (and its parents) are returned. If &#x60;name&#x60; is an image ID, similarly only that image (and its parents) are returned, but with the exclusion of the &#x60;repositories&#x60; file in the tarball, as there were no image names referenced.  ### Image tarball format  An image tarball contains one directory per image layer (named using its long ID), each containing these files:  - &#x60;VERSION&#x60;: currently &#x60;1.0&#x60; - the file format version - &#x60;json&#x60;: detailed layer information, similar to &#x60;docker inspect layer_id&#x60; - &#x60;layer.tar&#x60;: A tarfile containing the filesystem changes in this layer  The &#x60;layer.tar&#x60; file contains &#x60;aufs&#x60; style &#x60;.wh..wh.aufs&#x60; files and directories for storing attribute changes and deletions.  If the tarball defines a repository, the tarball should also include a &#x60;repositories&#x60; file at the root that contains a list of repository and tag names mapped to layer IDs.  &#x60;&#x60;&#x60;json {   \&quot;hello-world\&quot;: {     \&quot;latest\&quot;: \&quot;565a9d68a73f6706862bfe8409a7f659776d4d60a8d096eb4a3cbce6999cc2a1\&quot;   } } &#x60;&#x60;&#x60; 
     * @param name Image name or ID 
     * @return org.openapitools.client.infrastructure.OctetByteArray
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageGet(name: kotlin.String): HttpResponse<org.openapitools.client.infrastructure.OctetByteArray> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/{name}/get".replace("{" + "name" + "}", "$name"),
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
     * Export several images
     * Get a tarball containing all images and metadata for several image repositories.  For each value of the &#x60;names&#x60; parameter: if it is a specific name and tag (e.g. &#x60;ubuntu:latest&#x60;), then only that image (and its parents) are returned; if it is an image ID, similarly only that image (and its parents) are returned and there would be no names referenced in the &#39;repositories&#39; file for this image ID.  For details on the format, see the [export image endpoint](#operation/ImageGet). 
     * @param names Image names to filter by (optional)
     * @return org.openapitools.client.infrastructure.OctetByteArray
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageGetAll(names: kotlin.collections.List<kotlin.String>?): HttpResponse<org.openapitools.client.infrastructure.OctetByteArray> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        names?.apply { localVariableQuery["names"] = toMultiValue(this, "csv") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/get",
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
     * Get the history of an image
     * Return parent layers of an image.
     * @param name Image name or ID 
     * @return kotlin.collections.List<HistoryResponseItem>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageHistory(name: kotlin.String): HttpResponse<kotlin.collections.List<HistoryResponseItem>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/{name}/history".replace("{" + "name" + "}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<ImageHistoryResponse>().map { value }
    }

    @Serializable
    private class ImageHistoryResponse(val value: List<HistoryResponseItem>) {
        @Serializer(ImageHistoryResponse::class)
        companion object : KSerializer<ImageHistoryResponse> {
            private val serializer: KSerializer<List<HistoryResponseItem>> = serializer<List<HistoryResponseItem>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: ImageHistoryResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = ImageHistoryResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Inspect an image
     * Return low-level information about an image.
     * @param name Image name or id 
     * @return Image
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageInspect(name: kotlin.String): HttpResponse<Image> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/{name}/json".replace("{" + "name" + "}", "$name"),
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
     * List Images
     * Returns a list of images on the server. Note that it uses a different, smaller representation of an image than inspecting a single image.
     * @param all Show all images. Only images from a final layer (no children) are shown by default. (optional, default to false)
     * @param filters A JSON encoded value of the filters (a &#x60;map[string][]string&#x60;) to process on the images list.  Available filters:  - &#x60;before&#x60;&#x3D;(&#x60;&lt;image-name&gt;[:&lt;tag&gt;]&#x60;,  &#x60;&lt;image id&gt;&#x60; or &#x60;&lt;image@digest&gt;&#x60;) - &#x60;dangling&#x3D;true&#x60; - &#x60;label&#x3D;key&#x60; or &#x60;label&#x3D;\&quot;key&#x3D;value\&quot;&#x60; of an image label - &#x60;reference&#x60;&#x3D;(&#x60;&lt;image-name&gt;[:&lt;tag&gt;]&#x60;) - &#x60;since&#x60;&#x3D;(&#x60;&lt;image-name&gt;[:&lt;tag&gt;]&#x60;,  &#x60;&lt;image id&gt;&#x60; or &#x60;&lt;image@digest&gt;&#x60;)  (optional)
     * @param digests Show digest information as a &#x60;RepoDigests&#x60; field on each image. (optional, default to false)
     * @return kotlin.collections.List<ImageSummary>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageList(all: kotlin.Boolean?, filters: kotlin.String?, digests: kotlin.Boolean?): HttpResponse<kotlin.collections.List<ImageSummary>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        all?.apply { localVariableQuery["all"] = listOf("$all") }
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }
        digests?.apply { localVariableQuery["digests"] = listOf("$digests") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/json",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<ImageListResponse>().map { value }
    }

    @Serializable
    private class ImageListResponse(val value: List<ImageSummary>) {
        @Serializer(ImageListResponse::class)
        companion object : KSerializer<ImageListResponse> {
            private val serializer: KSerializer<List<ImageSummary>> = serializer<List<ImageSummary>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: ImageListResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = ImageListResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Import images
     * Load a set of images and tags into a repository.  For details on the format, see the [export image endpoint](#operation/ImageGet). 
     * @param quiet Suppress progress details during load. (optional, default to false)
     * @param imagesTarball Tar archive containing images (optional)
     * @return void
     */
    suspend fun imageLoad(quiet: kotlin.Boolean?, imagesTarball: org.openapitools.client.infrastructure.OctetByteArray?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = imagesTarball

        val localVariableQuery = mutableMapOf<String, List<String>>()
        quiet?.apply { localVariableQuery["quiet"] = listOf("$quiet") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/images/load",
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
     * Delete unused images
     * 
     * @param filters Filters to process on the prune list, encoded as JSON (a &#x60;map[string][]string&#x60;). Available filters:  - &#x60;dangling&#x3D;&lt;boolean&gt;&#x60; When set to &#x60;true&#x60; (or &#x60;1&#x60;), prune only    unused *and* untagged images. When set to &#x60;false&#x60;    (or &#x60;0&#x60;), all unused images are pruned. - &#x60;until&#x3D;&lt;string&gt;&#x60; Prune images created before this timestamp. The &#x60;&lt;timestamp&gt;&#x60; can be Unix timestamps, date formatted timestamps, or Go duration strings (e.g. &#x60;10m&#x60;, &#x60;1h30m&#x60;) computed relative to the daemon machine’s time. - &#x60;label&#x60; (&#x60;label&#x3D;&lt;key&gt;&#x60;, &#x60;label&#x3D;&lt;key&gt;&#x3D;&lt;value&gt;&#x60;, &#x60;label!&#x3D;&lt;key&gt;&#x60;, or &#x60;label!&#x3D;&lt;key&gt;&#x3D;&lt;value&gt;&#x60;) Prune images with (or without, in case &#x60;label!&#x3D;...&#x60; is used) the specified labels.  (optional)
     * @return ImagePruneResponse
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imagePrune(filters: kotlin.String?): HttpResponse<ImagePruneResponse> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/images/prune",
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
     * Push an image
     * Push an image to a registry.  If you wish to push an image on to a private registry, that image must already have a tag which references the registry. For example, &#x60;registry.example.com/myimage:latest&#x60;.  The push is cancelled if the HTTP connection is closed. 
     * @param name Image name or ID. 
     * @param xRegistryAuth A base64url-encoded auth configuration.  Refer to the [authentication section](#section/Authentication) for details.  
     * @param tag The tag to associate with the image on the registry. (optional)
     * @return void
     */
    suspend fun imagePush(name: kotlin.String, xRegistryAuth: kotlin.String, tag: kotlin.String?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        tag?.apply { localVariableQuery["tag"] = listOf("$tag") }

        val localVariableHeaders = mutableMapOf<String, String>()
        xRegistryAuth?.apply { localVariableHeaders["X-Registry-Auth"] = this.toString() }

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/images/{name}/push".replace("{" + "name" + "}", "$name"),
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
     * Search images
     * Search for an image on Docker Hub.
     * @param term Term to search 
     * @param limit Maximum number of results to return (optional)
     * @param filters A JSON encoded value of the filters (a &#x60;map[string][]string&#x60;) to process on the images list. Available filters:  - &#x60;is-automated&#x3D;(true|false)&#x60; - &#x60;is-official&#x3D;(true|false)&#x60; - &#x60;stars&#x3D;&lt;number&gt;&#x60; Matches images that has at least &#39;number&#39; stars.  (optional)
     * @return kotlin.collections.List<ImageSearchResponseItem>
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun imageSearch(term: kotlin.String, limit: kotlin.Int?, filters: kotlin.String?): HttpResponse<kotlin.collections.List<ImageSearchResponseItem>> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        term?.apply { localVariableQuery["term"] = listOf("$term") }
        limit?.apply { localVariableQuery["limit"] = listOf("$limit") }
        filters?.apply { localVariableQuery["filters"] = listOf("$filters") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/images/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<ImageSearchResponse>().map { value }
    }

    @Serializable
    private class ImageSearchResponse(val value: List<ImageSearchResponseItem>) {
        @Serializer(ImageSearchResponse::class)
        companion object : KSerializer<ImageSearchResponse> {
            private val serializer: KSerializer<List<ImageSearchResponseItem>> = serializer<List<ImageSearchResponseItem>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: ImageSearchResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = ImageSearchResponse(serializer.deserialize(decoder))
        }
    }

    /**
     * Tag an image
     * Tag an image so that it becomes part of a repository.
     * @param name Image name or ID to tag. 
     * @param repo The repository to tag in. For example, &#x60;someuser/someimage&#x60;. (optional)
     * @param tag The name of the new tag. (optional)
     * @return void
     */
    suspend fun imageTag(name: kotlin.String, repo: kotlin.String?, tag: kotlin.String?): HttpResponse<Unit> {

        val localVariableAuthNames = listOf<String>()

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        repo?.apply { localVariableQuery["repo"] = listOf("$repo") }
        tag?.apply { localVariableQuery["tag"] = listOf("$tag") }

        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/images/{name}/tag".replace("{" + "name" + "}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }


}