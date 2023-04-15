package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {

    @Test
    public void generateWords() {
        assertEquals(
                List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"),
                StringUtil.generateWords(StringUtil.Constants.UP_LETTER_CHARS));

        assertEquals(
                List.of("A1", "A2", "B1", "B2", "C1", "C2"),
                StringUtil.generateWords("ABC".toCharArray(), "12".toCharArray()));

        assertEquals(
                List.of("AX1", "AX2", "BX1", "BX2", "CX1", "CX2"),
                StringUtil.generateWords("ABC".toCharArray(), "X".toCharArray(), "12".toCharArray()));
    }
}
