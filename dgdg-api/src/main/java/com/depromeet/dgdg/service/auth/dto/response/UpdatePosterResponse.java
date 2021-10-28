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
public class UpdatePosterResponse {
    Long postId;
    String postUrl;

    public static UpdatePosterResponse of(Poster poster) {
        return new UpdatePosterResponse(poster.getId(), poster.getImageUrl());
    }
}
