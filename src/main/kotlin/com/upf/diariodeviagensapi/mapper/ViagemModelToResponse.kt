package com.upf.diariodeviagensapi.mapper

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.stereotype.Component

@Component
class ViagemModelToResponse: Mapper<UsuarioViagem, ViagemWrapperResponse> {
    override fun map(t: UsuarioViagem): ViagemWrapperResponse {
        return ViagemWrapperResponse(
            id = t.id,
            usuario = t.usuario,
            viagens = mapViagensToResponse(t.viagens)
        )
    }

    fun mapViagensToResponse(viagens: ArrayList<UsuarioViagem.Viagem>?): ArrayList<ViagemWrapperResponse.ViagemWrapperResponse> {
        val lista: ArrayList<ViagemWrapperResponse.ViagemWrapperResponse> = arrayListOf()
        viagens?.forEach {
                lista.add(ViagemWrapperResponse.ViagemWrapperResponse(
                    titulo = it.titulo,
                    descricao = it.descricao,
                    localizacao = mapLocalizacaoToResponse(it.localizacao),
                    imagemCapa = it.imagemCapa,
                    imagens = mapImagemToResponse(it.imagens),
                    dataInicio = it.dataInicio,
                    dataFim = it.dataFim,
                    visitas = mapVisitasToResponse(it.visitas)
                ))
            }
        return lista
    }

    fun mapLocalizacaoToResponse(localizacao: UsuarioViagem.Viagem.Localizacao?): ViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse {
        return ViagemWrapperResponse.ViagemWrapperResponse.LocalizacaoWrapperResponse(
            cidade = localizacao?.cidade,
            estado = localizacao?.estado,
            pais = localizacao?.pais,
            bairro = localizacao?.bairro,
            rua = localizacao?.rua
        )
    }

    fun mapImagemToResponse(imagens: List<UsuarioViagem.Viagem.Imagem>?): ArrayList<ViagemWrapperResponse.ViagemWrapperResponse.ImagemWrapperResponse> {
        val listaImagens: ArrayList<ViagemWrapperResponse.ViagemWrapperResponse.ImagemWrapperResponse> = arrayListOf()
        imagens?.forEach {
            listaImagens.add(
                ViagemWrapperResponse.ViagemWrapperResponse.ImagemWrapperResponse(
                    arquivo = it.arquivo,
                    localizacao = mapLocalizacaoToResponse(it.localizacao),
                    data = it.data
                )
            )
        }
        return listaImagens
    }

    fun mapVisitasToResponse(visitas: List<UsuarioViagem.Viagem.Visita>?): ArrayList<ViagemWrapperResponse.ViagemWrapperResponse.VisitaWrapperResponse> {
        val listaVisitas: ArrayList<ViagemWrapperResponse.ViagemWrapperResponse.VisitaWrapperResponse> = arrayListOf()
        visitas?.forEach {
            listaVisitas.add(
                ViagemWrapperResponse.ViagemWrapperResponse.VisitaWrapperResponse(
                    nomeLocal = it.nomeLocal,
                    localizacao = mapLocalizacaoToResponse(it.localizacao),
                    data = it.data,
                    imagens = it.imagens
                )
            )
        }
        return listaVisitas
    }
}