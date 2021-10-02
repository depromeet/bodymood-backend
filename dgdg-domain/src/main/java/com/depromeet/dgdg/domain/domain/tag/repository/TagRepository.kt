package com.depromeet.dgdg.domain.domain.tag.repository

import com.depromeet.dgdg.domain.domain.tag.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long>
