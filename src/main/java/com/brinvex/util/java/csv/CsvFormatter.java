package com.brinvex.util.java.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

public class CsvFormatter {

    public static List<List<String>> parseCsvLines(Collection<String> lines, CsvConfig config) {
        List<List<String>> results = new ArrayList<>();
        for (String line : lines) {
            char separator = config.separator();
            Character quoteCharacter = config.quoteCharacter();
            Character quoteEscapeCharacter = config.quoteEscapeCharacter();

            if (quoteCharacter == null) {
                String[] values = line.split(String.valueOf(separator));
                results.add(List.of(Arrays.stream(values).map(String::trim).toArray(String[]::new)));
            } else {
                List<String> lineValues = new ArrayList<>();
                StringBuilder currentValue = new StringBuilder();
                boolean inQuotes = false;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    if (c == quoteCharacter && (i == 0 || quoteEscapeCharacter == null || line.charAt(i - 1) != quoteEscapeCharacter)) {
                        inQuotes = !inQuotes;
                    } else if (c == separator && !inQuotes) {
                        lineValues.add(currentValue.toString().trim());
                        currentValue.setLength(0);
                    } else {
                        currentValue.append(c);
                    }
                }
                lineValues.add(currentValue.toString().trim());
                results.add(lineValues);
            }
        }
        return results;
    }

    public static List<String> formatCsvLines(Collection<String> headers, List<List<String>> bodyRows, CsvConfig config) {
        Collector<CharSequence, ?, String> lineCollector;
        Function<String, String> valueTransformer;

        Character quoteCharacter = config.quoteCharacter();
        if (quoteCharacter == null) {
            lineCollector = joining(String.valueOf(config.separator()));
            valueTransformer = identity();
        } else {
            String quoteCharacterStr = String.valueOf(quoteCharacter);
            lineCollector = joining(String.valueOf(config.separator()));
            Character quoteEscapeCharacter = config.quoteEscapeCharacter();
            if (quoteEscapeCharacter == null) {
                valueTransformer = s -> quoteCharacterStr + s + quoteCharacterStr;
            } else {
                String quoteEscapeCharacterStr = String.valueOf(quoteEscapeCharacter);
                valueTransformer = s -> quoteCharacterStr + s.replace(quoteCharacterStr, quoteEscapeCharacterStr + quoteCharacterStr) + quoteCharacterStr;
            }
        }
        return Stream.of(List.of(headers), bodyRows)
                .flatMap(Collection::stream)
                .map(s -> s.stream().map(valueTransformer).collect(lineCollector))
                .collect(toCollection(ArrayList::new));
    }
}
