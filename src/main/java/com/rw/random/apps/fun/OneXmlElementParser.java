package com.rw.random.apps.fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;


public class OneXmlElementParser {
    private static final int SIZE = 64; // characters at a time
    private final char[] tag;
    private final char[] closeTag;


    public OneXmlElementParser(String tagName) {
        this.tag = ("<" + tagName + ">").toCharArray();
        this.closeTag = ("</" + tagName + ">").toCharArray();
    }


    public String parse(Reader underlyingReader) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(underlyingReader)) {
            return doParse(bufferedReader);
        }
    }

    private String doParse(BufferedReader reader) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();
        ReadValueResult readValueResult = new ReadValueResult(null, false);
        char[] buffer = new char[SIZE];
        int read = reader.read(buffer, 0, buffer.length);
        while ((!readValueResult.closeTagFound) && (read != -1)) {
            int startIndex = findPattern(buffer, tag, 0);
            if (startIndex > -1) {
                readTagValue(readValueResult, buffer, startIndex);
                resultBuilder.append(readValueResult.readChars);
                while ((!readValueResult.closeTagFound) && (read != -1)) { // buffer is not enough
                    read = reader.read(buffer, 0, buffer.length);
                    readTagValue(readValueResult, buffer, 0);
                    resultBuilder.append(readValueResult.readChars);
                }
            } else {
                read = reader.read(buffer, 0, buffer.length);
            }
        }
        return readValueResult.closeTagFound ? resultBuilder.toString() : null;
    }

    private int findPattern(char[] buffer, char[] pattern, int startIndex) {
        for (int i = startIndex; i <= (buffer.length - pattern.length); i++) {
            int j;
            for (j = 0; j < pattern.length; j++) {
                if (pattern[j] != buffer[i + j]) {
                    break;
                }
            }
            if (j == pattern.length) {
                return i + pattern.length;
            }
        }
        return -1;
    }

    private void readTagValue(ReadValueResult readValueResult, char[] buffer, int startIndex) {
        int lastIndex = findPattern(buffer, closeTag, startIndex);
        char[] chars;
        if (lastIndex > -1) {
            chars = Arrays.copyOfRange(buffer, startIndex, lastIndex - closeTag.length);
        } else {
            chars = Arrays.copyOfRange(buffer, startIndex, buffer.length);
        }
        readValueResult.readChars = chars;
        readValueResult.closeTagFound = lastIndex > -1;
    }

    private static class ReadValueResult {
        char[] readChars;
        boolean closeTagFound;

        ReadValueResult(char[] readChars, boolean closeTagFound) {
            this.readChars = readChars;
            this.closeTagFound = closeTagFound;
        }
    }
}
