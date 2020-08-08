package com.rw.random.apps.fun;

import java.util.List;


public class MicroOptimized {
    public static void main(String[] args) {
        new MicroOptimized().loop();
    }

    public void loop() {
        List<String> list = List.of("test1", "test2", "test3");
        for (int i = -1, size = list.size(); ++i < size; ) {
            Logger.log(list.get(i));
        }
    }
}
