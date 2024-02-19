package com.github.dreamph.core.dto;


import lombok.Data;

import java.util.Date;

@Data
//@AllArgsConstructor
public class ErrorDetail {
    //private String apiModule;
    //private String apiName;
    private boolean status;
    private int statusCode;
    private Date timestamp;
    private String message;
    private String detail;

}