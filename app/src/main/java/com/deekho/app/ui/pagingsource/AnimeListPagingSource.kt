package com.deekho.app.ui.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.deekho.app.ui.interfaces.ApiInterface
import com.deekho.app.ui.model.Anime
import retrofit2.HttpException
import java.io.IOException

class AnimeListPagingSource(
    private val api: ApiInterface
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: 1
        return try {
            val response = api.hitGetAnimeList(page)

            val data = response.body()?.data ?: emptyList()
            val hasNext = response.body()?.pagination?.has_next_page == true
            Log.e("CHECKDATA" , data.toString())
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (hasNext) page + 1 else null
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
