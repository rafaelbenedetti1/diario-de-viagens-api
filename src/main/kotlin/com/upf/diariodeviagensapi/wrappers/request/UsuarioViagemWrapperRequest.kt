package com.upf.diariodeviagensapi.wrappers.request

data class UsuarioViagemWrapperRequest(

    val id: String? = null,
    val usuario: String,
    val viagem: ViagemWrapperRequest?
) {
    data class ViagemWrapperRequest(
        val id: String? = null,
        val titulo: String? = null,
        val descricao: String? = null,
        val localizacao: LocalizacaoWrapperRequest? = null,
        val imagemCapa: String? = null,
        val imagens: ArrayList<String>? = null,
    ) {
        data class LocalizacaoWrapperRequest(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
        )
    }
}