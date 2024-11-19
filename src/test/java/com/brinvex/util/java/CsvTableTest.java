package com.brinvex.util.java;

import com.brinvex.util.java.csv.CsvConfig;
import com.brinvex.util.java.csv.CsvTable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTableTest {

    @Test
    void csvTable1() {
        CsvConfig csvConfig = CsvConfig.builder().quoteCharacter(null).quoteEscapeCharacter(null).build();
        CsvTable table = CsvTable.of("""
                h0,h1,h2
                v00,v01,v02
                v10,v11,v12
                """
                .lines().toList(), csvConfig
        );
        assertEquals(List.of("h0", "h1", "h2"), table.getHeaders());
        assertEquals("v00", table.getValue(0, "h0"));
        assertEquals("v01", table.getValue(0, "h1"));
        assertEquals("v02", table.getValue(0, "h2"));

        assertEquals("v10", table.getValue(1, "h0"));
        assertEquals("v11", table.getValue(1, "h1"));
        assertEquals("v12", table.getValue(1, "h2"));

        List<String> csvLines = table.toCsvLines(csvConfig);
        assertEquals("""
                h0,h1,h2
                v00,v01,v02
                v10,v11,v12
                """
                .lines().toList(), csvLines);
    }

    @Test
    void csvTable2() {
        CsvConfig csvConfig = CsvConfig.builder().build();
        CsvTable table = CsvTable.of("""
                "h0","h1","h2"
                "v00","v01","v02"
                "v10","v11","v12"
                """
                .lines().toList(), csvConfig
        );
        assertEquals(List.of("h0", "h1", "h2"), table.getHeaders());
        assertEquals("v00", table.getValue(0, "h0"));
        assertEquals("v01", table.getValue(0, "h1"));
        assertEquals("v02", table.getValue(0, "h2"));

        assertEquals("v10", table.getValue(1, "h0"));
        assertEquals("v11", table.getValue(1, "h1"));
        assertEquals("v12", table.getValue(1, "h2"));

        List<String> csvLines = table.toCsvLines(csvConfig);
        assertEquals("""
                "h0","h1","h2"
                "v00","v01","v02"
                "v10","v11","v12"
                """
                .lines().toList(), csvLines);
    }

    @Test
    void csvTable3() {
        CsvConfig csvConfig = CsvConfig.builder().build();
        CsvTable table = CsvTable.of("""
                h0,h1,"h2"
                "v00",v01, "v02"
                "v10",v11,"v12"
                """
                .lines().toList(), csvConfig
        );
        assertEquals(List.of("h0", "h1", "h2"), table.getHeaders());
        assertEquals("v00", table.getValue(0, "h0"));
        assertEquals("v01", table.getValue(0, "h1"));
        assertEquals("v02", table.getValue(0, "h2"));

        assertEquals("v10", table.getValue(1, "h0"));
        assertEquals("v11", table.getValue(1, "h1"));
        assertEquals("v12", table.getValue(1, "h2"));

        List<String> csvLines = table.toCsvLines(csvConfig);
        assertEquals("""
                "h0","h1","h2"
                "v00","v01","v02"
                "v10","v11","v12"
                """
                .lines().toList(), csvLines);

    }
}
