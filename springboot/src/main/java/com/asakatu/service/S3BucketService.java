package com.asakatu.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.asakatu.property.S3BucketProperties;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3BucketService {
    private final S3BucketProperties s3BucketProperties;

    public S3BucketService(S3BucketProperties s3BucketProperties) {
        this.s3BucketProperties = s3BucketProperties;
    }

    public String storeFile(MultipartFile file) {
        String fileName = RandomString.make(10);

        try {
            // TODO: fileの安全性確認

            // .defaultClient()
            // ファイルをS3にアップロードする
            AmazonS3ClientBuilder.standard().withRegion("us-east-1").build().putObject( new PutObjectRequest(
                    s3BucketProperties.getBucket(),
                    getFilePath(fileName),
                    file.getInputStream(),
                    new ObjectMetadata()
            ).withCannedAcl(CannedAccessControlList.PublicRead)); // 公開設定
            return fileName;

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        } catch (AmazonServiceException e) {
            throw new FileStorageException("s3へのアップロードに失敗しました" , e);
        }
    }

    public String getObjectURL(String fileName){
        String url = "https://%s.s3.amazonaws.com/%s/%s";
        return String.format(
                url,
                s3BucketProperties.getBucket(),
                s3BucketProperties.getFolder(),
                fileName);
    }

    public String getDefaultImagePath(){
        System.out.println(s3BucketProperties.getDefaultImage());
        return getObjectURL(s3BucketProperties.getDefaultImage());
    }

    private String getFilePath(String fileName){
        String path = "%s/%s";
        return String.format(
                path,
                s3BucketProperties.getFolder(),
                fileName);
    }
}


class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}