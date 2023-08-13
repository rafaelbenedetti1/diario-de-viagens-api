package com.upf.diariodeviagensapi.service

import com.upf.diariodeviagensapi.mapper.UsuarioViagemModelToResponse
import com.upf.diariodeviagensapi.mapper.UsuarioViagemRequestToModelMapper
import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.repository.UsuarioViagemRepository
import com.upf.diariodeviagensapi.wrappers.request.UsuarioViagemWrapperRequest
import com.upf.diariodeviagensapi.wrappers.response.ApiResponse
import com.upf.diariodeviagensapi.wrappers.response.UsuarioViagemWrapperResponse
import org.springframework.stereotype.Service

@Service
class UsuarioViagemService(
    private val usuarioViagemRepository: UsuarioViagemRepository,
    private val usuarioViagemRequestToModelMapper: UsuarioViagemRequestToModelMapper,
    private val usuarioViagemModelToResponse: UsuarioViagemModelToResponse
) {

    fun findAllByUsuario(usuario: String): ApiResponse<UsuarioViagemWrapperResponse> {
        val retorno = usuarioViagemRepository.findUsuarioViagemByUsuario(usuario)
        return ApiResponse(data = retorno)
    }

    fun insertUsuario(request: UsuarioViagemWrapperRequest): ApiResponse<UsuarioViagemWrapperResponse> {
        val usuarioOpt = usuarioViagemRepository.findUsuarioViagemById(request.id)
        return if (usuarioOpt.isEmpty) {
            ApiResponse(
                data = usuarioViagemModelToResponse.map(
                    usuarioViagemRepository.save(
                        usuarioViagemRequestToModelMapper.map(request)
                    )
                )
            )
        } else {
            val usuarioModificado = usuarioOpt.get().apply {
                usuario = request.usuario
            }
            ApiResponse(data = usuarioViagemModelToResponse.map(usuarioViagemRepository.save(usuarioModificado)))
        }
    }

    fun insertViagem(request: UsuarioViagemWrapperRequest): ApiResponse<UsuarioViagemWrapperResponse> {
        // consulta na base para ver se a viagem já existe baseado no viagem id
        val viagemOpt = usuarioViagemRepository.findByViagensId(request.viagem?.id)
        // se não existir, insere a nova viagem
        return if (viagemOpt.isEmpty) {
            ApiResponse(
                usuarioViagemModelToResponse.map(
                    usuarioViagemRepository.save(
                        usuarioViagemRequestToModelMapper.map(
                            request
                        )
                    )
                )
            )
        } else {
            // se já existir, realiza o update nos campos alterados
            viagemOpt.get().viagens?.forEach {
                if(it.id == request.id) {
                    it.titulo = request.viagem?.titulo
                    it.descricao = request.viagem?.descricao
                    it.imagemCapa = request.viagem?.imagemCapa
                    it.imagens = request.viagem?.imagens
                }
            }
            ApiResponse(usuarioViagemModelToResponse.map(usuarioViagemRepository.save(viagemOpt.get())))
        }
    }
}