package com.upf.diariodeviagensapi.wrappers.request

import java.time.LocalDate

data class ViagemWrapperRequest(

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
        val imagens: ArrayList<ImagemWrapperRequest>? = null,
        var visitas: ArrayList<VisitaWrapperRequest>? = null,
        val dataInicio: LocalDate? = null,
        val dataFim: LocalDate? = null,

        ) {
        data class LocalizacaoWrapperRequest(
            val cidade: String? = null,
            val estado: String? = null,
            val pais: String? = null,
            val bairro: String? = null,
            val rua: String? = null
        )

        data class ImagemWrapperRequest(
            val arquivo: String? = null,
            val localizacao: LocalizacaoWrapperRequest? = null,
            val data: LocalDate? = null,
        )

        data class VisitaWrapperRequest(
            val nomeLocal: String? = null,
            val imagens: ArrayList<String>? = null,
            val localizacao: LocalizacaoWrapperRequest? = null,
            val data: LocalDate? = null,
        )
    }
}