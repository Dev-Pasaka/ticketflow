package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto.TemplatesDtoItem
import com.unbuniworks.camusat.efiber.domain.repository.TemplateRepository
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TemplateRepositoryImpl(private val client: HttpClient = HttpClient): TemplateRepository {
    override suspend fun getTemplates()
    : List<TemplatesDtoItem>  = try {
        client.client.get("${client.baseUrl}template/component").body<List<TemplatesDtoItem>>()
    }catch (e:Exception){
        emptyList()
    }

}

suspend fun main(){
    println(
        TemplateRepositoryImpl().getTemplates()
    )
}