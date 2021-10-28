package com.depromeet.dgdg.controller;


import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/poster")
@AllArgsConstructor
public class PostControllerV1 {
    private final PostService postService;

    /*
    @PutMapping("{/id}")
    public BaseResponse<String> modifyPost(@PathVariable Long postId) {
        postService.modifyPost(postId);
        return BaseResponse.OK;

    }

    @PostMapping
    public BaseResponse<String> createPost(@RequestBody) {
        return postService.createPost();
    }

     */

    @DeleteMapping("{/id}")
    public BaseResponse<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.OK;
    }
}
