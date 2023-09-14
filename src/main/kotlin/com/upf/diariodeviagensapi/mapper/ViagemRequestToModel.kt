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
                    titulo = viagemWrapperRequest.titulo,
                    descricao = viagemWrapperRequest.descricao,
                    localizacao = mapLocalizacaoRequestToModel(viagemWrapperRequest.localizacao),
                    imagemCapa = viagemWrapperRequest.imagemCapa,
                    imagens = mapImagemRequestToModel(viagemWrapperRequest.imagens),
                    dataInicio = viagemWrapperRequest.dataInicio,
                    dataFim = viagemWrapperRequest.dataFim,
                    visitas = mapVisitasRequestToModel(viagemWrapperRequest.visitas)
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
            bairro = localizacaoRequest?.bairro,
            rua = localizacaoRequest?.rua
        )
    }

    fun mapImagemRequestToModel(imagensRequest: List<ViagemWrapperRequest.ViagemWrapperRequest.ImagemWrapperRequest>?): ArrayList<UsuarioViagem.Viagem.Imagem> {
        val listaImagens: ArrayList<UsuarioViagem.Viagem.Imagem> = arrayListOf()
        imagensRequest?.forEach {
            listaImagens.add(
                UsuarioViagem.Viagem.Imagem(
                    arquivo = it.arquivo,
                    localizacao = mapLocalizacaoRequestToModel(it.localizacao),
                    data = it.data
                )
            )
        }
        return listaImagens
    }

    fun mapVisitasRequestToModel(visitasRequest: List<ViagemWrapperRequest.ViagemWrapperRequest.VisitaWrapperRequest>?): ArrayList<UsuarioViagem.Viagem.Visita> {
        val listaVisitas: ArrayList<UsuarioViagem.Viagem.Visita> = arrayListOf()
        visitasRequest?.forEach {
            listaVisitas.add(
                UsuarioViagem.Viagem.Visita(
                    nomeLocal = it.nomeLocal,
                    localizacao = mapLocalizacaoRequestToModel(it.localizacao),
                    data = it.data,
                    imagens = it.imagens
                )
            )
        }
        return listaVisitas
    }
}