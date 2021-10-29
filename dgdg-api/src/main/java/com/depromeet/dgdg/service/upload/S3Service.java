package com.depromeet.dgdg.service.upload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.depromeet.dgdg.external.kakao.dto.properties.S3Properties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

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

    public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException{
        S3Object o = amazonS3.getObject(new GetObjectRequest(s3Properties.getBucket(), storedFileName));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }



}
