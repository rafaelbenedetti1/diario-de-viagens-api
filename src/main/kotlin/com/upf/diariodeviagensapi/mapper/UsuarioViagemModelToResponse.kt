package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.request.UsuarioViagemWrapperRequest
import com.upf.diariodeviagensapi.wrappers.response.UsuarioViagemWrapperResponse
import org.springframework.stereotype.Component

@Component
class UsuarioViagemModelToResponse: Mapper<UsuarioViagem, UsuarioViagemWrapperResponse> {
    override fun map(t: UsuarioViagem): UsuarioViagemWrapperResponse {
        return UsuarioViagemWrapperResponse(
            id = t.id,
            usuario = t.usuario,
            viagens = mapViagensToResponse(t.viagens)
        )
    }

    fun mapViagensToResponse(viagens: ArrayList<UsuarioViagem.Viagem>?): ArrayList<UsuarioViagemWrapperResponse.ViagemWrapperResponse> {
        val lista: ArrayList<UsuarioViagemWrapperResponse.ViagemWrapperResponse> = arrayListOf()
        viagens?.forEach {
                lista.add(UsuarioViagemWrapperResponse.ViagemWrapperResponse(
                    titulo = it.titulo,
                    descricao = it.descricao,
                    localizacao = mapLocalizacaoToResponse(it.localizacao),
                    imagemCapa = it.imagemCapa,
                    imagens = it.imagens
                ))
            }
        return lista
    }

    fun mapLocalizacaoToResponse(localizacao: UsuarioViagem.Viagem.Localizacao?): UsuarioViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse {
        return UsuarioViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse(
            cidade = localizacao?.cidade,
            estado = localizacao?.estado,
            pais = localizacao?.pais
        )
    }
}