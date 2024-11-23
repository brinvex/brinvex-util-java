package com.brinvex.util.java.csv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.brinvex.util.java.Collectors.toLinkedMap;
import static java.util.function.Function.identity;

public class CsvTable {

    private final LinkedHashMap<String, Integer> headers;

    private final List<List<String>> body;

    public static CsvTable of(Collection<String> lines, CsvConfig config) {
        List<List<String>> csvLines = CsvFormatter.parseCsvLines(lines, config);
        List<String> headerValues = csvLines.get(0);
        int headersSize = headerValues.size();
        LinkedHashMap<String, Integer> headers = IntStream.range(0, headersSize)
                .boxed()
                .collect(toLinkedMap(headerValues::get, identity()));
        int csvLinesSize = csvLines.size();
        List<List<String>> body = new ArrayList<>(csvLinesSize - 1);
        for (int i = 1; i < csvLinesSize; i++) {
            List<String> bodyRowValues = csvLines.get(i);
            if (bodyRowValues.size() != headersSize) {
                throw new IllegalArgumentException("Invalid row: %s".formatted(bodyRowValues));
            }
            body.add(bodyRowValues);
        }
        return new CsvTable(headers, body);
    }

    private CsvTable(LinkedHashMap<String, Integer> headers, List<List<String>> body) {
        this.headers = headers;
        this.body = body;
    }

    public List<String> getHeaders() {
        return headers.keySet().stream().toList();
    }

    /**
     * @param bodyRowIdx - zero-based
     */
    public String getValue(int bodyRowIdx, String header) {
        return body.get(bodyRowIdx).get(headers.get(header));
    }

    /**
     * @param bodyRowIdx - zero-based
     */
    public String getValue(int bodyRowIdx, int col) {
        return body.get(bodyRowIdx).get(col);
    }

    public List<String> toCsvLines(CsvConfig config) {
        return CsvFormatter.formatCsvLines(headers.keySet(), body, config);
    }

    public int bodySize() {
        return body.size();
    }

    public Stream<Map<String, String>> bodyRows() {
        return body
                .stream()
                .map(bodyRow -> headers.entrySet()
                        .stream()
                        .collect(toLinkedMap(Map.Entry::getKey, e -> bodyRow.get(e.getValue()))));
    }
}
