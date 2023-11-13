package com.upf.diariodeviagensapi.wrappers.request

import java.time.LocalDate

data class ViagemWrapperRequest(

    val id: String? = null,
    val usuario: String,
    val viagem: ViagemWrapperRequest?
) {
    data class ViagemWrapperRequest(
        val id: String? = null,
        val localizacao: LocalizacaoWrapperRequest? = null,
        val imagemCapa: String? = null,
        val imagens: ArrayList<String>? = null,
        var visitas: ArrayList<VisitaWrapperRequest>? = null,
        val dataInicio: String? = null,
        val dataFim: String? = null,
        val avaliacao: Double? = null
        ) {
        data class LocalizacaoWrapperRequest(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
        )

        data class VisitaWrapperRequest(
            val nomeLocal: String? = null,
            val imagens: String? = null,
            val data: String? = null,
        )
    }
}