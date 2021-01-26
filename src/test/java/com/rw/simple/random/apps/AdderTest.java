package com.rw.simple.random.apps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AdderTest {
    public AdderTest() {
    }


    @Test
    void testAdd() {
        Adder adder = new Adder();
        int current = adder.add(99);
        Assertions.assertEquals(99, current);

        current = adder.add(-9);
        Assertions.assertEquals(90, current);

        current = adder.add(110);
        Assertions.assertEquals(200, current);
    }

    @Test
    void testUndo() {
        Adder adder = new Adder();

        int current = adder.add(5);
        Assertions.assertEquals(5, current);

        Assertions.assertEquals(0, adder.undo());

        try {
            adder.undo();
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof UnsupportedOperationException);
            Assertions.assertEquals("Nothing to Undo!", ex.getMessage());
        }

        Assertions.assertEquals(0, adder.getResult());
    }

    @Test
    void testRedo() {
        Adder adder = new Adder();

        int current = adder.add(7);
        Assertions.assertEquals(7, current);

        try {
            adder.redo();
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof UnsupportedOperationException);
            Assertions.assertEquals("Nothing to Redo!", ex.getMessage());
        }

        current = adder.undo();
        Assertions.assertEquals(0, current);

        current = adder.redo();
        Assertions.assertEquals(7, current);
    }
}
