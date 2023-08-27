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
                    imagens = mapImagemRequestToModel(viagemWrapperRequest.imagens),
                    dataInicio = viagemWrapperRequest.dataInicio,
                    dataFim = viagemWrapperRequest.dataFim,
                    visitas = mapVisitasRequestToModel(viagemWrapperRequest.visitas)
                )
            )
        }


        return lista
    }

    fun mapLocalizacaoRequestToModel(localizacaoRequest: ViagemWrapperRequest.ViagemWrapperRequest.LocalizacaoWrapperRequest?): Viagem.Viagem.Localizacao {
        return Viagem.Viagem.Localizacao(
            cidade = localizacaoRequest?.cidade,
            estado = localizacaoRequest?.estado,
            pais = localizacaoRequest?.pais,
            bairro = localizacaoRequest?.bairro,
            rua = localizacaoRequest?.rua
        )
    }

    fun mapImagemRequestToModel(imagensRequest: List<ViagemWrapperRequest.ViagemWrapperRequest.ImagemWrapperRequest>?): ArrayList<Viagem.Viagem.Imagem> {
        val listaImagens: ArrayList<Viagem.Viagem.Imagem> = arrayListOf()
        imagensRequest?.forEach {
            listaImagens.add(
                Viagem.Viagem.Imagem(
                    arquivo = it.arquivo,
                    localizacao = mapLocalizacaoRequestToModel(it.localizacao),
                    data = it.data
                )
            )
        }
        return listaImagens
    }

    fun mapVisitasRequestToModel(visitasRequest: List<ViagemWrapperRequest.ViagemWrapperRequest.VisitaWrapperRequest>?): ArrayList<Viagem.Viagem.Visita> {
        val listaVisitas: ArrayList<Viagem.Viagem.Visita> = arrayListOf()
        visitasRequest?.forEach {
            listaVisitas.add(
                Viagem.Viagem.Visita(
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