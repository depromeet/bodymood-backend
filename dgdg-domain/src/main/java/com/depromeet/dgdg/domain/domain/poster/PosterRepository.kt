package com.depromeet.dgdg.domain.domain.poster

import com.depromeet.dgdg.domain.domain.poster.repository.PosterRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface PosterRepository : JpaRepository<Poster, Long>, PosterRepositoryCustom
