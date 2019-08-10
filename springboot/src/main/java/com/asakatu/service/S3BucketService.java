package com.asakatu.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.asakatu.entity.User;
import com.asakatu.exeptions.FileStorageException;
import com.asakatu.property.S3ProfileImageProperties;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class S3BucketService {
    private final S3ProfileImageProperties s3ProfileImageProperties;

    public S3BucketService(S3ProfileImageProperties s3ProfileImageProperties) {
        this.s3ProfileImageProperties = s3ProfileImageProperties;
    }

    public String storeFile(MultipartFile file, User user) {
        String fileName = user.getId() + "_" + RandomString.make(10);

        try {
            // TODO: fileの安全性確認

            // .defaultClient()
            // ファイルをS3にアップロードする
            AmazonS3ClientBuilder.standard().withRegion(s3ProfileImageProperties.getRegion()).build().putObject(
                    new PutObjectRequest(
                        s3ProfileImageProperties.getBucket(),
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
                s3ProfileImageProperties.getBucket(),
                s3ProfileImageProperties.getFolder(),
                fileName);
    }

    public String getDefaultImagePath(){
        System.out.println(s3ProfileImageProperties.getDefaultImage());
        return getObjectURL(s3ProfileImageProperties.getDefaultImage());
    }

    private String getFilePath(String fileName){
        String path = "%s/%s";
        return String.format(
                path,
                s3ProfileImageProperties.getFolder(),
                fileName);
    }
}