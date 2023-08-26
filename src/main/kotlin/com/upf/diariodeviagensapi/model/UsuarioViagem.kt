package com.upf.diariodeviagensapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList

@Document("usuarioviagem")
data class UsuarioViagem(
    @Id
    @Field("_id")
    val id: String? = UUID.randomUUID().toString(),
    var usuario: String,
    @Field
    var viagens: ArrayList<Viagem>?
) {
    data class Viagem(
        @Id
        @Field("_id")
        val id: String = UUID.randomUUID().toString(),
        @Field
        var titulo: String? = null,
        @Field
        var descricao: String? = null,
        @Field
        var localizacao: Localizacao? = null,
        @Field
        var imagemCapa: String? = null,
        @Field
        var imagens: ArrayList<String>? = null,
    ) {
        data class Localizacao(
            @Field
            val cidade: String? = null,
            @Field
            val estado: String? = null,
            @Field
            val pais: String? = null,
        )
    }
}

