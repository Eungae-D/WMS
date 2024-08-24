package com.wms.global.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.wms.global.exception.exception.S3Exception;
import com.wms.global.exception.responseCode.S3ExceptionResponseCode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    @Value("${cloud.aws.s3.dir}")
//    private String dir;

    // 파일 업로드
    @Override
    public String uploadFile(MultipartFile multipartFile, String url) {
        // 기본 디렉토리 설정
        String fileName = url + "/" + UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getSize());
        try {
            amazonS3.putObject(bucket, fileName, multipartFile.getInputStream(), objMeta);
        } catch (IOException e) {
            throw new S3Exception(S3ExceptionResponseCode.UPLOAD_EXCEPTION,"S3 업로드 중 에러가 발생");
        }
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    // 파일 삭제
    @Override
    public boolean delete(String fileUrl) {
        try {
            String[] temp = fileUrl.split(".com/");
            String fileKey = temp[1];
            amazonS3.deleteObject(bucket, fileKey);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
