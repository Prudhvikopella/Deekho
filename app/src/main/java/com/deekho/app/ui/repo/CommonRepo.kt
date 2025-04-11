package com.deekho.app.ui.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.deekho.app.network.RetrofitUtil
import com.deekho.app.ui.model.Anime
import com.deekho.app.ui.model.AnimeDetailsResponse
import com.deekho.app.utils.Coroutines
import com.deekho.app.utils.Resource

class CommonRepo {

    val animeDetailsResponse: MutableLiveData<Resource<AnimeDetailsResponse>> = MutableLiveData()

    fun hitGetAnimeDetails(malId : Int){
        Coroutines.main {
            try {
                animeDetailsResponse.postValue(Resource.loading(null))
                val response = RetrofitUtil.createBaseApiService().hitGetAnimeDetails(malId)
                Log.e("CHECKINGdATA", response.body().toString())
                animeDetailsResponse.postValue(Resource.success(response.body()))
            }catch (ex : Exception){
                Log.e("CHECKINGdATA","error $ex" )
                animeDetailsResponse.postValue(Resource.error(ex, null))
            }

        }
    }
}