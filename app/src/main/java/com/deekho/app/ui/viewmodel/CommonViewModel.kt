package com.deekho.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.deekho.app.network.RetrofitUtil
import com.deekho.app.ui.interfaces.ApiInterface
import com.deekho.app.ui.model.Anime
import com.deekho.app.ui.model.AnimeDetailsResponse
import com.deekho.app.ui.pagingsource.AnimeListPagingSource
import com.deekho.app.ui.repo.CommonRepo
import com.deekho.app.utils.Resource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CommonViewModel : ViewModel()  , KoinComponent{

    private val commonRepo : CommonRepo by inject()


    fun getAnimeList(apiService : ApiInterface): Flow<PagingData<Anime>> {
        Log.e("CHECKDATA" , "CALLING COMMENCED")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimeListPagingSource(apiService) }

        ).flow.cachedIn(viewModelScope)
    }

    fun getAnimeDetails(malId : Int){
        commonRepo.hitGetAnimeDetails(malId)
    }

    fun getAnimeDetailsResponse(): MutableLiveData<Resource<AnimeDetailsResponse>> {
        return commonRepo.animeDetailsResponse
    }
}