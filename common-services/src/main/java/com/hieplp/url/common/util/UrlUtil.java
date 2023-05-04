package com.hieplp.url.common.util;

import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtil {
    public String getPath(String url) {
        try {
            URI uri = new URI(url);
            return uri.getPath();
        } catch (URISyntaxException e) {
            return "";
        }
    }
}
