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
package br.com.devsrsouza.kotlin.docker.models

import br.com.devsrsouza.kotlin.docker.models.DeviceMapping
import br.com.devsrsouza.kotlin.docker.models.DeviceRequest
import br.com.devsrsouza.kotlin.docker.models.ResourcesBlkioWeightDevice
import br.com.devsrsouza.kotlin.docker.models.ResourcesUlimits
import br.com.devsrsouza.kotlin.docker.models.ThrottleDevice

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * A container's resources (cgroups config, ulimits, etc)
 * @param cpuShares An integer value representing this container's relative CPU weight versus other containers. 
 * @param memory Memory limit in bytes.
 * @param cgroupParent Path to `cgroups` under which the container's `cgroup` is created. If the path is not absolute, the path is considered to be relative to the `cgroups` path of the init process. Cgroups are created if they do not already exist. 
 * @param blkioWeight Block IO weight (relative weight).
 * @param blkioWeightDevice Block IO weight (relative device weight) in the form:  ``` [{\"Path\": \"device_path\", \"Weight\": weight}] ``` 
 * @param blkioDeviceReadBps Limit read rate (bytes per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ``` 
 * @param blkioDeviceWriteBps Limit write rate (bytes per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ``` 
 * @param blkioDeviceReadIOps Limit read rate (IO per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ``` 
 * @param blkioDeviceWriteIOps Limit write rate (IO per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ``` 
 * @param cpuPeriod The length of a CPU period in microseconds.
 * @param cpuQuota Microseconds of CPU time that the container can get in a CPU period. 
 * @param cpuRealtimePeriod The length of a CPU real-time period in microseconds. Set to 0 to allocate no time allocated to real-time tasks. 
 * @param cpuRealtimeRuntime The length of a CPU real-time runtime in microseconds. Set to 0 to allocate no time allocated to real-time tasks. 
 * @param cpusetCpus CPUs in which to allow execution (e.g., `0-3`, `0,1`). 
 * @param cpusetMems Memory nodes (MEMs) in which to allow execution (0-3, 0,1). Only effective on NUMA systems. 
 * @param devices A list of devices to add to the container.
 * @param deviceCgroupRules a list of cgroup rules to apply to the container
 * @param deviceRequests A list of requests for devices to be sent to device drivers. 
 * @param kernelMemory Kernel memory limit in bytes.  <p><br /></p>  > **Deprecated**: This field is deprecated as the kernel 5.4 deprecated > `kmem.limit_in_bytes`. 
 * @param kernelMemoryTCP Hard limit for kernel TCP buffer memory (in bytes).
 * @param memoryReservation Memory soft limit in bytes.
 * @param memorySwap Total memory limit (memory + swap). Set as `-1` to enable unlimited swap. 
 * @param memorySwappiness Tune a container's memory swappiness behavior. Accepts an integer between 0 and 100. 
 * @param nanoCpus CPU quota in units of 10<sup>-9</sup> CPUs.
 * @param oomKillDisable Disable OOM Killer for the container.
 * @param init Run an init inside the container that forwards signals and reaps processes. This field is omitted if empty, and the default (as configured on the daemon) is used. 
 * @param pidsLimit Tune a container's PIDs limit. Set `0` or `-1` for unlimited, or `null` to not change. 
 * @param ulimits A list of resource limits to set in the container. For example:  ``` {\"Name\": \"nofile\", \"Soft\": 1024, \"Hard\": 2048} ``` 
 * @param cpuCount The number of usable CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last. 
 * @param cpuPercent The usable percentage of the available CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last. 
 * @param ioMaximumIOps Maximum IOps for the container system drive (Windows only)
 * @param ioMaximumBandwidth Maximum IO in bytes per second for the container system drive (Windows only). 
 */
@Serializable
data class Resources (
    /* An integer value representing this container's relative CPU weight versus other containers.  */
    @SerialName(value = "CpuShares") val cpuShares: kotlin.Int? = null,
    /* Memory limit in bytes. */
    @SerialName(value = "Memory") val memory: kotlin.Long? = null,
    /* Path to `cgroups` under which the container's `cgroup` is created. If the path is not absolute, the path is considered to be relative to the `cgroups` path of the init process. Cgroups are created if they do not already exist.  */
    @SerialName(value = "CgroupParent") val cgroupParent: kotlin.String? = null,
    /* Block IO weight (relative weight). */
    @SerialName(value = "BlkioWeight") val blkioWeight: kotlin.Int? = null,
    /* Block IO weight (relative device weight) in the form:  ``` [{\"Path\": \"device_path\", \"Weight\": weight}] ```  */
    @SerialName(value = "BlkioWeightDevice") val blkioWeightDevice: kotlin.collections.List<ResourcesBlkioWeightDevice>? = null,
    /* Limit read rate (bytes per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    @SerialName(value = "BlkioDeviceReadBps") val blkioDeviceReadBps: kotlin.collections.List<ThrottleDevice>? = null,
    /* Limit write rate (bytes per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    @SerialName(value = "BlkioDeviceWriteBps") val blkioDeviceWriteBps: kotlin.collections.List<ThrottleDevice>? = null,
    /* Limit read rate (IO per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    @SerialName(value = "BlkioDeviceReadIOps") val blkioDeviceReadIOps: kotlin.collections.List<ThrottleDevice>? = null,
    /* Limit write rate (IO per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    @SerialName(value = "BlkioDeviceWriteIOps") val blkioDeviceWriteIOps: kotlin.collections.List<ThrottleDevice>? = null,
    /* The length of a CPU period in microseconds. */
    @SerialName(value = "CpuPeriod") val cpuPeriod: kotlin.Long? = null,
    /* Microseconds of CPU time that the container can get in a CPU period.  */
    @SerialName(value = "CpuQuota") val cpuQuota: kotlin.Long? = null,
    /* The length of a CPU real-time period in microseconds. Set to 0 to allocate no time allocated to real-time tasks.  */
    @SerialName(value = "CpuRealtimePeriod") val cpuRealtimePeriod: kotlin.Long? = null,
    /* The length of a CPU real-time runtime in microseconds. Set to 0 to allocate no time allocated to real-time tasks.  */
    @SerialName(value = "CpuRealtimeRuntime") val cpuRealtimeRuntime: kotlin.Long? = null,
    /* CPUs in which to allow execution (e.g., `0-3`, `0,1`).  */
    @SerialName(value = "CpusetCpus") val cpusetCpus: kotlin.String? = null,
    /* Memory nodes (MEMs) in which to allow execution (0-3, 0,1). Only effective on NUMA systems.  */
    @SerialName(value = "CpusetMems") val cpusetMems: kotlin.String? = null,
    /* A list of devices to add to the container. */
    @SerialName(value = "Devices") val devices: kotlin.collections.List<DeviceMapping>? = null,
    /* a list of cgroup rules to apply to the container */
    @SerialName(value = "DeviceCgroupRules") val deviceCgroupRules: kotlin.collections.List<kotlin.String>? = null,
    /* A list of requests for devices to be sent to device drivers.  */
    @SerialName(value = "DeviceRequests") val deviceRequests: kotlin.collections.List<DeviceRequest>? = null,
    /* Kernel memory limit in bytes.  <p><br /></p>  > **Deprecated**: This field is deprecated as the kernel 5.4 deprecated > `kmem.limit_in_bytes`.  */
    @SerialName(value = "KernelMemory") val kernelMemory: kotlin.Long? = null,
    /* Hard limit for kernel TCP buffer memory (in bytes). */
    @SerialName(value = "KernelMemoryTCP") val kernelMemoryTCP: kotlin.Long? = null,
    /* Memory soft limit in bytes. */
    @SerialName(value = "MemoryReservation") val memoryReservation: kotlin.Long? = null,
    /* Total memory limit (memory + swap). Set as `-1` to enable unlimited swap.  */
    @SerialName(value = "MemorySwap") val memorySwap: kotlin.Long? = null,
    /* Tune a container's memory swappiness behavior. Accepts an integer between 0 and 100.  */
    @SerialName(value = "MemorySwappiness") val memorySwappiness: kotlin.Long? = null,
    /* CPU quota in units of 10<sup>-9</sup> CPUs. */
    @SerialName(value = "NanoCpus") val nanoCpus: kotlin.Long? = null,
    /* Disable OOM Killer for the container. */
    @SerialName(value = "OomKillDisable") val oomKillDisable: kotlin.Boolean? = null,
    /* Run an init inside the container that forwards signals and reaps processes. This field is omitted if empty, and the default (as configured on the daemon) is used.  */
    @SerialName(value = "Init") val init: kotlin.Boolean? = null,
    /* Tune a container's PIDs limit. Set `0` or `-1` for unlimited, or `null` to not change.  */
    @SerialName(value = "PidsLimit") val pidsLimit: kotlin.Long? = null,
    /* A list of resource limits to set in the container. For example:  ``` {\"Name\": \"nofile\", \"Soft\": 1024, \"Hard\": 2048} ```  */
    @SerialName(value = "Ulimits") val ulimits: kotlin.collections.List<ResourcesUlimits>? = null,
    /* The number of usable CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last.  */
    @SerialName(value = "CpuCount") val cpuCount: kotlin.Long? = null,
    /* The usable percentage of the available CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last.  */
    @SerialName(value = "CpuPercent") val cpuPercent: kotlin.Long? = null,
    /* Maximum IOps for the container system drive (Windows only) */
    @SerialName(value = "IOMaximumIOps") val ioMaximumIOps: kotlin.Long? = null,
    /* Maximum IO in bytes per second for the container system drive (Windows only).  */
    @SerialName(value = "IOMaximumBandwidth") val ioMaximumBandwidth: kotlin.Long? = null
)

