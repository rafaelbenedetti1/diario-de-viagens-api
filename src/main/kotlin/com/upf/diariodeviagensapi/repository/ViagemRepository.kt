package com.upf.diariodeviagensapi.repository

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ViagemRepository: MongoRepository<UsuarioViagem, String> {

    fun findUsuarioViagemById(id: String?): Optional<UsuarioViagem>

    fun findByViagensId(id: String?): Optional<UsuarioViagem>

    fun findUsuarioViagemByUsuario(usuario: String): ViagemWrapperResponse
}