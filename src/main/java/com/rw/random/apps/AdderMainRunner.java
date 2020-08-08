package com.rw.random.apps;

import java.util.Scanner;


public class AdderMainRunner {
    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("debug", "false"));


    private AdderMainRunner() {
    }


    // driver
    public static void main(String[] args) {
        AdderMainRunner adderRunner = new AdderMainRunner();
        adderRunner.run();
    }

    public void run() {
        try (Scanner userInputScanner = new Scanner(System.in)) {
            run(userInputScanner);
        }
    }

    private void run(Scanner userInputScanner) {
        Adder adder = new Adder();
        do {
            logInline("Enter an integer number to be added   | Undo | Redo | Exit: ");
            Integer result = doRun0(userInputScanner, adder);
            if (result == null) {
                break;
            }
            log("Current value: " + result + "\n");
        } while (true);
    }

    private Integer doRun0(Scanner userInputScanner, Adder adder) {
        String input = userInputScanner.next().toLowerCase();
        Integer integerNum = parseInteger(input);
        int result = 0;
        if (integerNum != null) {
            result = adder.add(integerNum);
        } else {
            try {
                switch (input) {
                case "redo":
                    result = adder.redo();
                    break;
                case "undo":
                    result = adder.undo();
                    break;
                case "exit":
                    log("The app closes now");
                    return null;
                default:
                    log("Invalid command. Try again");
                    break;
                }
            } catch (UnsupportedOperationException ex) {
                log(ex.getMessage());
            }
        }
        return result;
    }

    private void logInline(String msg) {
        System.out.print(msg);
    }

    private void log(String msg) {
        System.out.println(msg);
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
