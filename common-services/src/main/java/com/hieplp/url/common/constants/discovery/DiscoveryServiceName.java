package com.hieplp.url.common.constants.discovery;

public enum DiscoveryServiceName {
    AUTH("auth"),
    ;

    private final String name;

    DiscoveryServiceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
