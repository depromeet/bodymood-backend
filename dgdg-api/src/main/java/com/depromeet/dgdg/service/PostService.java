package com.depromeet.dgdg.service;

import com.depromeet.dgdg.domain.domain.poster.Poster;
import com.depromeet.dgdg.domain.domain.poster.PosterRepository;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import com.depromeet.dgdg.service.auth.dto.response.PostResponse;
import com.depromeet.dgdg.service.auth.dto.response.UpdatePosterResponse;
import com.depromeet.dgdg.service.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PosterRepository posterRepository;

    private final S3Service s3Service;

/*
    @Transactional
    public void createPost() throws IOException {
        ResponseEntity<byte[]> poster = s3Service.getObject("storedFileName");
        PostResponse.of(poster);
        return posterRepository.save(poster);
        //이 부분 ResponseEntity로 받아온 부분이 Poster로 어떻게 맵핑시켜야할지
        //만들어 놓은  PostResponse를 이용해서 of로 맵핑해주고
        //posterRepository에 저장
    }

    //다운로드해서 수정하기
    @Transactional
    public PostResponse modifyPost(UpdatePosterResponse newPoster, Long postId)  {
        Poster poster = posterRepository.getById(postId);
        posterRepository.deleteById(postId);
        posterRepository.save(newPoster);
    }

 */

    @Transactional
    public void deletePost(Long postId) {
        posterRepository.deleteById(postId);
    }
}
