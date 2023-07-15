package com.brinvex.util.java;

import java.net.URI;
import java.net.URISyntaxException;

public class UriUtil {

    /**
     * https://stackoverflow.com/a/9608008/1157213
     */
    public static String extractDomainName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            if (domain.startsWith("www.")) {
                return domain.substring(4);
            }
            if (domain.startsWith("www3.")) {
                return domain.substring(5);
            }
            return domain;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractSchemeAndHost(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            String scheme = uri.getScheme();
            return scheme + "://" + host;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
