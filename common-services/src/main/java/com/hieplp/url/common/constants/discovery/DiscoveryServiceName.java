package com.hieplp.url.common.constants.discovery;

import com.hieplp.url.common.exception.BadRequestException;

public enum DiscoveryServiceName {
    AUTH("auth", "/auth"),
    USER("user", "/user"),
    USER_URL("user-url", "/user/url"),
    PUBLIC_URL("user-url", "/public/url");

    private final String name;
    private final String prefix;

    DiscoveryServiceName(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public static DiscoveryServiceName getByPrefix(String path) {
        for (DiscoveryServiceName service : DiscoveryServiceName.values()) {
            if (path.startsWith(service.getPrefix())) {
                return service;
            }
        }
        throw new BadRequestException("Service name not found");
    }
}
