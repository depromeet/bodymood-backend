package com.depromeet.dgdg.domain.domain.sample.repository

import com.depromeet.dgdg.domain.domain.sample.QSample.sample
import com.depromeet.dgdg.domain.domain.sample.Sample
import com.querydsl.jpa.impl.JPAQueryFactory

class SampleRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
): SampleRepositoryCustom {

    override fun findSampleById(id: Long): Sample? {
        return queryFactory.selectFrom(sample)
            .where(
                sample.id.eq(id)
            ).fetchOne()
    }

}
