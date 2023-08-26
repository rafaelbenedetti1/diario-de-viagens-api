package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.Viagem
import com.upf.diariodeviagensapi.wrappers.request.ViagemWrapperRequest
import org.springframework.stereotype.Component

@Component
class ViagemRequestToModel : Mapper<ViagemWrapperRequest, Viagem> {

    override fun map(t: ViagemWrapperRequest): Viagem {
        return Viagem(
            usuario = t.usuario,
            viagens = mapViagensRequestToModel(t.viagem)
        )
    }

    fun mapViagensRequestToModel(viagemWrapperRequest: ViagemWrapperRequest.ViagemWrapperRequest?): ArrayList<Viagem.Viagem> {
        val lista: ArrayList<Viagem.Viagem> = arrayListOf()
        if (viagemWrapperRequest != null) {
            lista.add(
                Viagem.Viagem(
                    titulo = viagemWrapperRequest.titulo,
                    descricao = viagemWrapperRequest.descricao,
                    localizacao = mapLocalizacaoRequestToModel(viagemWrapperRequest.localizacao),
                    imagemCapa = viagemWrapperRequest.imagemCapa,
                    imagens = viagemWrapperRequest.imagens
                )
            )
        }


        return lista
    }

    fun mapLocalizacaoRequestToModel(localizacaoRequest: ViagemWrapperRequest.ViagemWrapperRequest.LocalizacaoWrapperRequest?): Viagem.Viagem.Localizacao {
        return Viagem.Viagem.Localizacao(
            cidade = localizacaoRequest?.cidade,
            estado = localizacaoRequest?.estado,
            pais = localizacaoRequest?.pais
        )
    }
}