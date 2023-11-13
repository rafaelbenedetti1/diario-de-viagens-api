package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.request.ViagemWrapperRequest
import org.springframework.stereotype.Component

@Component
class ViagemRequestToModel : Mapper<ViagemWrapperRequest, UsuarioViagem> {

    override fun map(t: ViagemWrapperRequest): UsuarioViagem {
        return UsuarioViagem(
            usuario = t.usuario,
            viagens = mapViagensRequestToModel(t.viagem)
        )
    }

    fun mapViagensRequestToModel(viagemWrapperRequest: ViagemWrapperRequest.ViagemWrapperRequest?): ArrayList<UsuarioViagem.Viagem> {
        val lista: ArrayList<UsuarioViagem.Viagem> = arrayListOf()
        if (viagemWrapperRequest != null) {
            lista.add(
                UsuarioViagem.Viagem(
                    localizacao = mapLocalizacaoRequestToModel(viagemWrapperRequest.localizacao),
                    imagemCapa = viagemWrapperRequest.imagemCapa,
                    imagens = viagemWrapperRequest.imagens,
                    dataInicio = viagemWrapperRequest.dataInicio,
                    dataFim = viagemWrapperRequest.dataFim,
                    visitas = mapVisitasRequestToModel(viagemWrapperRequest.visitas),
                    avaliacao = viagemWrapperRequest.avaliacao
                )
            )
        }


        return lista
    }

    fun mapLocalizacaoRequestToModel(localizacaoRequest: ViagemWrapperRequest.ViagemWrapperRequest.LocalizacaoWrapperRequest?): UsuarioViagem.Viagem.Localizacao {
        return UsuarioViagem.Viagem.Localizacao(
            cidade = localizacaoRequest?.cidade,
            estado = localizacaoRequest?.estado,
            pais = localizacaoRequest?.pais,
        )
    }

    fun mapVisitasRequestToModel(visitasRequest: List<ViagemWrapperRequest.ViagemWrapperRequest.VisitaWrapperRequest>?): ArrayList<UsuarioViagem.Viagem.Visita> {
        val listaVisitas: ArrayList<UsuarioViagem.Viagem.Visita> = arrayListOf()
        visitasRequest?.forEach {
            listaVisitas.add(
                UsuarioViagem.Viagem.Visita(
                    nomeLocal = it.nomeLocal,
                    data = it.data,
                    imagens = it.imagens
                )
            )
        }
        return listaVisitas
    }
}