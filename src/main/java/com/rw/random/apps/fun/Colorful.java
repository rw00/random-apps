package com.rw.random.apps.fun;

public class Colorful {
    public Colorful() {
    }

    public static void main(String[] args) {
        Logger.log(inRed("This text is colored in red"));
    }

    private static String inRed(String text) {
        return escapeChar() + "[31m" + text + escapeChar() + "[0m";
    }

    private static char escapeChar() {
        return (char) 27;
    }
}
