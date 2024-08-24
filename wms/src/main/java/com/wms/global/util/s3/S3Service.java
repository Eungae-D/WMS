package com.wms.global.util.s3;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadFile(MultipartFile multipartFile, String url);
    boolean delete(String fileUrl);
}
