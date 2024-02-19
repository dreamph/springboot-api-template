package com.github.dreamph.core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DownloadUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadUtils.class);

    public static boolean checkLink(URL url) {
        int fileSize = getFileSize(url);
        return (fileSize > 0);
    }

    public static int getFileSize(URL url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void downloadFile(URL url, File outputFile) throws Exception {
        prepareOutputDir(outputFile);

        try {
            if (!outputFile.exists()) {
                File tmpFile = new File(outputFile.getParentFile(), FilenameUtils.getName(outputFile.getName()) + ".tmp");
                try {
                    LOG.info("Downloading : " + outputFile + "###" + url);
                    FileUtils.copyURLToFile(url, tmpFile);
                    FileUtils.moveFile(tmpFile, outputFile);
                    LOG.info("Download success : " + outputFile + "###" + url);
                    if (tmpFile.exists()) {
                        FileUtils.deleteQuietly(tmpFile);
                    }
                } catch (Exception e) {
                    if (tmpFile.exists()) {
                        FileUtils.deleteQuietly(tmpFile);
                    }
                    LOG.info("Download Error, Delete Temp File  : " + tmpFile + "###" + url);
                    throw e;
                }
            } else {
                LOG.info("File exists, Skip Download  : " + outputFile + "###" + url);
            }
        } catch (Exception e) {
            LOG.error("Error URL : " + url);
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    protected static void prepareOutputDir(File outputFile) throws IOException {
        if (!outputFile.getParentFile().exists()) {
            FileUtils.forceMkdir(outputFile.getParentFile());
        }
    }

    public static ResponseEntity<byte[]> streamToDownload(byte[] data, String fileName) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header("Access-Control-Expose-Headers", "Content-Disposition,x-filename,x-encodeFilename") //or @CrossOrigin//(exposedHeaders = "Content-Disposition,x-filename,x-encodeFilename")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"")
                .header("x-filename", Base64.getEncoder().encodeToString(fileName.getBytes()))
                .header("x-encodeFilename", "true")
                .body(data);
    }

}
