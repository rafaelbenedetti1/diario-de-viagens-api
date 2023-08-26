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
        val viagemOpt = usuarioViagemRepository.findUsuarioViagemById(request.id)
        return if (viagemOpt.isPresent) {
            val existe = viagemOpt.get().viagens?.find { it.id == request.viagem?.id }
            if (existe == null) {
                viagemOpt.get().viagens!!.add(
                    UsuarioViagem.Viagem(
                        titulo = request.viagem?.titulo,
                        descricao = request.viagem?.descricao,
                        localizacao = usuarioViagemRequestToModelMapper.mapLocalizacaoRequestToModel(request.viagem?.localizacao),
                        imagemCapa = request.viagem?.imagemCapa,
                        imagens = request.viagem?.imagens
                    )
                )
            } else {
                // se já existir, realiza o update nos campos alterados
                existe.titulo = request.viagem?.titulo
                existe.descricao = request.viagem?.descricao
                existe.localizacao = usuarioViagemRequestToModelMapper.mapLocalizacaoRequestToModel(request.viagem?.localizacao)
                existe.imagemCapa = request.viagem?.imagemCapa
                existe.imagens = request.viagem?.imagens
            }
            ApiResponse(
                usuarioViagemModelToResponse.map(
                    usuarioViagemRepository.save(
                        viagemOpt.get().copy(viagens = viagemOpt.get().viagens)
                    )
                )
            )
        } else {
            ApiResponse(
                usuarioViagemModelToResponse.map(
                    usuarioViagemRepository.save(
                        usuarioViagemRequestToModelMapper.map(
                            request
                        )
                    )
                )
            )
        }
    }
}