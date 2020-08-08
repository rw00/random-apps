package com.rw.random.apps;

import java.io.StringReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class FastReaderTest {
    @Test
    void testReaderNext() throws Exception {
        StringReader reader = new StringReader("Hello World!");
        FastReader fastReader = new FastReader(reader);
        Assertions.assertEquals("Hello", fastReader.next());
        Assertions.assertEquals("World!", fastReader.next());
    }

    @Test
    void testReaderNextLine() throws Exception {
        StringReader reader = new StringReader("Bonjour !\ntout l'monde !");
        FastReader fastReader = new FastReader(reader);
        Assertions.assertEquals("Bonjour !", fastReader.nextLine());
        Assertions.assertEquals("tout l'monde !", fastReader.nextLine());
    }

    @Test
    void testReaderNumber() throws Exception {
        StringReader reader = new StringReader("10 4.2 99999999999999999");
        FastReader fastReader = new FastReader(reader);
        Assertions.assertEquals(10, fastReader.nextInt());
        Assertions.assertEquals(4.2, fastReader.nextDouble());
        Assertions.assertEquals(99999999999999999L, fastReader.nextLong());
    }
}
