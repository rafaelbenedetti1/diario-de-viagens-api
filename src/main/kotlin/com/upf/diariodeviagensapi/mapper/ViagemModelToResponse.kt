package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.Viagem
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.stereotype.Component

@Component
class ViagemModelToResponse: Mapper<Viagem, ViagemWrapperResponse> {
    override fun map(t: Viagem): ViagemWrapperResponse {
        return ViagemWrapperResponse(
            id = t.id,
            usuario = t.usuario,
            viagens = mapViagensToResponse(t.viagens)
        )
    }

    fun mapViagensToResponse(viagens: ArrayList<Viagem.Viagem>?): ArrayList<ViagemWrapperResponse.ViagemWrapperResponse> {
        val lista: ArrayList<ViagemWrapperResponse.ViagemWrapperResponse> = arrayListOf()
        viagens?.forEach {
                lista.add(ViagemWrapperResponse.ViagemWrapperResponse(
                    titulo = it.titulo,
                    descricao = it.descricao,
                    localizacao = mapLocalizacaoToResponse(it.localizacao),
                    imagemCapa = it.imagemCapa,
                    imagens = it.imagens
                ))
            }
        return lista
    }

    fun mapLocalizacaoToResponse(localizacao: Viagem.Viagem.Localizacao?): ViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse {
        return ViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse(
            cidade = localizacao?.cidade,
            estado = localizacao?.estado,
            pais = localizacao?.pais
        )
    }
}