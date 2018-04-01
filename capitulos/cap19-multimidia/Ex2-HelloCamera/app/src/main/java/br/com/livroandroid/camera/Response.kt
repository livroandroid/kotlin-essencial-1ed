package br.com.livroandroid.camera

data class Response(val id: Long, val status: String, val msg: String, val url: String) {
    fun isOk() = "OK".equals(status, ignoreCase = true)

    companion object {
        fun error(): Response {
            val resp = Response(0,"error" , ",", ",")
            return resp
        }
    }
}
