package com.depromeet.dgdg.service.upload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.depromeet.dgdg.common.utils.FileUtils;
import com.depromeet.dgdg.external.kakao.dto.properties.S3Properties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public S3Service(AmazonS3 amazonS3, S3Properties s3Properties) {
        this.amazonS3 = amazonS3;
        this.s3Properties = s3Properties;
    }

    public String upload(MultipartFile file) {
        String fileName = FileUtils.createUniqueFileNameWithExtension(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        try {
            amazonS3.putObject(new PutObjectRequest(s3Properties.getBucket(), fileName, file.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일을 S3로 전송하는 중 에러가 발생하였습니다. (%s)", fileName));
        }
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

}
