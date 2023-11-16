package com.upf.diariodeviagensapi.service

import com.upf.diariodeviagensapi.mapper.ViagemModelToResponse
import com.upf.diariodeviagensapi.mapper.ViagemRequestToModel
import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.repository.ViagemRepository
import com.upf.diariodeviagensapi.wrappers.request.DeleteViagemWrapperRequest
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

    fun findByViagemId(usuario: String, id: String): ApiResponse<ViagemWrapperResponse.ViagemWrapperResponse> {
        val retorno = viagemRepository.findUsuarioViagemByUsuario(usuario)

        val viagem = retorno.viagens.find { it.id == id}
        return ApiResponse(data = viagem)
    }

    fun insertViagem(request: ViagemWrapperRequest): ApiResponse<ViagemWrapperResponse> {
        // consulta na base para ver se a viagem já existe baseado no viagem id
        val viagemOpt = viagemRepository.findUsuarioViagemsByUsuario(request.usuario)
        return if (viagemOpt.isPresent) {
            val viagemExistente = viagemOpt.get().viagens.find { it.id == request.viagem?.id }
            if (viagemExistente == null) {
                viagemOpt.get().viagens.add(
                    UsuarioViagem.Viagem(
                        localizacao = viagemRequestToModel.mapLocalizacaoRequestToModel(request.viagem?.localizacao),
                        imagemCapa = request.viagem?.imagemCapa,
                        imagens = request.viagem?.imagens,
                        dataInicio = request.viagem?.dataInicio,
                        dataFim = request.viagem?.dataFim,
                        visitas = viagemRequestToModel.mapVisitasRequestToModel(request.viagem?.visitas),
                        avaliacao = request.viagem?.avaliacao
                    )
                )
            } else {
                // se já existir, realiza o update nos campos alterados
                viagemExistente.localizacao = viagemRequestToModel.mapLocalizacaoRequestToModel(request.viagem?.localizacao)
                viagemExistente.imagemCapa = request.viagem?.imagemCapa
                viagemExistente.imagens = request.viagem?.imagens
                viagemExistente.dataInicio = request.viagem?.dataInicio
                viagemExistente.dataFim = request.viagem?.dataFim
                viagemExistente.visitas = viagemRequestToModel.mapVisitasRequestToModel(request.viagem?.visitas)
                viagemExistente.avaliacao = request.viagem?.avaliacao
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

    fun deleteViagemById(body: DeleteViagemWrapperRequest): ApiResponse<Boolean> {
        val usuarioViagem = viagemRepository.findUsuarioViagemsByUsuario(body.usuarioViagemId)

        return if (usuarioViagem.isPresent) {
            val viagemDeleted = usuarioViagem.get().viagens.removeIf { it.id == body.viagemId }
            if (viagemDeleted) {
                viagemRepository.save(usuarioViagem.get()) // Atualiza a entidade no MongoDB
            }
            ApiResponse(data = viagemDeleted)
        } else {
            ApiResponse(data = false)

        }
    }
}