package com.upf.diariodeviagensapi.service

import com.upf.diariodeviagensapi.mapper.ViagemModelToResponse
import com.upf.diariodeviagensapi.mapper.ViagemRequestToModel
import com.upf.diariodeviagensapi.model.Viagem
import com.upf.diariodeviagensapi.repository.ViagemRepository
import com.upf.diariodeviagensapi.wrappers.request.ViagemWrapperRequest
import com.upf.diariodeviagensapi.wrappers.response.ApiResponse
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.stereotype.Service

@Service
class ViagemService(
    private val viagemRepository: ViagemRepository,
    private val viagemRequestToModel: ViagemRequestToModel,
    private val viagemModelToResponse: ViagemModelToResponse
) {

    fun findAllByUsuario(usuario: String): ApiResponse<ViagemWrapperResponse> {
        val retorno = viagemRepository.findUsuarioViagemByUsuario(usuario)
        return ApiResponse(data = retorno)
    }

    fun insertViagem(request: ViagemWrapperRequest): ApiResponse<ViagemWrapperResponse> {
        // consulta na base para ver se a viagem já existe baseado no viagem id
        val viagemOpt = viagemRepository.findUsuarioViagemById(request.id)
        return if (viagemOpt.isPresent) {
            val viagemExistente = viagemOpt.get().viagens?.find { it.id == request.viagem?.id }
            if (viagemExistente == null) {
                viagemOpt.get().viagens!!.add(
                    Viagem.Viagem(
                        titulo = request.viagem?.titulo,
                        descricao = request.viagem?.descricao,
                        localizacao = viagemRequestToModel.mapLocalizacaoRequestToModel(request.viagem?.localizacao),
                        imagemCapa = request.viagem?.imagemCapa,
                        imagens = viagemRequestToModel.mapImagemRequestToModel(request.viagem?.imagens),
                        dataInicio = request.viagem?.dataInicio,
                        dataFim = request.viagem?.dataFim,
                        visitas = viagemRequestToModel.mapVisitasRequestToModel(request.viagem?.visitas)
                    )
                )
            } else {
                // se já existir, realiza o update nos campos alterados
                viagemExistente.titulo = request.viagem?.titulo
                viagemExistente.descricao = request.viagem?.descricao
                viagemExistente.localizacao = viagemRequestToModel.mapLocalizacaoRequestToModel(request.viagem?.localizacao)
                viagemExistente.imagemCapa = request.viagem?.imagemCapa
                viagemExistente.imagens = viagemRequestToModel.mapImagemRequestToModel(request.viagem?.imagens)
                viagemExistente.dataInicio = request.viagem?.dataInicio
                viagemExistente.dataFim = request.viagem?.dataFim
                viagemExistente.visitas = viagemRequestToModel.mapVisitasRequestToModel(request.viagem?.visitas)
            }
            ApiResponse(
                viagemModelToResponse.map(
                    viagemRepository.save(
                        viagemOpt.get().copy(viagens = viagemOpt.get().viagens)
                    )
                )
            )
        } else {
            ApiResponse(
                viagemModelToResponse.map(
                    viagemRepository.save(
                        viagemRequestToModel.map(
                            request
                        )
                    )
                )
            )
        }
    }
}