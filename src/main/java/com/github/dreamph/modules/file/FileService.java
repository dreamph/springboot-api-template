package com.github.dreamph.modules.file;

import com.github.dreamph.constants.AppConstants;
import com.github.dreamph.core.utils.MessageCode;
import com.github.dreamph.modules.file.dto.PdfMergeDto;
import com.github.dreamph.modules.file.utils.PdfUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.dreamph.core.utils.ValidationUtils.isNotEmpty;
import static com.github.dreamph.core.utils.ValidationUtils.required;


@RequiredArgsConstructor
@Service
public class FileService {

    public void pdfMergeValidate(List<byte[]> pdfBytesList) throws Exception {
        required(isNotEmpty(pdfBytesList), MessageCode.E00001, "pdfBytesList");
    }

    public PdfMergeDto.PdfMergeResponse pdfMerge(List<byte[]> pdfBytesList) throws Exception {
        this.pdfMergeValidate(pdfBytesList);
        byte[] data = PdfUtils.merge(pdfBytesList);
        PdfMergeDto.PdfMergeResponse response = new PdfMergeDto.PdfMergeResponse();
        response.setData(data);
        response.setFileName("pdf_merge_" + System.currentTimeMillis() + AppConstants.FileExtension.PDF);
        return response;
    }
}
