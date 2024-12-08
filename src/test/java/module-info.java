module test.brinvex.util.java {
    requires brinvex.util.java;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.mockito;
    requires net.bytebuddy;
    requires net.bytebuddy.agent;
    opens test.com.brinvex.util.java to org.junit.platform.commons,org.mockito;
}