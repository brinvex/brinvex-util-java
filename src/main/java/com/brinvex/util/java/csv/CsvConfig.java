package com.brinvex.util.java.csv;

public final class CsvConfig {
    private final char separator;
    private final Character quoteCharacter;
    private final Character quoteEscapeCharacter;

    private CsvConfig(char separator, Character quoteCharacter, Character quoteEscapeCharacter) {
        if (quoteCharacter == null) {
            if (quoteEscapeCharacter != null) {
                throw new IllegalArgumentException("quoteEscapeCharacter must be null, if quoteCharacter is null; given: %s".formatted(quoteEscapeCharacter));
            }
        }
        this.separator = separator;
        this.quoteCharacter = quoteCharacter;
        this.quoteEscapeCharacter = quoteEscapeCharacter;
    }

    public static CsvConfigBuilder builder() {
        return new CsvConfigBuilder();
    }

    public char separator() {
        return separator;
    }

    public Character quoteCharacter() {
        return quoteCharacter;
    }

    public Character quoteEscapeCharacter() {
        return quoteEscapeCharacter;
    }

    public static class CsvConfigBuilder {
        private char separator = ',';
        private Character quoteCharacter = '"';
        private Character quoteEscapeCharacter = '\\';

        private CsvConfigBuilder() {
        }

        public CsvConfigBuilder quoteCharacter(Character quoteCharacter) {
            this.quoteCharacter = quoteCharacter;
            return this;
        }

        public CsvConfigBuilder separator(char separator) {
            this.separator = separator;
            return this;
        }

        public CsvConfigBuilder quoteEscapeCharacter(Character quoteEscapeCharacter) {
            this.quoteEscapeCharacter = quoteEscapeCharacter;
            return this;
        }

        public CsvConfig build() {
            return new CsvConfig(separator, quoteCharacter, quoteEscapeCharacter);
        }
    }

}
