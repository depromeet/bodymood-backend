package com.depromeet.dgdg.service;

import com.depromeet.dgdg.controller.poster.dto.PosterDetail;
import com.depromeet.dgdg.controller.poster.dto.PosterResponse;
import com.depromeet.dgdg.domain.domain.poster.Poster;
import com.depromeet.dgdg.domain.domain.poster.PosterRepository;
import com.depromeet.dgdg.domain.domain.user.User;
import com.depromeet.dgdg.domain.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PosterRepository posterRepository;
    private final UserRepository userRepository;

    public PosterResponse modifyPoster(Long userId, Long posterId, String newPosterUrl, String originalUrl, PosterDetail request) {

        User user = userRepository.findUserByIdFetchJoinPoster(userId);
        Poster poster = posterRepository.findPosterById(userId, posterId);
        Poster newPoster = Poster.Companion.of(user, newPosterUrl, originalUrl, request.getEmotion());
        posterRepository.delete(poster);
        posterRepository.save(newPoster);

        return PosterResponse.Companion.of(poster, request.getCategories());
    }


    @Transactional
    public void deletePost(Long postId) {
        posterRepository.deleteById(postId);
    }
}


