package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("HttpUrlsUsage")
public class UriUtilTest {

    @Test
    void extractDomainName() {
        assertEquals("example.com", UriUtil.extractDomainName("http://example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("https://example.com/path/"));
        assertEquals("subpart.example.com", UriUtil.extractDomainName("http://subpart.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("http://example.com"));
        assertEquals("example.com", UriUtil.extractDomainName("http://example.com:18445/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("example.com"));

        assertEquals("Example.com", UriUtil.extractDomainName("http://Example.com/path/"));

        assertEquals("example.com", UriUtil.extractDomainName("http://www.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("http://WWW.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("http://WwW.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("https://www.example.com/path/"));
        assertEquals("subpart.example.com", UriUtil.extractDomainName("http://www.subpart.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("http://www.example.com"));
        assertEquals("example.com", UriUtil.extractDomainName("http://www.example.com:18445/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("www.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("www.example.com"));

        assertEquals("example.com", UriUtil.extractDomainName("http://www2.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("https://www3.example.com/path/"));
        assertEquals("subpart.example.com", UriUtil.extractDomainName("http://www4.subpart.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("http://www5.example.com"));
        assertEquals("example.com", UriUtil.extractDomainName("http://www66.example.com:18445/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("wwwABC.example.com/path/"));
        assertEquals("example.com", UriUtil.extractDomainName("www123.example.com"));
    }

    @Test
    void extractSchemaAndHost() {
        assertEquals("http://example.com", UriUtil.extractSchemeAndHost("http://example.com/path/"));
        assertEquals("https://example.com", UriUtil.extractSchemeAndHost("https://example.com/path/"));
        assertEquals("http://subpart.example.com", UriUtil.extractSchemeAndHost("http://subpart.example.com/path/"));
        assertEquals("http://example.com", UriUtil.extractSchemeAndHost("http://example.com"));
        assertEquals("http://example.com", UriUtil.extractSchemeAndHost("http://example.com:18445/path/"));
        assertEquals("example.com", UriUtil.extractSchemeAndHost("example.com/path/"));
        assertEquals("example.com", UriUtil.extractSchemeAndHost("example.com"));

        assertEquals("http://Example.com", UriUtil.extractSchemeAndHost("http://Example.com/path/"));

        assertEquals("http://www.example.com", UriUtil.extractSchemeAndHost("http://www.example.com/path/"));
        assertEquals("http://WWW.example.com", UriUtil.extractSchemeAndHost("http://WWW.example.com/path/"));
        assertEquals("http://WwW.example.com", UriUtil.extractSchemeAndHost("http://WwW.example.com/path/"));
        assertEquals("https://www.example.com", UriUtil.extractSchemeAndHost("https://www.example.com/path/"));
        assertEquals("http://www.subpart.example.com", UriUtil.extractSchemeAndHost("http://www.subpart.example.com/path/"));
        assertEquals("http://www.example.com", UriUtil.extractSchemeAndHost("http://www.example.com"));
        assertEquals("http://www.example.com", UriUtil.extractSchemeAndHost("http://www.example.com:18445/path/"));
        assertEquals("www.example.com", UriUtil.extractSchemeAndHost("www.example.com/path/"));
        assertEquals("www.example.com", UriUtil.extractSchemeAndHost("www.example.com"));

        assertEquals("http://www2.example.com", UriUtil.extractSchemeAndHost("http://www2.example.com/path/"));
        assertEquals("https://www3.example.com", UriUtil.extractSchemeAndHost("https://www3.example.com/path/"));
        assertEquals("http://www4.subpart.example.com", UriUtil.extractSchemeAndHost("http://www4.subpart.example.com/path/"));
        assertEquals("https://www5.example.com", UriUtil.extractSchemeAndHost("https://www5.example.com"));
        assertEquals("https://www66.example.com", UriUtil.extractSchemeAndHost("https://www66.example.com:18445/path/"));
        assertEquals("wwwABC.example.com", UriUtil.extractSchemeAndHost("wwwABC.example.com/path/"));
        assertEquals("www123.example.com", UriUtil.extractSchemeAndHost("www123.example.com"));
    }
}
