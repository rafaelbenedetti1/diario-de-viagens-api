package com.upf.diariodeviagensapi.wrappers.response

import java.time.LocalDate

data class ViagemWrapperResponse(
    val id: String? = null,

    val usuario: String,

    val viagens: List<ViagemWrapperResponse>
) {
    data class ViagemWrapperResponse(
        val localizacao: LocalizacaoWrapperResponse? = null,
        val imagemCapa: String?,
        val imagens: ArrayList<String>? = null,
        var visitas: ArrayList<VisitaWrapperResponse>? = null,
        val dataInicio: LocalDate? = null,
        val dataFim: LocalDate? = null
    ) {
        data class LocalizacaoWrapperResponse(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
        )

        data class VisitaWrapperResponse(
            val nomeLocal: String? = null,
            val imagens: ArrayList<String>? = null,
            val localizacao: LocalizacaoWrapperResponse? = null,
            val data: LocalDate? = null,
        )
    }
}
