package com.rw.random.apps.fun;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;


class OneXmlElementParserTest {
    @Test
    void testParsesCorrectly() throws IOException {
        OneXmlElementParser oneXmlElementParser = new OneXmlElementParser("internal");
        String value = oneXmlElementParser.parse(new StringReader("<root><internal>secret</internal></root>"));
        assertEquals("secret", value);
    }

    @Test
    void testParsesLengthyValue() throws IOException {
        OneXmlElementParser oneXmlElementParser = new OneXmlElementParser("data");
        String value = oneXmlElementParser.parse(new StringReader(test_lengthy_value_data()));
        assertEquals("moreThanSize_123123123123123123123123123123123123123123123123123123123123123", value);
    }

    @Test
    void testParsesEmptyElement() throws IOException {
        OneXmlElementParser oneXmlElementParser = new OneXmlElementParser("data");
        String value = oneXmlElementParser.parse(new StringReader("<data></data>"));
        assertTrue(value.isEmpty());
    }

    @Test
    void testHandlesNotFoundElement() throws IOException {
        OneXmlElementParser oneXmlElementParser = new OneXmlElementParser("unknown");
        String value = oneXmlElementParser.parse(new StringReader("<root><data></data></root>"));
        assertNull(value);
    }

    @Test
    void testReadsInnerElements() throws IOException {
        OneXmlElementParser oneXmlElementParser = new OneXmlElementParser("root");
        String value = oneXmlElementParser.parse(new StringReader("<root><part>data</part></root>"));
        assertEquals("<part>data</part>", value);
    }

    private String test_lengthy_value_data() {
        return //
            "<root><data>moreThanSize_123123123123123123123123123123123123123123123123123123123123123</data></root>";
    }
}
