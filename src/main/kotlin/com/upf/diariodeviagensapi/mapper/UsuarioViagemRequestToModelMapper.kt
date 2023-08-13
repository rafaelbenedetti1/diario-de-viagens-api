package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.request.UsuarioViagemWrapperRequest
import org.springframework.stereotype.Component

@Component
class UsuarioViagemRequestToModelMapper : Mapper<UsuarioViagemWrapperRequest, UsuarioViagem> {

    override fun map(t: UsuarioViagemWrapperRequest): UsuarioViagem {
        return UsuarioViagem(
            id = t.id!!,
            usuario = t.usuario,
            viagens = mapViagensRequestToModel(t.viagem)
        )
    }

    fun mapViagensRequestToModel(viagemWrapperRequest: UsuarioViagemWrapperRequest.ViagemWrapperRequest?): ArrayList<UsuarioViagem.Viagem> {
        val lista: ArrayList<UsuarioViagem.Viagem> = arrayListOf()
        if (viagemWrapperRequest != null) {
            lista.add(
                UsuarioViagem.Viagem(
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

    fun mapLocalizacaoRequestToModel(localizacaoRequest: UsuarioViagemWrapperRequest.ViagemWrapperRequest.LocalizacaoWrapperRequest?): UsuarioViagem.Viagem.Localizacao {
        return UsuarioViagem.Viagem.Localizacao(
            cidade = localizacaoRequest?.cidade,
            estado = localizacaoRequest?.estado,
            pais = localizacaoRequest?.pais
        )
    }
}