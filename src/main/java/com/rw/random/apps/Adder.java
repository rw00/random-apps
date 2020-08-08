package com.rw.random.apps;

import java.util.ArrayDeque;
import java.util.Deque;


public class Adder {
    private final Deque<Integer> undo;
    private final Deque<Integer> redo;
    private int result;


    public Adder() {
        undo = new ArrayDeque<>();
        redo = new ArrayDeque<>();
        result = 0;
    }


    public int getResult() {
        return result;
    }

    public int add(int number) {
        undo.push(result);
        result += number;
        return result;
    }

    public int redo() {
        handleAction(redo, "Nothing to Redo!", undo);
        return result;
    }

    public int undo() {
        handleAction(undo, "Nothing to Undo!", redo);
        return result;
    }

    private void handleAction(Deque<Integer> valuesStack, String errorMsg, Deque<Integer> valuesBackupStack) {
        if (valuesStack.isEmpty()) {
            throw new UnsupportedOperationException(errorMsg);
        } else {
            valuesBackupStack.push(result);
            result = valuesStack.pop();
        }
    }
}
