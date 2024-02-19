package com.github.dreamph.api;

import com.github.dreamph.core.utils.DownloadUtils;
import com.github.dreamph.core.utils.MultipartFileUtils;
import com.github.dreamph.modules.file.FileService;
import com.github.dreamph.modules.file.dto.PdfMergeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileApi {
    private final FileService fileService;

    @Operation(summary = "Add QRCode to PDF")
    @PostMapping(value = "/pdf/merge", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> pdfMerge(
            @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "string", format = "binary"))) @Valid @RequestParam("pdfFiles") List<MultipartFile> pdfFiles
    ) throws Exception {
        List<byte[]> files = MultipartFileUtils.toBytesList(pdfFiles);
        PdfMergeDto.PdfMergeResponse response = fileService.pdfMerge(files);
        return DownloadUtils.streamToDownload(response.getData(), response.getFileName());
    }

}
