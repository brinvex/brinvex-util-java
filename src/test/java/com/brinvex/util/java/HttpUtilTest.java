package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpUtilTest {

    @SuppressWarnings("HttpUrlsUsage")
    @Test
    void extractDomainName() {
        assertEquals(HttpUtil.extractDomainName("http://example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("https://example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://subpart.example.com/path/"), "subpart.example.com");
        assertEquals(HttpUtil.extractDomainName("http://example.com"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://example.com:18445/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("example.com"), "example.com");

        assertEquals(HttpUtil.extractDomainName("http://Example.com/path/"), "Example.com");

        assertEquals(HttpUtil.extractDomainName("http://www.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://WWW.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://WwW.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("https://www.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://www.subpart.example.com/path/"), "subpart.example.com");
        assertEquals(HttpUtil.extractDomainName("http://www.example.com"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://www.example.com:18445/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("www.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("www.example.com"), "example.com");

        assertEquals(HttpUtil.extractDomainName("http://www2.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("https://www3.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://www4.subpart.example.com/path/"), "subpart.example.com");
        assertEquals(HttpUtil.extractDomainName("http://www5.example.com"), "example.com");
        assertEquals(HttpUtil.extractDomainName("http://www66.example.com:18445/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("wwwABC.example.com/path/"), "example.com");
        assertEquals(HttpUtil.extractDomainName("www123.example.com"), "example.com");
    }
}
