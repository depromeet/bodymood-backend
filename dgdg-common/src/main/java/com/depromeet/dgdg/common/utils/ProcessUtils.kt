package com.depromeet.dgdg.common.utils

import org.springframework.util.StringUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object ProcessUtils {

    private val OS = System.getProperty("os.name").toLowerCase(Locale.getDefault())

    fun findAvailableRandomPort(): Int {
        for (port in 10000..30000) {
            val process = executeGrepProcessCommand(port)
            if (!isRunning(process)) {
                return port
            }
        }
        throw IllegalArgumentException("사용가능한 랜덤 포트를 찾을 수 없습니다. (10000 ~ 30000)")
    }

    private fun executeGrepProcessCommand(port: Int): Process {
        // 윈도우일 경우
        if (isWindows()) {
            val command = String.format("netstat -nao | find \"LISTEN\" | find \"%d\"", port)
            val shell = arrayOf("cmd.exe", "/y", "/c", command)
            return Runtime.getRuntime().exec(shell)
        }
        val command = String.format("netstat -nat | grep LISTEN|grep %d", port)
        val shell = arrayOf("/bin/sh", "-c", command)
        return Runtime.getRuntime().exec(shell)
    }

    private fun isWindows(): Boolean {
        return OS.contains("win")
    }

    private fun isRunning(process: Process): Boolean {
        var line: String?
        val pidInfo = StringBuilder()
        try {
            BufferedReader(InputStreamReader(process.inputStream)).use { input ->
                while (input.readLine().also {
                        line = it
                    } != null) {
                    pidInfo.append(line)
                }
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("사용가능한 포트를 찾는 중 에러가 발생하였습니다.")
        }
        return StringUtils.hasLength(pidInfo.toString())
    }

}
