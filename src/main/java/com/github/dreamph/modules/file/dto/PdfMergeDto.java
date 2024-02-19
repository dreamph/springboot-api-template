package com.github.dreamph.modules.file.dto;

import lombok.Data;

public class PdfMergeDto {

    @Data
    public static class PdfMergeResponse {
        private byte[] data;
        private String fileName;
    }
}
