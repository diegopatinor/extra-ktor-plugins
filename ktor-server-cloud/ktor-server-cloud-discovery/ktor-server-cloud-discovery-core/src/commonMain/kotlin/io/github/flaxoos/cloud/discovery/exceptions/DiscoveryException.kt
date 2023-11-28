package io.github.flaxoos.cloud.discovery.exceptions

public sealed class DiscoveryException :Exception()
public data object ApplicationNotUpException : DiscoveryException()