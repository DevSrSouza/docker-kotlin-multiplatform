package br.com.devsrsouza.kotlin.docker.auth

class OAuth : br.com.devsrsouza.kotlin.docker.auth.Authentication {
    var accessToken: String? = null

    override fun apply(query: MutableMap<String, List<String>>, headers: MutableMap<String, String>) {
        val token: String = accessToken ?: return
        headers["Authorization"] = "Bearer $token"
    }
}