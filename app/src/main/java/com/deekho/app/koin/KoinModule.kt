package com.deekho.app.koin

import com.deekho.app.ui.repo.CommonRepo
import com.deekho.app.ui.viewmodel.CommonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
factory{CommonRepo()}
}

val viewModelModule = module {
viewModel{CommonViewModel()}
}