package com.rw.simple.random.apps;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


// This is hardly testable
public class Adder {
    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("debug", "false"));
    private final Deque<Integer> undo;
    private final Deque<Integer> redo;


    public Adder() {
        undo = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }


    public void run() {
        try (Scanner userInputScanner = new Scanner(System.in)) {
            int num = 0;
            do {
                logInline("Enter an integer number to be added   | Undo | Redo | Exit: ");
                String input = userInputScanner.next().toLowerCase();
                Integer integerNum = parseInteger(input);
                if (integerNum != null) {
                    undo.push(num);
                    num += integerNum;
                } else {
                    if ("redo".equals(input)) {
                        num = handleAction(num, redo, "Nothing to Redo!", undo);
                    } else if ("undo".equals(input)) {
                        num = handleAction(num, undo, "Nothing to Undo!", redo);
                    } else if ("exit".equals(input)) {
                        log("The app closes now");
                        break;
                    } else {
                        log("Invalid command. Try again");
                    }
                }
                log("Current value: " + num + "\n");
            } while (true);
        }
    }

    private int handleAction(int num, Deque<Integer> valuesStack, String errorMsg, Deque<Integer> valuesBackupStack) {
        if (valuesStack.isEmpty()) {
            log(errorMsg);
        } else {
            valuesBackupStack.push(num);
            num = valuesStack.pop();
        }
        return num;
    }

    private void log(String message) {
        System.out.println(message);
    }

    private void logInline(String message) {
        System.out.print(message);
    }

    private void log(Exception ex) {
        ex.printStackTrace();
    }

    private Integer parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ex) {
            if (DEBUG) {
                log(ex);
            }
            return null;
        }
    }
}
