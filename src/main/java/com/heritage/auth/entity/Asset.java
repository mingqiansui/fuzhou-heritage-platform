package com.heritage.auth.entity;

import lombok.Data;

@Data
public class Asset {
    private Long id;
    private String filename;
    private String uploader;
    private String status;
    private String encPath;
}
