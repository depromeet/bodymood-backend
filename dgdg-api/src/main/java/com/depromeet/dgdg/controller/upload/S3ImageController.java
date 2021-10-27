package com.depromeet.dgdg.controller.upload;

import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.service.upload.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class S3ImageController {

    private final S3Service s3Service;

    @PostMapping("/api/v1/upload/images")
    public BaseResponse<String> upload(@RequestPart(value = "images") MultipartFile images) {
        return BaseResponse.success(s3Service.upload(images));
    }

}
