package com.upf.diariodeviagensapi.wrappers.response

import org.springframework.data.mongodb.core.mapping.Field

data class UsuarioViagemWrapperResponse(
    val id: String? = null,

    val usuario: String,

    val viagens: List<ViagemWrapperResponse>
) {
    data class ViagemWrapperResponse(
        val titulo: String? = null,
        val descricao: String? = null,
        val localizacao: LocalizacaoWrapperResponse? = null,
        val imagemCapa: String?,
        val imagens: ArrayList<String>? = null,
    ) {
        data class LocalizacaoWrapperResponse(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
        )
    }
}
