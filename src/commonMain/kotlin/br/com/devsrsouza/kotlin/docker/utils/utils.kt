package br.com.devsrsouza.kotlin.docker.utils

const val DEFAULT_DOCKER_SOCKET_FILE = "/var/run/docker.sock"
const val API_PATH = "/v1.41"

fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }