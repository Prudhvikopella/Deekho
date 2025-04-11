package com.deekho.app.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.deekho.app.R
import com.deekho.app.databinding.ActivityAnimeDetailsBinding
import com.deekho.app.ui.adapter.AlternateNameAdapter
import com.deekho.app.ui.adapter.InfoPagerAdapter
import com.deekho.app.ui.viewmodel.CommonViewModel
import com.deekho.app.utils.Status
import com.deekho.app.utils.ViewExtensions.formatTitleValue
import com.deekho.app.utils.ViewExtensions.loadImage
import com.deekho.app.utils.ViewExtensions.setLayoutEdgeTop
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class ActivityAnimeDetails : AppCompatActivity() {

    private val binding : ActivityAnimeDetailsBinding by lazy {
        ActivityAnimeDetailsBinding.inflate(layoutInflater)
    }
    var isExpanded = false
    private val commonViewModel : CommonViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setLayoutEdgeTop(binding.container)
        val mal_id = intent.getStringExtra("mal_id")
        commonViewModel.getAnimeDetailsResponse().observe(this){
            when(it.status){
                Status.SUCCESS -> {
                    binding.ivThumbnail.loadImage(it.data!!.data.images.jpg.large_image_url , R.drawable.ic_approved)
                    binding.textTitle.text = it.data.data.title
                    if(it.data.data.trailer?.youtube_id != null){
                        binding.ivThumbnail.loadImage(it.data.data.trailer.images?.maximum_image_url, R.drawable.ic_no_video)
                    }
                    var synoyms = ""
                    val alternateNames = ArrayList<String>()
                    val heads = ArrayList<String>()
                    for(i in it.data.data.titles){
                        if(i.type.equals("Synonym")){
                            if(synoyms.equals("")) {
                                synoyms = i.title
                            }else {
                                synoyms = synoyms + ", ${i.title}"
                            }
                        }else{
                            alternateNames.add(formatTitleValue(i.type , i.title).toString())
                        }

                    }

                    alternateNames.add(formatTitleValue("Synonym" , synoyms).toString())
                    val adapter = AlternateNameAdapter(this, alternateNames)
                    binding.lvAlternateNames.adapter = adapter
                    setUpTabs()
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
        commonViewModel.getAnimeDetails(mal_id!!.toInt())



        binding.textToggle.setOnClickListener {
            isExpanded = !isExpanded

            binding.alternateNamesLayout.visibility =
                if (isExpanded) View.VISIBLE else View.GONE

            binding.textToggle.text =
                if (isExpanded) "Hide Alternate Names ⬆️"
                else "Show Alternate Names 🔽"
        }
    }

    private fun setUpTabs() {
        val tabLayout = binding.tbTabs
        val viewPager = binding.vpInfo
        val headings = listOf("Details" , "Crew")
        // Setup ViewPager Adapter
        val adapter = InfoPagerAdapter(this, headings)
        viewPager.adapter = adapter

        // Attach ViewPager with TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = headings[position]
        }.attach()
    }

}