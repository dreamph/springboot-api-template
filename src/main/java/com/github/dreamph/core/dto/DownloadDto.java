package com.github.dreamph.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadDto {
    private byte[] data;
    private String fileName;
    private String contentType;
}
