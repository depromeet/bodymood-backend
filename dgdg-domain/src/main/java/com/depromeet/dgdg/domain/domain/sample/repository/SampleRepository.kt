package com.depromeet.dgdg.domain.domain.sample.repository

import com.depromeet.dgdg.domain.domain.sample.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>, SampleRepositoryCustom {

}
