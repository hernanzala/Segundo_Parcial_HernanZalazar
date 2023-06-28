package com.example.segundo_parcial

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getFrase(@Url url: String): Response<ChuckNorris>

}

