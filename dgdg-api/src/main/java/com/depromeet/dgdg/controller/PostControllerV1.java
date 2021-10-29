package com.depromeet.dgdg.controller;


import com.depromeet.dgdg.config.auth.UserId;
import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.controller.poster.dto.PosterDetail;
import com.depromeet.dgdg.controller.poster.dto.PosterRequest;
import com.depromeet.dgdg.controller.poster.dto.PosterResponse;
import com.depromeet.dgdg.domain.domain.poster.Poster;
import com.depromeet.dgdg.service.PostService;
import com.depromeet.dgdg.service.upload.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@AllArgsConstructor
public class PostControllerV1 {
    private final PostService postService;
    private final S3Service s3Service;

    @PutMapping("{/api/v1/poster/posterId}")
    public BaseResponse<String> modifyPoster(@PathVariable Long posterId, @UserId Long userId, @ModelAttribute PosterRequest request) throws IOException {

        String newPosterUrl = s3Service.upload(request.getPosterImage());
        String originalUrl = s3Service.upload(request.getOriginImage());

        PosterDetail detail = PosterDetail.of(request.getEmotion(), request.getCategories());

        postService.modifyPoster(userId, posterId, newPosterUrl, originalUrl, detail);

        return BaseResponse.OK;
    }


    @DeleteMapping("{/api/v1/poster/posterId}")
    public BaseResponse<String> deletePost(@PathVariable Long posterId) {
        postService.deletePost(posterId);
        return BaseResponse.OK;
    }
}

