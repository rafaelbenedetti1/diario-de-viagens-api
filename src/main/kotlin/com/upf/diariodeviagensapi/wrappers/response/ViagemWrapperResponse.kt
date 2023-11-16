package com.upf.diariodeviagensapi.wrappers.response

import java.time.LocalDate

data class ViagemWrapperResponse(
    val id: String? = null,

    val usuario: String,

    val viagens: List<ViagemWrapperResponse>
) {
    data class ViagemWrapperResponse(
        val id: String,
        val localizacao: LocalizacaoWrapperResponse? = null,
        val imagemCapa: String?,
        val imagens: ArrayList<String>? = null,
        var visitas: ArrayList<VisitaWrapperResponse>? = null,
        val dataInicio: String? = null,
        val dataFim: String? = null,
        val avaliacao: Double? = null
    ) {
        data class LocalizacaoWrapperResponse(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
        )

        data class VisitaWrapperResponse(
            val nomeLocal: String? = null,
            val imagens: String? = null,
            val data: String? = null,
        )
    }
}
