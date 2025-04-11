package com.deekho.app.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.deekho.app.databinding.ActivityHomeBinding
import com.deekho.app.network.RetrofitUtil
import com.deekho.app.ui.adapter.AnimeListAdapter
import com.deekho.app.ui.interfaces.AnimeItemClickListener
import com.deekho.app.ui.viewmodel.CommonViewModel
import com.deekho.app.utils.ViewExtensions.setLayoutEdgeBottom
import com.deekho.app.utils.ViewExtensions.setLayoutEdgeTop
import com.deekho.app.utils.ViewExtensions.switchActivityWithIntent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ActivityHome : AppCompatActivity() , AnimeItemClickListener {

    private val binding : ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val commonViewModel : CommonViewModel by inject()
    private val paginAdapter : AnimeListAdapter by lazy {
        AnimeListAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setLayoutEdgeTop(binding.tvHome)
        setLayoutEdgeBottom(binding.rvAnimeList)
        binding.rvAnimeList.layoutManager = GridLayoutManager(this@ActivityHome , 3)
        binding.rvAnimeList.adapter = paginAdapter
        lifecycleScope.launch {
            commonViewModel.getAnimeList(
                RetrofitUtil.createBaseApiService(),
            ).collectLatest { pagingData ->
                paginAdapter.submitData(pagingData)
            }
        }
    }

    override fun onItemClickec(mal_id: Int) {
        switchActivityWithIntent(ActivityAnimeDetails()){
            putExtra("mal_id" , mal_id.toString())
        }
    }
}