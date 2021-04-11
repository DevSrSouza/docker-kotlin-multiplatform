package br.com.devsrsouza.kotlin.docker.utils

import io.ktor.util.*

object UnixDomain {
    @UseExperimental(ExperimentalStdlibApi::class)
    fun encodeHostname(path: String): String {
        return path.encodeToByteArray().toHexString() + ".socket"
    }
}