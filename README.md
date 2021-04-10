# org.openapitools.client - Kotlin client library for Docker Engine API

## Requires

* Kotlin 1.3.50

## Build

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.


<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost/v1.41*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ConfigApi* | [**configCreate**](docs/ConfigApi.md#configcreate) | **POST** /configs/create | Create a config
*ConfigApi* | [**configDelete**](docs/ConfigApi.md#configdelete) | **DELETE** /configs/{id} | Delete a config
*ConfigApi* | [**configInspect**](docs/ConfigApi.md#configinspect) | **GET** /configs/{id} | Inspect a config
*ConfigApi* | [**configList**](docs/ConfigApi.md#configlist) | **GET** /configs | List configs
*ConfigApi* | [**configUpdate**](docs/ConfigApi.md#configupdate) | **POST** /configs/{id}/update | Update a Config
*ContainerApi* | [**containerArchive**](docs/ContainerApi.md#containerarchive) | **GET** /containers/{id}/archive | Get an archive of a filesystem resource in a container
*ContainerApi* | [**containerArchiveInfo**](docs/ContainerApi.md#containerarchiveinfo) | **HEAD** /containers/{id}/archive | Get information about files in a container
*ContainerApi* | [**containerAttach**](docs/ContainerApi.md#containerattach) | **POST** /containers/{id}/attach | Attach to a container
*ContainerApi* | [**containerAttachWebsocket**](docs/ContainerApi.md#containerattachwebsocket) | **GET** /containers/{id}/attach/ws | Attach to a container via a websocket
*ContainerApi* | [**containerChanges**](docs/ContainerApi.md#containerchanges) | **GET** /containers/{id}/changes | Get changes on a container’s filesystem
*ContainerApi* | [**containerCreate**](docs/ContainerApi.md#containercreate) | **POST** /containers/create | Create a container
*ContainerApi* | [**containerDelete**](docs/ContainerApi.md#containerdelete) | **DELETE** /containers/{id} | Remove a container
*ContainerApi* | [**containerExport**](docs/ContainerApi.md#containerexport) | **GET** /containers/{id}/export | Export a container
*ContainerApi* | [**containerInspect**](docs/ContainerApi.md#containerinspect) | **GET** /containers/{id}/json | Inspect a container
*ContainerApi* | [**containerKill**](docs/ContainerApi.md#containerkill) | **POST** /containers/{id}/kill | Kill a container
*ContainerApi* | [**containerList**](docs/ContainerApi.md#containerlist) | **GET** /containers/json | List containers
*ContainerApi* | [**containerLogs**](docs/ContainerApi.md#containerlogs) | **GET** /containers/{id}/logs | Get container logs
*ContainerApi* | [**containerPause**](docs/ContainerApi.md#containerpause) | **POST** /containers/{id}/pause | Pause a container
*ContainerApi* | [**containerPrune**](docs/ContainerApi.md#containerprune) | **POST** /containers/prune | Delete stopped containers
*ContainerApi* | [**containerRename**](docs/ContainerApi.md#containerrename) | **POST** /containers/{id}/rename | Rename a container
*ContainerApi* | [**containerResize**](docs/ContainerApi.md#containerresize) | **POST** /containers/{id}/resize | Resize a container TTY
*ContainerApi* | [**containerRestart**](docs/ContainerApi.md#containerrestart) | **POST** /containers/{id}/restart | Restart a container
*ContainerApi* | [**containerStart**](docs/ContainerApi.md#containerstart) | **POST** /containers/{id}/start | Start a container
*ContainerApi* | [**containerStats**](docs/ContainerApi.md#containerstats) | **GET** /containers/{id}/stats | Get container stats based on resource usage
*ContainerApi* | [**containerStop**](docs/ContainerApi.md#containerstop) | **POST** /containers/{id}/stop | Stop a container
*ContainerApi* | [**containerTop**](docs/ContainerApi.md#containertop) | **GET** /containers/{id}/top | List processes running inside a container
*ContainerApi* | [**containerUnpause**](docs/ContainerApi.md#containerunpause) | **POST** /containers/{id}/unpause | Unpause a container
*ContainerApi* | [**containerUpdate**](docs/ContainerApi.md#containerupdate) | **POST** /containers/{id}/update | Update a container
*ContainerApi* | [**containerWait**](docs/ContainerApi.md#containerwait) | **POST** /containers/{id}/wait | Wait for a container
*ContainerApi* | [**putContainerArchive**](docs/ContainerApi.md#putcontainerarchive) | **PUT** /containers/{id}/archive | Extract an archive of files or folders to a directory in a container
*DistributionApi* | [**distributionInspect**](docs/DistributionApi.md#distributioninspect) | **GET** /distribution/{name}/json | Get image information from the registry
*ExecApi* | [**containerExec**](docs/ExecApi.md#containerexec) | **POST** /containers/{id}/exec | Create an exec instance
*ExecApi* | [**execInspect**](docs/ExecApi.md#execinspect) | **GET** /exec/{id}/json | Inspect an exec instance
*ExecApi* | [**execResize**](docs/ExecApi.md#execresize) | **POST** /exec/{id}/resize | Resize an exec instance
*ExecApi* | [**execStart**](docs/ExecApi.md#execstart) | **POST** /exec/{id}/start | Start an exec instance
*ImageApi* | [**buildPrune**](docs/ImageApi.md#buildprune) | **POST** /build/prune | Delete builder cache
*ImageApi* | [**imageBuild**](docs/ImageApi.md#imagebuild) | **POST** /build | Build an image
*ImageApi* | [**imageCommit**](docs/ImageApi.md#imagecommit) | **POST** /commit | Create a new image from a container
*ImageApi* | [**imageCreate**](docs/ImageApi.md#imagecreate) | **POST** /images/create | Create an image
*ImageApi* | [**imageDelete**](docs/ImageApi.md#imagedelete) | **DELETE** /images/{name} | Remove an image
*ImageApi* | [**imageGet**](docs/ImageApi.md#imageget) | **GET** /images/{name}/get | Export an image
*ImageApi* | [**imageGetAll**](docs/ImageApi.md#imagegetall) | **GET** /images/get | Export several images
*ImageApi* | [**imageHistory**](docs/ImageApi.md#imagehistory) | **GET** /images/{name}/history | Get the history of an image
*ImageApi* | [**imageInspect**](docs/ImageApi.md#imageinspect) | **GET** /images/{name}/json | Inspect an image
*ImageApi* | [**imageList**](docs/ImageApi.md#imagelist) | **GET** /images/json | List Images
*ImageApi* | [**imageLoad**](docs/ImageApi.md#imageload) | **POST** /images/load | Import images
*ImageApi* | [**imagePrune**](docs/ImageApi.md#imageprune) | **POST** /images/prune | Delete unused images
*ImageApi* | [**imagePush**](docs/ImageApi.md#imagepush) | **POST** /images/{name}/push | Push an image
*ImageApi* | [**imageSearch**](docs/ImageApi.md#imagesearch) | **GET** /images/search | Search images
*ImageApi* | [**imageTag**](docs/ImageApi.md#imagetag) | **POST** /images/{name}/tag | Tag an image
*NetworkApi* | [**networkConnect**](docs/NetworkApi.md#networkconnect) | **POST** /networks/{id}/connect | Connect a container to a network
*NetworkApi* | [**networkCreate**](docs/NetworkApi.md#networkcreate) | **POST** /networks/create | Create a network
*NetworkApi* | [**networkDelete**](docs/NetworkApi.md#networkdelete) | **DELETE** /networks/{id} | Remove a network
*NetworkApi* | [**networkDisconnect**](docs/NetworkApi.md#networkdisconnect) | **POST** /networks/{id}/disconnect | Disconnect a container from a network
*NetworkApi* | [**networkInspect**](docs/NetworkApi.md#networkinspect) | **GET** /networks/{id} | Inspect a network
*NetworkApi* | [**networkList**](docs/NetworkApi.md#networklist) | **GET** /networks | List networks
*NetworkApi* | [**networkPrune**](docs/NetworkApi.md#networkprune) | **POST** /networks/prune | Delete unused networks
*NodeApi* | [**nodeDelete**](docs/NodeApi.md#nodedelete) | **DELETE** /nodes/{id} | Delete a node
*NodeApi* | [**nodeInspect**](docs/NodeApi.md#nodeinspect) | **GET** /nodes/{id} | Inspect a node
*NodeApi* | [**nodeList**](docs/NodeApi.md#nodelist) | **GET** /nodes | List nodes
*NodeApi* | [**nodeUpdate**](docs/NodeApi.md#nodeupdate) | **POST** /nodes/{id}/update | Update a node
*PluginApi* | [**getPluginPrivileges**](docs/PluginApi.md#getpluginprivileges) | **GET** /plugins/privileges | Get plugin privileges
*PluginApi* | [**pluginCreate**](docs/PluginApi.md#plugincreate) | **POST** /plugins/create | Create a plugin
*PluginApi* | [**pluginDelete**](docs/PluginApi.md#plugindelete) | **DELETE** /plugins/{name} | Remove a plugin
*PluginApi* | [**pluginDisable**](docs/PluginApi.md#plugindisable) | **POST** /plugins/{name}/disable | Disable a plugin
*PluginApi* | [**pluginEnable**](docs/PluginApi.md#pluginenable) | **POST** /plugins/{name}/enable | Enable a plugin
*PluginApi* | [**pluginInspect**](docs/PluginApi.md#plugininspect) | **GET** /plugins/{name}/json | Inspect a plugin
*PluginApi* | [**pluginList**](docs/PluginApi.md#pluginlist) | **GET** /plugins | List plugins
*PluginApi* | [**pluginPull**](docs/PluginApi.md#pluginpull) | **POST** /plugins/pull | Install a plugin
*PluginApi* | [**pluginPush**](docs/PluginApi.md#pluginpush) | **POST** /plugins/{name}/push | Push a plugin
*PluginApi* | [**pluginSet**](docs/PluginApi.md#pluginset) | **POST** /plugins/{name}/set | Configure a plugin
*PluginApi* | [**pluginUpgrade**](docs/PluginApi.md#pluginupgrade) | **POST** /plugins/{name}/upgrade | Upgrade a plugin
*SecretApi* | [**secretCreate**](docs/SecretApi.md#secretcreate) | **POST** /secrets/create | Create a secret
*SecretApi* | [**secretDelete**](docs/SecretApi.md#secretdelete) | **DELETE** /secrets/{id} | Delete a secret
*SecretApi* | [**secretInspect**](docs/SecretApi.md#secretinspect) | **GET** /secrets/{id} | Inspect a secret
*SecretApi* | [**secretList**](docs/SecretApi.md#secretlist) | **GET** /secrets | List secrets
*SecretApi* | [**secretUpdate**](docs/SecretApi.md#secretupdate) | **POST** /secrets/{id}/update | Update a Secret
*ServiceApi* | [**serviceCreate**](docs/ServiceApi.md#servicecreate) | **POST** /services/create | Create a service
*ServiceApi* | [**serviceDelete**](docs/ServiceApi.md#servicedelete) | **DELETE** /services/{id} | Delete a service
*ServiceApi* | [**serviceInspect**](docs/ServiceApi.md#serviceinspect) | **GET** /services/{id} | Inspect a service
*ServiceApi* | [**serviceList**](docs/ServiceApi.md#servicelist) | **GET** /services | List services
*ServiceApi* | [**serviceLogs**](docs/ServiceApi.md#servicelogs) | **GET** /services/{id}/logs | Get service logs
*ServiceApi* | [**serviceUpdate**](docs/ServiceApi.md#serviceupdate) | **POST** /services/{id}/update | Update a service
*SessionApi* | [**session**](docs/SessionApi.md#session) | **POST** /session | Initialize interactive session
*SwarmApi* | [**swarmInit**](docs/SwarmApi.md#swarminit) | **POST** /swarm/init | Initialize a new swarm
*SwarmApi* | [**swarmInspect**](docs/SwarmApi.md#swarminspect) | **GET** /swarm | Inspect swarm
*SwarmApi* | [**swarmJoin**](docs/SwarmApi.md#swarmjoin) | **POST** /swarm/join | Join an existing swarm
*SwarmApi* | [**swarmLeave**](docs/SwarmApi.md#swarmleave) | **POST** /swarm/leave | Leave a swarm
*SwarmApi* | [**swarmUnlock**](docs/SwarmApi.md#swarmunlock) | **POST** /swarm/unlock | Unlock a locked manager
*SwarmApi* | [**swarmUnlockkey**](docs/SwarmApi.md#swarmunlockkey) | **GET** /swarm/unlockkey | Get the unlock key
*SwarmApi* | [**swarmUpdate**](docs/SwarmApi.md#swarmupdate) | **POST** /swarm/update | Update a swarm
*SystemApi* | [**systemAuth**](docs/SystemApi.md#systemauth) | **POST** /auth | Check auth configuration
*SystemApi* | [**systemDataUsage**](docs/SystemApi.md#systemdatausage) | **GET** /system/df | Get data usage information
*SystemApi* | [**systemEvents**](docs/SystemApi.md#systemevents) | **GET** /events | Monitor events
*SystemApi* | [**systemInfo**](docs/SystemApi.md#systeminfo) | **GET** /info | Get system information
*SystemApi* | [**systemPing**](docs/SystemApi.md#systemping) | **GET** /_ping | Ping
*SystemApi* | [**systemPingHead**](docs/SystemApi.md#systempinghead) | **HEAD** /_ping | Ping
*SystemApi* | [**systemVersion**](docs/SystemApi.md#systemversion) | **GET** /version | Get version
*TaskApi* | [**taskInspect**](docs/TaskApi.md#taskinspect) | **GET** /tasks/{id} | Inspect a task
*TaskApi* | [**taskList**](docs/TaskApi.md#tasklist) | **GET** /tasks | List tasks
*TaskApi* | [**taskLogs**](docs/TaskApi.md#tasklogs) | **GET** /tasks/{id}/logs | Get task logs
*VolumeApi* | [**volumeCreate**](docs/VolumeApi.md#volumecreate) | **POST** /volumes/create | Create a volume
*VolumeApi* | [**volumeDelete**](docs/VolumeApi.md#volumedelete) | **DELETE** /volumes/{name} | Remove a volume
*VolumeApi* | [**volumeInspect**](docs/VolumeApi.md#volumeinspect) | **GET** /volumes/{name} | Inspect a volume
*VolumeApi* | [**volumeList**](docs/VolumeApi.md#volumelist) | **GET** /volumes | List volumes
*VolumeApi* | [**volumePrune**](docs/VolumeApi.md#volumeprune) | **POST** /volumes/prune | Delete unused volumes


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.Address](docs/Address.md)
 - [org.openapitools.client.models.AuthConfig](docs/AuthConfig.md)
 - [org.openapitools.client.models.BuildCache](docs/BuildCache.md)
 - [org.openapitools.client.models.BuildInfo](docs/BuildInfo.md)
 - [org.openapitools.client.models.BuildPruneResponse](docs/BuildPruneResponse.md)
 - [org.openapitools.client.models.ClusterInfo](docs/ClusterInfo.md)
 - [org.openapitools.client.models.Commit](docs/Commit.md)
 - [org.openapitools.client.models.Config](docs/Config.md)
 - [org.openapitools.client.models.ConfigSpec](docs/ConfigSpec.md)
 - [org.openapitools.client.models.ContainerChangeResponseItem](docs/ContainerChangeResponseItem.md)
 - [org.openapitools.client.models.ContainerConfig](docs/ContainerConfig.md)
 - [org.openapitools.client.models.ContainerCreateResponse](docs/ContainerCreateResponse.md)
 - [org.openapitools.client.models.ContainerInspectResponse](docs/ContainerInspectResponse.md)
 - [org.openapitools.client.models.ContainerPruneResponse](docs/ContainerPruneResponse.md)
 - [org.openapitools.client.models.ContainerState](docs/ContainerState.md)
 - [org.openapitools.client.models.ContainerSummary](docs/ContainerSummary.md)
 - [org.openapitools.client.models.ContainerSummaryHostConfig](docs/ContainerSummaryHostConfig.md)
 - [org.openapitools.client.models.ContainerSummaryNetworkSettings](docs/ContainerSummaryNetworkSettings.md)
 - [org.openapitools.client.models.ContainerTopResponse](docs/ContainerTopResponse.md)
 - [org.openapitools.client.models.ContainerUpdateResponse](docs/ContainerUpdateResponse.md)
 - [org.openapitools.client.models.ContainerWaitResponse](docs/ContainerWaitResponse.md)
 - [org.openapitools.client.models.ContainerWaitResponseError](docs/ContainerWaitResponseError.md)
 - [org.openapitools.client.models.CreateImageInfo](docs/CreateImageInfo.md)
 - [org.openapitools.client.models.DeviceMapping](docs/DeviceMapping.md)
 - [org.openapitools.client.models.DeviceRequest](docs/DeviceRequest.md)
 - [org.openapitools.client.models.DistributionInspectResponse](docs/DistributionInspectResponse.md)
 - [org.openapitools.client.models.DistributionInspectResponseDescriptor](docs/DistributionInspectResponseDescriptor.md)
 - [org.openapitools.client.models.DistributionInspectResponsePlatforms](docs/DistributionInspectResponsePlatforms.md)
 - [org.openapitools.client.models.Driver](docs/Driver.md)
 - [org.openapitools.client.models.EndpointIPAMConfig](docs/EndpointIPAMConfig.md)
 - [org.openapitools.client.models.EndpointPortConfig](docs/EndpointPortConfig.md)
 - [org.openapitools.client.models.EndpointSettings](docs/EndpointSettings.md)
 - [org.openapitools.client.models.EndpointSpec](docs/EndpointSpec.md)
 - [org.openapitools.client.models.EngineDescription](docs/EngineDescription.md)
 - [org.openapitools.client.models.EngineDescriptionPlugins](docs/EngineDescriptionPlugins.md)
 - [org.openapitools.client.models.ErrorDetail](docs/ErrorDetail.md)
 - [org.openapitools.client.models.ErrorResponse](docs/ErrorResponse.md)
 - [org.openapitools.client.models.ExecInspectResponse](docs/ExecInspectResponse.md)
 - [org.openapitools.client.models.GraphDriverData](docs/GraphDriverData.md)
 - [org.openapitools.client.models.Health](docs/Health.md)
 - [org.openapitools.client.models.HealthConfig](docs/HealthConfig.md)
 - [org.openapitools.client.models.HealthcheckResult](docs/HealthcheckResult.md)
 - [org.openapitools.client.models.HistoryResponseItem](docs/HistoryResponseItem.md)
 - [org.openapitools.client.models.HostConfig](docs/HostConfig.md)
 - [org.openapitools.client.models.HostConfigAllOf](docs/HostConfigAllOf.md)
 - [org.openapitools.client.models.HostConfigAllOfLogConfig](docs/HostConfigAllOfLogConfig.md)
 - [org.openapitools.client.models.IPAM](docs/IPAM.md)
 - [org.openapitools.client.models.IdResponse](docs/IdResponse.md)
 - [org.openapitools.client.models.Image](docs/Image.md)
 - [org.openapitools.client.models.ImageDeleteResponseItem](docs/ImageDeleteResponseItem.md)
 - [org.openapitools.client.models.ImageID](docs/ImageID.md)
 - [org.openapitools.client.models.ImageMetadata](docs/ImageMetadata.md)
 - [org.openapitools.client.models.ImagePruneResponse](docs/ImagePruneResponse.md)
 - [org.openapitools.client.models.ImageRootFS](docs/ImageRootFS.md)
 - [org.openapitools.client.models.ImageSearchResponseItem](docs/ImageSearchResponseItem.md)
 - [org.openapitools.client.models.ImageSummary](docs/ImageSummary.md)
 - [org.openapitools.client.models.IndexInfo](docs/IndexInfo.md)
 - [org.openapitools.client.models.InlineObject](docs/InlineObject.md)
 - [org.openapitools.client.models.InlineObject1](docs/InlineObject1.md)
 - [org.openapitools.client.models.InlineObject2](docs/InlineObject2.md)
 - [org.openapitools.client.models.InlineObject3](docs/InlineObject3.md)
 - [org.openapitools.client.models.InlineObject4](docs/InlineObject4.md)
 - [org.openapitools.client.models.InlineObject5](docs/InlineObject5.md)
 - [org.openapitools.client.models.InlineObject6](docs/InlineObject6.md)
 - [org.openapitools.client.models.InlineObject7](docs/InlineObject7.md)
 - [org.openapitools.client.models.InlineResponse400](docs/InlineResponse400.md)
 - [org.openapitools.client.models.JoinTokens](docs/JoinTokens.md)
 - [org.openapitools.client.models.Limit](docs/Limit.md)
 - [org.openapitools.client.models.LocalNodeState](docs/LocalNodeState.md)
 - [org.openapitools.client.models.ManagerStatus](docs/ManagerStatus.md)
 - [org.openapitools.client.models.Mount](docs/Mount.md)
 - [org.openapitools.client.models.MountBindOptions](docs/MountBindOptions.md)
 - [org.openapitools.client.models.MountPoint](docs/MountPoint.md)
 - [org.openapitools.client.models.MountTmpfsOptions](docs/MountTmpfsOptions.md)
 - [org.openapitools.client.models.MountVolumeOptions](docs/MountVolumeOptions.md)
 - [org.openapitools.client.models.MountVolumeOptionsDriverConfig](docs/MountVolumeOptionsDriverConfig.md)
 - [org.openapitools.client.models.Network](docs/Network.md)
 - [org.openapitools.client.models.NetworkAttachmentConfig](docs/NetworkAttachmentConfig.md)
 - [org.openapitools.client.models.NetworkContainer](docs/NetworkContainer.md)
 - [org.openapitools.client.models.NetworkCreateResponse](docs/NetworkCreateResponse.md)
 - [org.openapitools.client.models.NetworkPruneResponse](docs/NetworkPruneResponse.md)
 - [org.openapitools.client.models.NetworkSettings](docs/NetworkSettings.md)
 - [org.openapitools.client.models.NetworkingConfig](docs/NetworkingConfig.md)
 - [org.openapitools.client.models.Node](docs/Node.md)
 - [org.openapitools.client.models.NodeDescription](docs/NodeDescription.md)
 - [org.openapitools.client.models.NodeSpec](docs/NodeSpec.md)
 - [org.openapitools.client.models.NodeState](docs/NodeState.md)
 - [org.openapitools.client.models.NodeStatus](docs/NodeStatus.md)
 - [org.openapitools.client.models.ObjectVersion](docs/ObjectVersion.md)
 - [org.openapitools.client.models.PeerNode](docs/PeerNode.md)
 - [org.openapitools.client.models.Platform](docs/Platform.md)
 - [org.openapitools.client.models.Plugin](docs/Plugin.md)
 - [org.openapitools.client.models.PluginConfig](docs/PluginConfig.md)
 - [org.openapitools.client.models.PluginConfigArgs](docs/PluginConfigArgs.md)
 - [org.openapitools.client.models.PluginConfigInterface](docs/PluginConfigInterface.md)
 - [org.openapitools.client.models.PluginConfigLinux](docs/PluginConfigLinux.md)
 - [org.openapitools.client.models.PluginConfigNetwork](docs/PluginConfigNetwork.md)
 - [org.openapitools.client.models.PluginConfigRootfs](docs/PluginConfigRootfs.md)
 - [org.openapitools.client.models.PluginConfigUser](docs/PluginConfigUser.md)
 - [org.openapitools.client.models.PluginDevice](docs/PluginDevice.md)
 - [org.openapitools.client.models.PluginEnv](docs/PluginEnv.md)
 - [org.openapitools.client.models.PluginInterfaceType](docs/PluginInterfaceType.md)
 - [org.openapitools.client.models.PluginMount](docs/PluginMount.md)
 - [org.openapitools.client.models.PluginPrivilegeItem](docs/PluginPrivilegeItem.md)
 - [org.openapitools.client.models.PluginSettings](docs/PluginSettings.md)
 - [org.openapitools.client.models.PluginsInfo](docs/PluginsInfo.md)
 - [org.openapitools.client.models.Port](docs/Port.md)
 - [org.openapitools.client.models.PortBinding](docs/PortBinding.md)
 - [org.openapitools.client.models.ProcessConfig](docs/ProcessConfig.md)
 - [org.openapitools.client.models.ProgressDetail](docs/ProgressDetail.md)
 - [org.openapitools.client.models.PushImageInfo](docs/PushImageInfo.md)
 - [org.openapitools.client.models.Reachability](docs/Reachability.md)
 - [org.openapitools.client.models.RegistryServiceConfig](docs/RegistryServiceConfig.md)
 - [org.openapitools.client.models.ResourceObject](docs/ResourceObject.md)
 - [org.openapitools.client.models.Resources](docs/Resources.md)
 - [org.openapitools.client.models.ResourcesBlkioWeightDevice](docs/ResourcesBlkioWeightDevice.md)
 - [org.openapitools.client.models.ResourcesUlimits](docs/ResourcesUlimits.md)
 - [org.openapitools.client.models.RestartPolicy](docs/RestartPolicy.md)
 - [org.openapitools.client.models.Runtime](docs/Runtime.md)
 - [org.openapitools.client.models.Secret](docs/Secret.md)
 - [org.openapitools.client.models.SecretSpec](docs/SecretSpec.md)
 - [org.openapitools.client.models.Service](docs/Service.md)
 - [org.openapitools.client.models.ServiceCreateResponse](docs/ServiceCreateResponse.md)
 - [org.openapitools.client.models.ServiceEndpoint](docs/ServiceEndpoint.md)
 - [org.openapitools.client.models.ServiceEndpointVirtualIPs](docs/ServiceEndpointVirtualIPs.md)
 - [org.openapitools.client.models.ServiceJobStatus](docs/ServiceJobStatus.md)
 - [org.openapitools.client.models.ServiceServiceStatus](docs/ServiceServiceStatus.md)
 - [org.openapitools.client.models.ServiceSpec](docs/ServiceSpec.md)
 - [org.openapitools.client.models.ServiceSpecMode](docs/ServiceSpecMode.md)
 - [org.openapitools.client.models.ServiceSpecModeReplicated](docs/ServiceSpecModeReplicated.md)
 - [org.openapitools.client.models.ServiceSpecModeReplicatedJob](docs/ServiceSpecModeReplicatedJob.md)
 - [org.openapitools.client.models.ServiceSpecRollbackConfig](docs/ServiceSpecRollbackConfig.md)
 - [org.openapitools.client.models.ServiceSpecUpdateConfig](docs/ServiceSpecUpdateConfig.md)
 - [org.openapitools.client.models.ServiceUpdateResponse](docs/ServiceUpdateResponse.md)
 - [org.openapitools.client.models.ServiceUpdateStatus](docs/ServiceUpdateStatus.md)
 - [org.openapitools.client.models.Swarm](docs/Swarm.md)
 - [org.openapitools.client.models.SwarmAllOf](docs/SwarmAllOf.md)
 - [org.openapitools.client.models.SwarmInfo](docs/SwarmInfo.md)
 - [org.openapitools.client.models.SwarmSpec](docs/SwarmSpec.md)
 - [org.openapitools.client.models.SwarmSpecCAConfig](docs/SwarmSpecCAConfig.md)
 - [org.openapitools.client.models.SwarmSpecCAConfigExternalCAs](docs/SwarmSpecCAConfigExternalCAs.md)
 - [org.openapitools.client.models.SwarmSpecDispatcher](docs/SwarmSpecDispatcher.md)
 - [org.openapitools.client.models.SwarmSpecEncryptionConfig](docs/SwarmSpecEncryptionConfig.md)
 - [org.openapitools.client.models.SwarmSpecOrchestration](docs/SwarmSpecOrchestration.md)
 - [org.openapitools.client.models.SwarmSpecRaft](docs/SwarmSpecRaft.md)
 - [org.openapitools.client.models.SwarmSpecTaskDefaults](docs/SwarmSpecTaskDefaults.md)
 - [org.openapitools.client.models.SwarmSpecTaskDefaultsLogDriver](docs/SwarmSpecTaskDefaultsLogDriver.md)
 - [org.openapitools.client.models.SystemAuthResponse](docs/SystemAuthResponse.md)
 - [org.openapitools.client.models.SystemDataUsageResponse](docs/SystemDataUsageResponse.md)
 - [org.openapitools.client.models.SystemEventsResponse](docs/SystemEventsResponse.md)
 - [org.openapitools.client.models.SystemEventsResponseActor](docs/SystemEventsResponseActor.md)
 - [org.openapitools.client.models.SystemInfo](docs/SystemInfo.md)
 - [org.openapitools.client.models.SystemInfoDefaultAddressPools](docs/SystemInfoDefaultAddressPools.md)
 - [org.openapitools.client.models.SystemVersion](docs/SystemVersion.md)
 - [org.openapitools.client.models.SystemVersionComponents](docs/SystemVersionComponents.md)
 - [org.openapitools.client.models.SystemVersionPlatform](docs/SystemVersionPlatform.md)
 - [org.openapitools.client.models.TLSInfo](docs/TLSInfo.md)
 - [org.openapitools.client.models.Task](docs/Task.md)
 - [org.openapitools.client.models.TaskSpec](docs/TaskSpec.md)
 - [org.openapitools.client.models.TaskSpecContainerSpec](docs/TaskSpecContainerSpec.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecConfigs](docs/TaskSpecContainerSpecConfigs.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecDNSConfig](docs/TaskSpecContainerSpecDNSConfig.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecFile](docs/TaskSpecContainerSpecFile.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecFile1](docs/TaskSpecContainerSpecFile1.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecPrivileges](docs/TaskSpecContainerSpecPrivileges.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecPrivilegesCredentialSpec](docs/TaskSpecContainerSpecPrivilegesCredentialSpec.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecPrivilegesSELinuxContext](docs/TaskSpecContainerSpecPrivilegesSELinuxContext.md)
 - [org.openapitools.client.models.TaskSpecContainerSpecSecrets](docs/TaskSpecContainerSpecSecrets.md)
 - [org.openapitools.client.models.TaskSpecLogDriver](docs/TaskSpecLogDriver.md)
 - [org.openapitools.client.models.TaskSpecNetworkAttachmentSpec](docs/TaskSpecNetworkAttachmentSpec.md)
 - [org.openapitools.client.models.TaskSpecPlacement](docs/TaskSpecPlacement.md)
 - [org.openapitools.client.models.TaskSpecPlacementPreferences](docs/TaskSpecPlacementPreferences.md)
 - [org.openapitools.client.models.TaskSpecPlacementSpread](docs/TaskSpecPlacementSpread.md)
 - [org.openapitools.client.models.TaskSpecPluginSpec](docs/TaskSpecPluginSpec.md)
 - [org.openapitools.client.models.TaskSpecResources](docs/TaskSpecResources.md)
 - [org.openapitools.client.models.TaskSpecRestartPolicy](docs/TaskSpecRestartPolicy.md)
 - [org.openapitools.client.models.TaskState](docs/TaskState.md)
 - [org.openapitools.client.models.TaskStatus](docs/TaskStatus.md)
 - [org.openapitools.client.models.TaskStatusContainerStatus](docs/TaskStatusContainerStatus.md)
 - [org.openapitools.client.models.ThrottleDevice](docs/ThrottleDevice.md)
 - [org.openapitools.client.models.UnlockKeyResponse](docs/UnlockKeyResponse.md)
 - [org.openapitools.client.models.Volume](docs/Volume.md)
 - [org.openapitools.client.models.VolumeConfig](docs/VolumeConfig.md)
 - [org.openapitools.client.models.VolumeListResponse](docs/VolumeListResponse.md)
 - [org.openapitools.client.models.VolumePruneResponse](docs/VolumePruneResponse.md)
 - [org.openapitools.client.models.VolumeUsageData](docs/VolumeUsageData.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
