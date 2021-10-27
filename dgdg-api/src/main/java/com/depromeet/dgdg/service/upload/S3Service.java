package com.depromeet.dgdg.service.upload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.depromeet.dgdg.external.kakao.dto.properties.S3Properties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@AllArgsConstructor
@Service
public class S3Service {
    private AmazonS3 amazonS3;

    private S3Properties s3Properties;


    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        amazonS3.putObject(new PutObjectRequest(s3Properties.getBucket(), fileName, file.getInputStream(), objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }
}
