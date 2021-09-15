package com.depromeet.dgdg.domain.domain.sample

import com.depromeet.dgdg.domain.domain.sample.repository.SampleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SampleRepositoryTest(
    @Autowired
    private val sampleRepository: SampleRepository
) {

    @Test
    fun test_querydsl() {
        // given
        val sample = sampleRepository.save(Sample("sample"))

        // when
        val findSample = sampleRepository.findSampleById(sample.id)

        // then
        assertThat(findSample!!.name).isEqualTo(sample.name)
    }

}
