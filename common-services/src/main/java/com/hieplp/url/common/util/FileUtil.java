package com.hieplp.url.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    private FileUtil() {
        throw new IllegalStateException("Utility class: FileUtil");
    }

    public static String getFilePathFromUserDir(String fileName) {
        return System.getProperty("user.dir") + fileName;
    }

    public static byte[] getBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(getFilePathFromUserDir(fileName)));
    }
}
