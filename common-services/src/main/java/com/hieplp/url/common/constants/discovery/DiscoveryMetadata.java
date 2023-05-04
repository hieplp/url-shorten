package com.hieplp.url.common.constants.discovery;

public enum DiscoveryMetadata {
    NAME("api.name");

    private final String name;

    DiscoveryMetadata(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
