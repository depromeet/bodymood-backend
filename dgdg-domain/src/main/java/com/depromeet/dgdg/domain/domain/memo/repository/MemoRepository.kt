package com.depromeet.dgdg.domain.domain.memo.repository

import com.depromeet.dgdg.domain.domain.memo.Memo
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository : JpaRepository<Memo, Long>
