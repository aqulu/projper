package lu.aqu.projper.project.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import lu.aqu.core.support.Resource
import lu.aqu.projper.extensions.asLiveData
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

class OverviewViewModel private constructor(
    findProjectsUseCase: FindProjectsUseCase
) : ViewModel() {

    val projects: LiveData<Resource<List<Project>>> =
        findProjectsUseCase.asLiveData(viewModelScope)

    class Factory @Inject constructor(
        private val findProjectsUseCase: FindProjectsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OverviewViewModel(findProjectsUseCase) as T
        }
    }
}
