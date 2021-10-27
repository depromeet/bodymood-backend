package com.depromeet.dgdg.common.utils

import com.depromeet.dgdg.common.exception.BadRequestException
import org.apache.commons.io.FilenameUtils
import java.util.*

object FileUtils {

    private const val DOT = "."

    @JvmStatic
    fun createUniqueFileNameWithExtension(fileName: String?): String {
        if (FilenameUtils.indexOfExtension(fileName) == -1) {
            throw BadRequestException("확장자가 없는 잘못된 파일 형식 입니다. $fileName")
        }
        val extension = DOT + FilenameUtils.getExtension(fileName)
        return UUID.randomUUID().toString() + extension
    }

}
