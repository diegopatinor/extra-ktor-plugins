package io.github.flaxoos.discovery.eureka.config

import com.netflix.eureka.DefaultEurekaServerConfig
import com.netflix.eureka.EurekaServerConfig
import com.netflix.eureka.aws.AwsBindingStrategy
import io.ktor.server.config.ApplicationConfig

public class EurekaServerConfiguration internal constructor(
    @get:JvmName("_namespace")
    public var namespace: String,

    @get:JvmName("_awsAccessId")
    public var awsAccessId: String? = null,

    @get:JvmName("_awsSecretKey")
    public var awsSecretKey: String? = null,

    @get:JvmName("_eipBindRebindRetries")
    public var eipBindRebindRetries: Int? = null,

    @get:JvmName("_eipBindingRetryIntervalMsWhenUnbound")
    public var eipBindingRetryIntervalMsWhenUnbound: Int? = null,

    @get:JvmName("_eipBindingRetryIntervalMs")
    public var eipBindingRetryIntervalMs: Int? = null,

    @get:JvmName("_shouldEnableSelfPreservation")
    public var shouldEnableSelfPreservation: Boolean? = null,

    @get:JvmName("_renewalPercentThreshold")
    public var renewalPercentThreshold: Double? = null,

    @get:JvmName("_renewalThresholdUpdateIntervalMs")
    public var renewalThresholdUpdateIntervalMs: Int? = null,

    @get:JvmName("_expectedClientRenewalIntervalSeconds")
    public var expectedClientRenewalIntervalSeconds: Int? = null,

    @get:JvmName("_peerEurekaNodesUpdateIntervalMs")
    public var peerEurekaNodesUpdateIntervalMs: Int? = null,

    @get:JvmName("_shouldEnableReplicatedRequestCompression")
    public var shouldEnableReplicatedRequestCompression: Boolean? = null,

    @get:JvmName("_numberOfReplicationRetries")
    public var numberOfReplicationRetries: Int? = null,

    @get:JvmName("_peerEurekaStatusRefreshTimeIntervalMs")
    public var peerEurekaStatusRefreshTimeIntervalMs: Int? = null,

    @get:JvmName("_waitTimeInMsWhenSyncEmpty")
    public var waitTimeInMsWhenSyncEmpty: Int? = null,

    @get:JvmName("_peerNodeConnectTimeoutMs")
    public var peerNodeConnectTimeoutMs: Int? = null,

    @get:JvmName("_peerNodeReadTimeoutMs")
    public var peerNodeReadTimeoutMs: Int? = null,

    @get:JvmName("_peerNodeTotalConnections")
    public var peerNodeTotalConnections: Int? = null,

    @get:JvmName("_peerNodeTotalConnectionsPerHost")
    public var peerNodeTotalConnectionsPerHost: Int? = null,

    @get:JvmName("_peerNodeConnectionIdleTimeoutSeconds")
    public var peerNodeConnectionIdleTimeoutSeconds: Int? = null,

    @get:JvmName("_retentionTimeInMSInDeltaQueue")
    public var retentionTimeInMSInDeltaQueue: Long? = null,

    @get:JvmName("_deltaRetentionTimerIntervalInMs")
    public var deltaRetentionTimerIntervalInMs: Long? = null,

    @get:JvmName("_evictionIntervalTimerInMs")
    public var evictionIntervalTimerInMs: Long? = null,

    @get:JvmName("_shouldUseAwsAsgApi")
    public var shouldUseAwsAsgApi: Boolean? = null,

    @get:JvmName("_asgQueryTimeoutMs")
    public var asgQueryTimeoutMs: Int? = null,

    @get:JvmName("_asgUpdateIntervalMs")
    public var asgUpdateIntervalMs: Long? = null,

    @get:JvmName("_asgCacheExpiryTimeoutMs")
    public var asgCacheExpiryTimeoutMs: Long? = null,

    @get:JvmName("_responseCacheAutoExpirationInSeconds")
    public var responseCacheAutoExpirationInSeconds: Long? = null,

    @get:JvmName("_responseCacheUpdateIntervalMs")
    public var responseCacheUpdateIntervalMs: Long? = null,

    @get:JvmName("_shouldUseReadOnlyResponseCache")
    public var shouldUseReadOnlyResponseCache: Boolean? = null,

    @get:JvmName("_shouldDisableDelta")
    public var shouldDisableDelta: Boolean? = null,

    @get:JvmName("_maxIdleThreadInMinutesAgeForStatusReplication")
    public var maxIdleThreadInMinutesAgeForStatusReplication: Long? = null,

    @get:JvmName("_minThreadsForStatusReplication")
    public var minThreadsForStatusReplication: Int? = null,

    @get:JvmName("_maxThreadsForStatusReplication")
    public var maxThreadsForStatusReplication: Int? = null,

    @get:JvmName("_maxElementsInStatusReplicationPool")
    public var maxElementsInStatusReplicationPool: Int? = null,

    @get:JvmName("_shouldSyncWhenTimestampDiffers")
    public var shouldSyncWhenTimestampDiffers: Boolean? = null,

    @get:JvmName("_registrySyncRetries")
    public var registrySyncRetries: Int? = null,

    @get:JvmName("_registrySyncRetryWaitMs")
    public var registrySyncRetryWaitMs: Long? = null,

    @get:JvmName("_maxElementsInPeerReplicationPool")
    public var maxElementsInPeerReplicationPool: Int? = null,

    @get:JvmName("_maxIdleThreadAgeInMinutesForPeerReplication")
    public var maxIdleThreadAgeInMinutesForPeerReplication: Long? = null,

    @get:JvmName("_minThreadsForPeerReplication")
    public var minThreadsForPeerReplication: Int? = null,

    @get:JvmName("_maxThreadsForPeerReplication")
    public var maxThreadsForPeerReplication: Int? = null,

    @get:JvmName("_healthStatusMinNumberOfAvailablePeers")
    public var healthStatusMinNumberOfAvailablePeers: Int? = null,

    @get:JvmName("_maxTimeForReplication")
    public var maxTimeForReplication: Int? = null,

    @get:JvmName("_shouldPrimeAwsReplicaConnections")
    public var shouldPrimeAwsReplicaConnections: Boolean? = null,

    @get:JvmName("_shouldDisableDeltaForRemoteRegions")
    public var shouldDisableDeltaForRemoteRegions: Boolean? = null,

    @get:JvmName("_remoteRegionConnectTimeoutMs")
    public var remoteRegionConnectTimeoutMs: Int? = null,

    @get:JvmName("_remoteRegionReadTimeoutMs")
    public var remoteRegionReadTimeoutMs: Int? = null,

    @get:JvmName("_remoteRegionTotalConnections")
    public var remoteRegionTotalConnections: Int? = null,

    @get:JvmName("_remoteRegionTotalConnectionsPerHost")
    public var remoteRegionTotalConnectionsPerHost: Int? = null,

    @get:JvmName("_remoteRegionConnectionIdleTimeoutSeconds")
    public var remoteRegionConnectionIdleTimeoutSeconds: Int? = null,

    @get:JvmName("_shouldGZipContentFromRemoteRegion")
    public var shouldGZipContentFromRemoteRegion: Boolean? = null,

    @get:JvmName("_remoteRegionUrlsWithName")
    public var remoteRegionUrlsWithName: Map<String, String>? = null,

    @get:JvmName("_remoteRegionUrls")
    public var remoteRegionUrls: Array<String>? = null,

    public var remoteRegionAppWhitelist: Map<String, MutableSet<String>>? = null,

    @get:JvmName("_remoteRegionRegistryFetchInterval")
    public var remoteRegionRegistryFetchInterval: Int? = null,

    @get:JvmName("_remoteRegionFetchThreadPoolSize")
    public var remoteRegionFetchThreadPoolSize: Int? = null,

    @get:JvmName("_remoteRegionTrustStore")
    public var remoteRegionTrustStore: String? = null,

    @get:JvmName("_remoteRegionTrustStorePassword")
    public var remoteRegionTrustStorePassword: String? = null,

    @get:JvmName("_disableTransparentFallbackToOtherRegion")
    public var disableTransparentFallbackToOtherRegion: Boolean? = null,

    @get:JvmName("_shouldBatchReplication")
    public var shouldBatchReplication: Boolean? = null,

    @get:JvmName("_myUrl")
    public var myUrl: String? = null,

    @get:JvmName("_shouldLogIdentityHeaders")
    public var shouldLogIdentityHeaders: Boolean? = null,

    @get:JvmName("_isRateLimiterEnabled")
    public var isRateLimiterEnabled: Boolean? = null,

    @get:JvmName("_isRateLimiterThrottleStandardClients")
    public var isRateLimiterThrottleStandardClients: Boolean? = null,

    @get:JvmName("_rateLimiterPrivilegedClients")
    public var rateLimiterPrivilegedClients: Set<String>? = null,

    @get:JvmName("_rateLimiterBurstSize")
    public var rateLimiterBurstSize: Int? = null,

    @get:JvmName("_rateLimiterRegistryFetchAverageRate")
    public var rateLimiterRegistryFetchAverageRate: Int? = null,

    @get:JvmName("_rateLimiterFullFetchAverageRate")
    public var rateLimiterFullFetchAverageRate: Int? = null,

    @get:JvmName("_listAutoScalingGroupsRoleName")
    public var listAutoScalingGroupsRoleName: String? = null,

    @get:JvmName("_jsonCodecName")
    public var jsonCodecName: String? = null,

    @get:JvmName("_xmlCodecName")
    public var xmlCodecName: String? = null,

    @get:JvmName("_bindingStrategy")
    public var bindingStrategy: AwsBindingStrategy? = null,

    @get:JvmName("_asgCacheExpiryTimeout")
    public var asgCacheExpiryTimeout: Long? = null,

    @get:JvmName("_isAws")
    public var isAws: Boolean? = null,

    @get:JvmName("_awsBindingStrategy")
    public var awsBindingStrategy: AwsBindingStrategy? = null,

    @get:JvmName("_route53DomainTtl")
    public var route53DomainTtl: Int? = null,

    @get:JvmName("_route53BindRebindRetries")
    public var route53BindRebindRetries: String? = null,

    @get:JvmName("_route53BindingRetryIntervalMs")
    public var route53BindingRetryIntervalMs: String? = null,

    @get:JvmName("_route53BindingRetryIntervalMsWhenUnbound")
    public var route53BindingRetryIntervalMsWhenUnbound: String? = null,

    @get:JvmName("_experimental")
    public var experimental: Map<String, String>? = null,

    @get:JvmName("_initialCapacityOfResponseCache")
    public var initialCapacityOfResponseCache: Int? = null

) : EurekaServerConfig {
    private val delegate = DefaultEurekaServerConfig(namespace)
    override fun getAWSAccessId(): String = awsAccessId ?: delegate.awsAccessId

    override fun getAWSSecretKey(): String = awsSecretKey ?: delegate.awsSecretKey

    override fun getEIPBindRebindRetries(): Int = eipBindRebindRetries ?: delegate.eipBindRebindRetries

    override fun getEIPBindingRetryIntervalMsWhenUnbound(): Int =
        eipBindingRetryIntervalMsWhenUnbound ?: delegate.eipBindingRetryIntervalMsWhenUnbound

    override fun getEIPBindingRetryIntervalMs(): Int = eipBindingRetryIntervalMs ?: delegate.eipBindingRetryIntervalMs

    override fun shouldEnableSelfPreservation(): Boolean =
        shouldEnableSelfPreservation ?: delegate.shouldEnableSelfPreservation()

    override fun getRenewalPercentThreshold(): Double = renewalPercentThreshold ?: delegate.renewalPercentThreshold

    override fun getRenewalThresholdUpdateIntervalMs(): Int =
        renewalThresholdUpdateIntervalMs ?: delegate.renewalThresholdUpdateIntervalMs

    override fun getExpectedClientRenewalIntervalSeconds(): Int =
        expectedClientRenewalIntervalSeconds ?: delegate.expectedClientRenewalIntervalSeconds

    override fun getPeerEurekaNodesUpdateIntervalMs(): Int =
        peerEurekaNodesUpdateIntervalMs ?: delegate.peerEurekaNodesUpdateIntervalMs

    override fun shouldEnableReplicatedRequestCompression(): Boolean =
        shouldEnableReplicatedRequestCompression ?: delegate.shouldEnableReplicatedRequestCompression()

    override fun getNumberOfReplicationRetries(): Int =
        numberOfReplicationRetries ?: delegate.numberOfReplicationRetries

    override fun getPeerEurekaStatusRefreshTimeIntervalMs(): Int =
        peerEurekaStatusRefreshTimeIntervalMs ?: delegate.peerEurekaStatusRefreshTimeIntervalMs

    override fun getWaitTimeInMsWhenSyncEmpty(): Int = waitTimeInMsWhenSyncEmpty ?: delegate.waitTimeInMsWhenSyncEmpty

    override fun getPeerNodeConnectTimeoutMs(): Int = peerNodeConnectTimeoutMs ?: delegate.peerNodeConnectTimeoutMs

    override fun getPeerNodeReadTimeoutMs(): Int = peerNodeReadTimeoutMs ?: delegate.peerNodeReadTimeoutMs

    override fun getPeerNodeTotalConnections(): Int = peerNodeTotalConnections ?: delegate.peerNodeTotalConnections

    override fun getPeerNodeTotalConnectionsPerHost(): Int =
        peerNodeTotalConnectionsPerHost ?: delegate.peerNodeTotalConnectionsPerHost

    override fun getPeerNodeConnectionIdleTimeoutSeconds(): Int =
        peerNodeConnectionIdleTimeoutSeconds ?: delegate.peerNodeConnectionIdleTimeoutSeconds

    override fun getRetentionTimeInMSInDeltaQueue(): Long =
        retentionTimeInMSInDeltaQueue ?: delegate.retentionTimeInMSInDeltaQueue

    override fun getDeltaRetentionTimerIntervalInMs(): Long =
        deltaRetentionTimerIntervalInMs ?: delegate.deltaRetentionTimerIntervalInMs

    override fun getEvictionIntervalTimerInMs(): Long = evictionIntervalTimerInMs ?: delegate.evictionIntervalTimerInMs

    override fun shouldUseAwsAsgApi(): Boolean = shouldUseAwsAsgApi ?: delegate.shouldUseAwsAsgApi()

    override fun getASGQueryTimeoutMs(): Int = asgQueryTimeoutMs ?: delegate.asgQueryTimeoutMs

    override fun getASGUpdateIntervalMs(): Long = asgUpdateIntervalMs ?: delegate.asgUpdateIntervalMs

    override fun getASGCacheExpiryTimeoutMs(): Long = asgCacheExpiryTimeoutMs ?: delegate.asgCacheExpiryTimeoutMs

    override fun getResponseCacheAutoExpirationInSeconds(): Long =
        responseCacheAutoExpirationInSeconds ?: delegate.responseCacheAutoExpirationInSeconds

    override fun getResponseCacheUpdateIntervalMs(): Long =
        responseCacheUpdateIntervalMs ?: delegate.responseCacheUpdateIntervalMs

    override fun shouldUseReadOnlyResponseCache(): Boolean =
        shouldUseReadOnlyResponseCache ?: delegate.shouldUseReadOnlyResponseCache()

    override fun shouldDisableDelta(): Boolean = shouldDisableDelta ?: delegate.shouldDisableDelta()

    override fun getMaxIdleThreadInMinutesAgeForStatusReplication(): Long =
        maxIdleThreadInMinutesAgeForStatusReplication ?: delegate.maxIdleThreadInMinutesAgeForStatusReplication

    override fun getMinThreadsForStatusReplication(): Int =
        minThreadsForStatusReplication ?: delegate.minThreadsForStatusReplication

    override fun getMaxThreadsForStatusReplication(): Int =
        maxThreadsForStatusReplication ?: delegate.maxThreadsForStatusReplication

    override fun getMaxElementsInStatusReplicationPool(): Int =
        maxElementsInStatusReplicationPool ?: delegate.maxElementsInStatusReplicationPool

    override fun shouldSyncWhenTimestampDiffers(): Boolean =
        shouldSyncWhenTimestampDiffers ?: delegate.shouldSyncWhenTimestampDiffers()

    override fun getRegistrySyncRetries(): Int = registrySyncRetries ?: delegate.registrySyncRetries

    override fun getRegistrySyncRetryWaitMs(): Long = registrySyncRetryWaitMs ?: delegate.registrySyncRetryWaitMs

    override fun getMaxElementsInPeerReplicationPool(): Int =
        maxElementsInPeerReplicationPool ?: delegate.maxElementsInPeerReplicationPool

    override fun getMaxIdleThreadAgeInMinutesForPeerReplication(): Long =
        maxIdleThreadAgeInMinutesForPeerReplication ?: delegate.maxIdleThreadAgeInMinutesForPeerReplication

    override fun getMinThreadsForPeerReplication(): Int =
        minThreadsForPeerReplication ?: delegate.minThreadsForPeerReplication

    override fun getMaxThreadsForPeerReplication(): Int =
        maxThreadsForPeerReplication ?: delegate.maxThreadsForPeerReplication

    override fun getHealthStatusMinNumberOfAvailablePeers(): Int =
        healthStatusMinNumberOfAvailablePeers ?: delegate.healthStatusMinNumberOfAvailablePeers

    override fun getMaxTimeForReplication(): Int = maxTimeForReplication ?: delegate.maxTimeForReplication

    override fun shouldPrimeAwsReplicaConnections(): Boolean =
        shouldPrimeAwsReplicaConnections ?: delegate.shouldPrimeAwsReplicaConnections()

    override fun shouldDisableDeltaForRemoteRegions(): Boolean =
        shouldDisableDeltaForRemoteRegions ?: delegate.shouldDisableDeltaForRemoteRegions()

    override fun getRemoteRegionConnectTimeoutMs(): Int =
        remoteRegionConnectTimeoutMs ?: delegate.remoteRegionConnectTimeoutMs

    override fun getRemoteRegionReadTimeoutMs(): Int = remoteRegionReadTimeoutMs ?: delegate.remoteRegionReadTimeoutMs

    override fun getRemoteRegionTotalConnections(): Int =
        remoteRegionTotalConnections ?: delegate.remoteRegionTotalConnections

    override fun getRemoteRegionTotalConnectionsPerHost(): Int =
        remoteRegionTotalConnectionsPerHost ?: delegate.remoteRegionTotalConnectionsPerHost

    override fun getRemoteRegionConnectionIdleTimeoutSeconds(): Int =
        remoteRegionConnectionIdleTimeoutSeconds ?: delegate.remoteRegionConnectionIdleTimeoutSeconds

    override fun shouldGZipContentFromRemoteRegion(): Boolean = delegate.shouldGZipContentFromRemoteRegion()

    override fun getRemoteRegionUrlsWithName(): Map<String, String> =
        remoteRegionUrlsWithName ?: delegate.remoteRegionUrlsWithName

    override fun getRemoteRegionUrls(): Array<String> = remoteRegionUrls ?: delegate.remoteRegionUrls

    override fun getRemoteRegionAppWhitelist(regionName: String?): MutableSet<String>? =
        remoteRegionAppWhitelist?.get(regionName) ?: delegate.getRemoteRegionAppWhitelist(regionName)

    override fun getRemoteRegionRegistryFetchInterval(): Int =
        remoteRegionRegistryFetchInterval ?: delegate.remoteRegionRegistryFetchInterval

    override fun getRemoteRegionFetchThreadPoolSize(): Int =
        remoteRegionFetchThreadPoolSize ?: delegate.remoteRegionFetchThreadPoolSize

    override fun getRemoteRegionTrustStore(): String = remoteRegionTrustStore ?: delegate.remoteRegionTrustStore

    override fun getRemoteRegionTrustStorePassword(): String =
        remoteRegionTrustStorePassword ?: delegate.remoteRegionTrustStorePassword

    override fun disableTransparentFallbackToOtherRegion(): Boolean =
        disableTransparentFallbackToOtherRegion ?: delegate.disableTransparentFallbackToOtherRegion()

    override fun shouldBatchReplication(): Boolean = shouldBatchReplication ?: delegate.shouldBatchReplication()

    override fun getMyUrl(): String = myUrl ?: delegate.myUrl

    override fun shouldLogIdentityHeaders(): Boolean = shouldLogIdentityHeaders ?: delegate.shouldLogIdentityHeaders()

    override fun isRateLimiterEnabled(): Boolean = isRateLimiterEnabled ?: delegate.isRateLimiterEnabled

    override fun isRateLimiterThrottleStandardClients(): Boolean =
        isRateLimiterThrottleStandardClients ?: delegate.isRateLimiterThrottleStandardClients

    override fun getRateLimiterPrivilegedClients(): Set<String> =
        rateLimiterPrivilegedClients ?: delegate.rateLimiterPrivilegedClients

    override fun getRateLimiterBurstSize(): Int = rateLimiterBurstSize ?: delegate.rateLimiterBurstSize

    override fun getRateLimiterRegistryFetchAverageRate(): Int =
        rateLimiterRegistryFetchAverageRate ?: delegate.rateLimiterRegistryFetchAverageRate

    override fun getRateLimiterFullFetchAverageRate(): Int =
        rateLimiterFullFetchAverageRate ?: delegate.rateLimiterFullFetchAverageRate

    override fun getListAutoScalingGroupsRoleName(): String =
        listAutoScalingGroupsRoleName ?: delegate.listAutoScalingGroupsRoleName

    override fun getJsonCodecName(): String = jsonCodecName ?: delegate.jsonCodecName

    override fun getXmlCodecName(): String = xmlCodecName ?: delegate.xmlCodecName

    override fun getBindingStrategy(): AwsBindingStrategy = bindingStrategy ?: delegate.bindingStrategy

    override fun getRoute53DomainTTL(): Long = route53DomainTtl?.toLong() ?: delegate.route53DomainTTL

    override fun getRoute53BindRebindRetries(): Int =
        route53BindRebindRetries?.toInt() ?: delegate.route53BindRebindRetries

    override fun getRoute53BindingRetryIntervalMs(): Int =
        route53BindingRetryIntervalMs?.toInt() ?: delegate.route53BindingRetryIntervalMs

    override fun getExperimental(name: String?): String = experimental?.get(name) ?: delegate.getExperimental(name)

    override fun getInitialCapacityOfResponseCache(): Int =
        initialCapacityOfResponseCache ?: delegate.initialCapacityOfResponseCache

    internal companion object {
        internal fun ApplicationConfig.readEurekaServerConfiguration(namespace: String): EurekaServerConfiguration =
            with(config(namespace)) {
                EurekaServerConfiguration(
                    namespace = property("namespace").getString(),
                    awsAccessId = propertyOrNull("awsAccessId")?.getString(),
                    awsSecretKey = propertyOrNull("awsSecretKey")?.getString(),
                    eipBindRebindRetries = propertyOrNull("eipBindRebindRetries")?.getString()?.toIntOrNull(),
                    eipBindingRetryIntervalMsWhenUnbound = propertyOrNull("eipBindingRetryIntervalMsWhenUnbound")?.getString()
                        ?.toIntOrNull(),
                    eipBindingRetryIntervalMs = propertyOrNull("eipBindingRetryIntervalMs")?.getString()?.toIntOrNull(),
                    shouldEnableSelfPreservation = propertyOrNull("shouldEnableSelfPreservation")?.getString()
                        ?.toBoolean(),
                    renewalPercentThreshold = propertyOrNull("renewalPercentThreshold")?.getString()?.toDoubleOrNull(),
                    renewalThresholdUpdateIntervalMs = propertyOrNull("renewalThresholdUpdateIntervalMs")?.getString()
                        ?.toIntOrNull(),
                    expectedClientRenewalIntervalSeconds = propertyOrNull("expectedClientRenewalIntervalSeconds")?.getString()
                        ?.toIntOrNull(),
                    peerEurekaNodesUpdateIntervalMs = propertyOrNull("peerEurekaNodesUpdateIntervalMs")?.getString()
                        ?.toIntOrNull(),
                    shouldEnableReplicatedRequestCompression = propertyOrNull("shouldEnableReplicatedRequestCompression")?.getString()
                        ?.toBoolean(),
                    numberOfReplicationRetries = propertyOrNull("numberOfReplicationRetries")?.getString()
                        ?.toIntOrNull(),
                    peerEurekaStatusRefreshTimeIntervalMs = propertyOrNull("peerEurekaStatusRefreshTimeIntervalMs")?.getString()
                        ?.toIntOrNull(),
                    waitTimeInMsWhenSyncEmpty = propertyOrNull("waitTimeInMsWhenSyncEmpty")?.getString()?.toIntOrNull(),
                    peerNodeConnectTimeoutMs = propertyOrNull("peerNodeConnectTimeoutMs")?.getString()?.toIntOrNull(),
                    peerNodeReadTimeoutMs = propertyOrNull("peerNodeReadTimeoutMs")?.getString()?.toIntOrNull(),
                    peerNodeTotalConnections = propertyOrNull("peerNodeTotalConnections")?.getString()?.toIntOrNull(),
                    peerNodeTotalConnectionsPerHost = propertyOrNull("peerNodeTotalConnectionsPerHost")?.getString()
                        ?.toIntOrNull(),
                    peerNodeConnectionIdleTimeoutSeconds = propertyOrNull("peerNodeConnectionIdleTimeoutSeconds")?.getString()
                        ?.toIntOrNull(),
                    retentionTimeInMSInDeltaQueue = propertyOrNull("retentionTimeInMSInDeltaQueue")?.getString()
                        ?.toLongOrNull(),
                    deltaRetentionTimerIntervalInMs = propertyOrNull("deltaRetentionTimerIntervalInMs")?.getString()
                        ?.toLongOrNull(),
                    evictionIntervalTimerInMs = propertyOrNull("evictionIntervalTimerInMs")?.getString()
                        ?.toLongOrNull(),
                    shouldUseAwsAsgApi = propertyOrNull("shouldUseAwsAsgApi")?.getString()?.toBoolean(),
                    asgQueryTimeoutMs = propertyOrNull("asgQueryTimeoutMs")?.getString()?.toIntOrNull(),
                    asgUpdateIntervalMs = propertyOrNull("asgUpdateIntervalMs")?.getString()?.toLongOrNull(),
                    responseCacheAutoExpirationInSeconds = propertyOrNull("responseCacheAutoExpirationInSeconds")?.getString()
                        ?.toLongOrNull(),
                    responseCacheUpdateIntervalMs = propertyOrNull("responseCacheUpdateIntervalMs")?.getString()
                        ?.toLongOrNull(),
                    shouldUseReadOnlyResponseCache = propertyOrNull("shouldUseReadOnlyResponseCache")?.getString()
                        ?.toBoolean(),
                    shouldDisableDelta = propertyOrNull("shouldDisableDelta")?.getString()?.toBoolean(),
                    maxIdleThreadInMinutesAgeForStatusReplication = propertyOrNull("maxIdleThreadInMinutesAgeForStatusReplication")?.getString()
                        ?.toLongOrNull(),
                    minThreadsForStatusReplication = propertyOrNull("minThreadsForStatusReplication")?.getString()
                        ?.toIntOrNull(),
                    maxThreadsForStatusReplication = propertyOrNull("maxThreadsForStatusReplication")?.getString()
                        ?.toIntOrNull(),
                    maxElementsInStatusReplicationPool = propertyOrNull("maxElementsInStatusReplicationPool")?.getString()
                        ?.toIntOrNull(),
                    shouldSyncWhenTimestampDiffers = propertyOrNull("shouldSyncWhenTimestampDiffers")?.getString()
                        ?.toBoolean(),
                    registrySyncRetries = propertyOrNull("registrySyncRetries")?.getString()?.toIntOrNull(),
                    registrySyncRetryWaitMs = propertyOrNull("registrySyncRetryWaitMs")?.getString()?.toLongOrNull(),
                    maxElementsInPeerReplicationPool = propertyOrNull("maxElementsInPeerReplicationPool")?.getString()
                        ?.toIntOrNull(),
                    maxIdleThreadAgeInMinutesForPeerReplication = propertyOrNull("maxIdleThreadAgeInMinutesForPeerReplication")?.getString()
                        ?.toLongOrNull(),
                    minThreadsForPeerReplication = propertyOrNull("minThreadsForPeerReplication")?.getString()
                        ?.toIntOrNull(),
                    maxThreadsForPeerReplication = propertyOrNull("maxThreadsForPeerReplication")?.getString()
                        ?.toIntOrNull(),
                    healthStatusMinNumberOfAvailablePeers = propertyOrNull("healthStatusMinNumberOfAvailablePeers")?.getString()
                        ?.toIntOrNull(),
                    maxTimeForReplication = propertyOrNull("maxTimeForReplication")?.getString()?.toIntOrNull(),
                    shouldPrimeAwsReplicaConnections = propertyOrNull("shouldPrimeAwsReplicaConnections")?.getString()
                        ?.toBoolean(),
                    shouldDisableDeltaForRemoteRegions = propertyOrNull("shouldDisableDeltaForRemoteRegions")?.getString()
                        ?.toBoolean(),
                    remoteRegionConnectTimeoutMs = propertyOrNull("remoteRegionConnectTimeoutMs")?.getString()
                        ?.toIntOrNull(),
                    remoteRegionReadTimeoutMs = propertyOrNull("remoteRegionReadTimeoutMs")?.getString()?.toIntOrNull(),
                    remoteRegionTotalConnections = propertyOrNull("remoteRegionTotalConnections")?.getString()
                        ?.toIntOrNull(),
                    remoteRegionTotalConnectionsPerHost = propertyOrNull("remoteRegionTotalConnectionsPerHost")?.getString()
                        ?.toIntOrNull(),
                    remoteRegionConnectionIdleTimeoutSeconds = propertyOrNull("remoteRegionConnectionIdleTimeoutSeconds")?.getString()
                        ?.toIntOrNull(),
                    shouldGZipContentFromRemoteRegion = propertyOrNull("shouldGZipContentFromRemoteRegion")?.getString()
                        ?.toBoolean(),
                    remoteRegionUrlsWithName = configOrNull("remoteRegionUrlsWithName")?.toMap()
                        ?.mapValues { it.toString() },
                    remoteRegionUrls = propertyOrNull("remoteRegionUrls")?.getList()?.toTypedArray(),
                    remoteRegionRegistryFetchInterval = propertyOrNull("remoteRegionRegistryFetchInterval")?.getString()
                        ?.toIntOrNull(),
                    remoteRegionFetchThreadPoolSize = propertyOrNull("remoteRegionFetchThreadPoolSize")?.getString()
                        ?.toIntOrNull(),
                    remoteRegionTrustStore = propertyOrNull("remoteRegionTrustStore")?.getString(),
                    remoteRegionTrustStorePassword = propertyOrNull("remoteRegionTrustStorePassword")?.getString(),
                    disableTransparentFallbackToOtherRegion = propertyOrNull("disableTransparentFallbackToOtherRegion")?.getString()
                        ?.toBoolean(),
                    shouldBatchReplication = propertyOrNull("shouldBatchReplication")?.getString()?.toBoolean(),
                    myUrl = propertyOrNull("myUrl")?.getString(),
                    shouldLogIdentityHeaders = propertyOrNull("shouldLogIdentityHeaders")?.getString()?.toBoolean(),
                    isRateLimiterEnabled = propertyOrNull("isRateLimiterEnabled")?.getString()?.toBoolean(),
                    isRateLimiterThrottleStandardClients = propertyOrNull("isRateLimiterThrottleStandardClients")?.getString()
                        ?.toBoolean(),
                    rateLimiterPrivilegedClients = propertyOrNull("rateLimiterPrivilegedClients")?.getList()?.toSet(),
                    rateLimiterBurstSize = propertyOrNull("rateLimiterBurstSize")?.getString()?.toIntOrNull(),
                    rateLimiterRegistryFetchAverageRate = propertyOrNull("rateLimiterRegistryFetchAverageRate")?.getString()
                        ?.toIntOrNull(),
                    rateLimiterFullFetchAverageRate = propertyOrNull("rateLimiterFullFetchAverageRate")?.getString()
                        ?.toIntOrNull(),
                    listAutoScalingGroupsRoleName = propertyOrNull("listAutoScalingGroupsRoleName")?.getString(),
                    jsonCodecName = propertyOrNull("jsonCodecName")?.getString(),
                    xmlCodecName = propertyOrNull("xmlCodecName")?.getString(),
                    bindingStrategy = propertyOrNull("bindingStrategy")?.getString()
                        ?.let { AwsBindingStrategy.valueOf(it) },
                    asgCacheExpiryTimeoutMs = propertyOrNull("asgCacheExpiryTimeoutMs")?.getString()?.toLongOrNull(),
                    isAws = propertyOrNull("isAws")?.getString()?.toBoolean(),
                    awsBindingStrategy = propertyOrNull("awsBindingStrategy")?.getString()
                        ?.let { AwsBindingStrategy.valueOf(it) },
                    route53DomainTtl = propertyOrNull("route53DomainTtl")?.getString()?.toIntOrNull(),
                    route53BindRebindRetries = propertyOrNull("route53BindRebindRetries")?.getString(),
                    route53BindingRetryIntervalMs = propertyOrNull("route53BindingRetryIntervalMs")?.getString(),
                    route53BindingRetryIntervalMsWhenUnbound = propertyOrNull("route53BindingRetryIntervalMsWhenUnbound")?.getString(),
                    experimental = configOrNull("experimental")?.toMap()?.mapValues { it.toString() },
                    initialCapacityOfResponseCache = propertyOrNull("initialCapacityOfResponseCache")?.getString()
                        ?.toIntOrNull()
                )
            }
    }
}