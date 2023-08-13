package com.upf.diariodeviagensapi.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
