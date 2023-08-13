package com.upf.diariodeviagensapi.repository

import com.upf.diariodeviagensapi.model.UsuarioViagem
import com.upf.diariodeviagensapi.wrappers.response.UsuarioViagemWrapperResponse
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioViagemRepository: MongoRepository<UsuarioViagem, String> {

    fun findUsuarioViagemById(id: String?): Optional<UsuarioViagem>

    fun findByViagensId(id: String?): Optional<UsuarioViagem>

    fun findUsuarioViagemByUsuario(usuario: String): UsuarioViagemWrapperResponse
}