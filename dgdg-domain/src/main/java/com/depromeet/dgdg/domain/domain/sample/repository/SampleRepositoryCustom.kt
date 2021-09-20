package com.depromeet.dgdg.domain.domain.sample.repository

import com.depromeet.dgdg.domain.domain.sample.Sample

interface SampleRepositoryCustom {

    fun findSampleById(id: Long): Sample?

}
