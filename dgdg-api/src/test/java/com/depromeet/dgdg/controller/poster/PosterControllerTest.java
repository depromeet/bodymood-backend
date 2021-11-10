package com.depromeet.dgdg.controller.poster;

import com.depromeet.dgdg.common.exception.BadRequestException;
import com.depromeet.dgdg.controller.poster.dto.PosterRequest;
import com.depromeet.dgdg.domain.domain.poster.Poster;
import com.depromeet.dgdg.service.poster.PosterService;
import com.depromeet.dgdg.service.poster.dto.PagePosterPhotoResponse;
import com.depromeet.dgdg.service.poster.dto.PosterPhotoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PosterControllerTest {

    @InjectMocks
    private PosterController posterController;


    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private PosterService posterService;

    private MockMvc mockMvc;


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    final PosterRequest posterRequest = new PosterRequest(any(), any(), "happy", any());
    final Poster poster = Poster.Companion.of(any(), any(), any(), "happy");
    final PosterPhotoResponse posterPhotoResponse = new PosterPhotoResponse(1L, "imageUrl", any(), any());


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(posterController).build();
    }

    @Test
    public void getPosterPhotoById_searchByPostId_returnPosterPhotoResponse() throws Exception {
        PosterPhotoResponse posterPhotoResponse = new PosterPhotoResponse(1L, "imageUrl", any(), any());
        when(posterService.getPosterById(1L, 1L)).thenReturn(posterPhotoResponse);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/posters/{posterId}", 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect((ResultMatcher) jsonPath("$.photoId", is(1)))
            .andExpect((ResultMatcher) jsonPath("$.ImageUrl", is("imageUrl")));

        verify(posterService, times(1)).getPosterById(1L, 1L);
        verifyNoMoreInteractions(posterService);

    }


    @Test
    public void getPosterPhotoById_searchByStrangePostId_returnBadRequestException() throws Exception {

        when(posterService.getPosterById(1L, 1L)).thenThrow(new BadRequestException(any(), any()));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/posters/{posterId}", 1L))
            .andExpect(status().isNotFound());

        verify(posterService, times(1)).getPosterById(1L, 1L);
        verifyNoMoreInteractions(posterService);

    }

    @Test
    public void getPosterPhotos_requestPageRequest_getAllPost() throws Exception {
        PagePosterPhotoResponse pagePosterPhotoResponse = new PagePosterPhotoResponse(any(), 2, 2, any());
        when(posterService.getPosterPhotos(any(), any())).thenReturn(pagePosterPhotoResponse);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/posters")
            .param("userId", any())
            .param("page", any())
            .param("size", any())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(posterService, times(1)).getPosterPhotos(any(), any());
        verifyNoMoreInteractions(posterService);
    }

    /*
    @Test
    public void makePoster_success_returnPoster() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("photoId", "1L");
        input.put("imageUrl", "imageUrl");
        input.put("createdAt", any());
        input.put("updatedAt", any());

        String content = objectMapper.writeValueAsString(input);
        mockMvc.perform(post("/api/v1/posters")
            .content(content)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }


     */

}
