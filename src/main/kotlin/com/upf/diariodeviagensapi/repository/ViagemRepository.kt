package com.upf.diariodeviagensapi.repository

import com.upf.diariodeviagensapi.model.Viagem
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ViagemRepository: MongoRepository<Viagem, String> {

    fun findUsuarioViagemById(id: String?): Optional<Viagem>

    fun findByViagensId(id: String?): Optional<Viagem>

    fun findUsuarioViagemByUsuario(usuario: String): ViagemWrapperResponse
}