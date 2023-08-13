package com.upf.diariodeviagensapi.controller

import com.upf.diariodeviagensapi.service.UsuarioViagemService
import com.upf.diariodeviagensapi.wrappers.request.UsuarioViagemWrapperRequest
import com.upf.diariodeviagensapi.wrappers.response.ApiResponse
import com.upf.diariodeviagensapi.wrappers.response.UsuarioViagemWrapperResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/viagem")
class UsuarioViagemController(private val usuarioViagemService: UsuarioViagemService) {


    @GetMapping("/{usuario}")
    fun findAll(@PathVariable usuario: String): ApiResponse<UsuarioViagemWrapperResponse> {
       return usuarioViagemService.findAllByUsuario(usuario)
    }

    @PostMapping("/insertUsuario")
    fun insertUsuario(@RequestBody usuarioViagemWrapperRequest: UsuarioViagemWrapperRequest): ApiResponse<UsuarioViagemWrapperResponse> {
        return usuarioViagemService.insertUsuario(usuarioViagemWrapperRequest)
    }

    @PostMapping("/insertViagem")
    fun insertViagem(@RequestBody usuarioViagemWrapperRequest: UsuarioViagemWrapperRequest): ApiResponse<UsuarioViagemWrapperResponse> {
        return usuarioViagemService.insertViagem(usuarioViagemWrapperRequest)
    }
}