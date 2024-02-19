package com.github.dreamph.constants;

import org.apache.pdfbox.io.MemoryUsageSetting;

public class AppConstants {
    public static class Pdf {
        public static MemoryUsageSetting MEMORY_USAGE_SETTING = MemoryUsageSetting.setupMixed(150 * 1024 * 1024); //150MB
    }

    public static class FileExtension {
        public static final String PDF = ".pdf";
        public static final String JPG = ".jpg";
        public static final String JASPER = ".jasper";
        public static final String P12 = ".p12";
    }
}
