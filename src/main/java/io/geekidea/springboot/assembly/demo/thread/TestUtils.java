package io.geekidea.springboot.assembly.demo.thread;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static ArrayList list = new ArrayList();

    public static void putValue(String value) {
        list.add(value);
    }
}
