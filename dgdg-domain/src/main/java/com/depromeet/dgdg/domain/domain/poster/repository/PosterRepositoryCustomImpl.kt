package com.depromeet.dgdg.domain.domain.poster.repository

import com.depromeet.dgdg.domain.domain.poster.Poster
import com.depromeet.dgdg.domain.domain.poster.QPoster.poster
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class PosterRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
): PosterRepositoryCustom {

    override fun findPosterById(userId: Long, posterId: Long): Poster? {
        return queryFactory.selectFrom(poster)
            .where(
                poster.id.eq(posterId),
                poster.user.id.eq(userId)
            )
            .fetchOne()
    }

    override fun findPosters(userId: Long, pageable: Pageable): Page<Poster> {
        val posters = queryFactory.selectFrom(poster)
            .where(poster.user.id.eq(userId))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(poster.updatedAt.desc())
            .fetchResults()

        return PageImpl<Poster>(posters.results, pageable, posters.total)
    }
}
