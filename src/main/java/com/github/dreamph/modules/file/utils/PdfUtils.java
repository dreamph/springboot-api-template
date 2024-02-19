package com.github.dreamph.modules.file.utils;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PdfUtils {

    public static byte[] createPdf(String text) throws IOException {
        PDPage pdPage = new PDPage();
        PDFont pdfFont = PDType1Font.HELVETICA_BOLD;
        int fontSize = 28;

        try (PDDocument document = new PDDocument()) {
            try (PDPageContentStream contentStream = new PDPageContentStream(document, pdPage, PDPageContentStream.AppendMode.APPEND, true, true)) {
                contentStream.setFont(pdfFont, fontSize);
                contentStream.beginText();
                contentStream.newLineAtOffset(200, 685);
                contentStream.showText(text);
                contentStream.endText();

                contentStream.setFont(pdfFont, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(200, 500);
                contentStream.showText(new Date().toString());
                contentStream.endText();
            }

            document.addPage(pdPage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        }
    }

    public static byte[] merge(List<byte[]> files) throws IOException {
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        for (byte[] fileData : files) {
            pdfMergerUtility.addSource(new ByteArrayInputStream(fileData));
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfMergerUtility.setDestinationStream(byteArrayOutputStream);

        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        return byteArrayOutputStream.toByteArray();
    }

}
