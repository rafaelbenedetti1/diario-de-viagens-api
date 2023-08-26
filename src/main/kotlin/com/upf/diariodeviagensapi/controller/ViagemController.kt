package com.upf.diariodeviagensapi.controller

import com.upf.diariodeviagensapi.service.ViagemService
import com.upf.diariodeviagensapi.wrappers.request.ViagemWrapperRequest
import com.upf.diariodeviagensapi.wrappers.response.ApiResponse
import com.upf.diariodeviagensapi.wrappers.response.ViagemWrapperResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/viagem")
class ViagemController(private val viagemService: ViagemService) {


    @GetMapping("/{usuario}")
    fun findAll(@PathVariable usuario: String): ApiResponse<ViagemWrapperResponse> {
       return viagemService.findAllByUsuario(usuario)
    }

    @PostMapping
    fun insertViagem(@RequestBody viagemWrapperRequest: ViagemWrapperRequest): ApiResponse<ViagemWrapperResponse> {
        return viagemService.insertViagem(viagemWrapperRequest)
    }
}