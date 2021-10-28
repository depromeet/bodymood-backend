package com.depromeet.dgdg.service.auth.dto.response;

import com.depromeet.dgdg.domain.domain.poster.Poster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    Long postId;
    String postUrl;

    public static PostResponse of(Poster poster) {
        return new PostResponse(poster.getId(), poster.getImageUrl());
    }
}
