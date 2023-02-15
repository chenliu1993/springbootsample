package com.example.demo.util;

import lombok.NoArgsConstructor;
import java.io.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public final class SystemUtil {

    public static SystemUtil systemUtil = null;
    static {
        log.info("Initialize the SystemUtil instance");
        if (systemUtil == null) {
            systemUtil = new SystemUtil();
        }
    }

    public static String GetSystemOS() {
        return System.getProperty("os.name");
    }

    public static boolean isWindows() {
        return GetSystemOS().startsWith("windows");
    }

    public static String translateIntoWindowsPath(String linuxPath) {
        String[] items = linuxPath.split("/", -1);
        log.info("file splited");

        // C:\demo
        String wholePath = items[0] + File.separator;

        // for convenience, i donot check it anymore
        for (int i = 1; i < items.length - 1; i = i + 1) {
            wholePath = wholePath + items[i] + File.separator;
        }

        wholePath = wholePath + items[items.length - 1];
        log.info("translate into", wholePath);

        return wholePath;
    }
}