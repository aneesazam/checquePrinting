package com.checquePrinting.model;

import lombok.Data;

@Data
public class ChequeErrorResponse {
    private int statusCode;
    private String message;
    private long timeStamp;
}
