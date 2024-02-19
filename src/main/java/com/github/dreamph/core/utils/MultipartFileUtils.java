package com.github.dreamph.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MultipartFileUtils {
    public static List<byte[]> toBytesList(List<MultipartFile> multipartFiles) throws Exception {
        if (multipartFiles == null) {
            return null;
        }
        return multipartFiles.stream().map((m) -> {
            try {
                return m.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static byte[] toBytes(MultipartFile pdfFile) throws Exception {
        if (pdfFile == null) {
            return null;
        }
        return pdfFile.getBytes();
    }

}
