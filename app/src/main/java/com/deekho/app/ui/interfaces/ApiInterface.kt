package com.deekho.app.ui.interfaces

import com.deekho.app.constants.ApiConstants
import com.deekho.app.ui.model.Anime
import com.deekho.app.ui.model.AnimeDetailsResponse
import com.deekho.app.ui.model.AnimeListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConstants.GET_ANIME_LIST)
    suspend fun hitGetAnimeList(@Query("page") page: Int = 1): Response<AnimeListResponseModel>

    @GET(ApiConstants.GET_ANIME_DETAILS)
    suspend fun hitGetAnimeDetails(@Path("anime_id") malId: Int): Response<AnimeDetailsResponse>
}