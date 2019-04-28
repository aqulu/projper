package lu.aqu.projper.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

class OverviewViewModel private constructor(
    findProjectsUseCase: FindProjectsUseCase
) : ViewModel() {

    val projects: LiveData<List<Project>> = MutableLiveData<List<Project>>().apply {
        // TODO handle asynchronously
        value = findProjectsUseCase.execute()
    }

    class Factory @Inject constructor(
        private val findProjectsUseCase: FindProjectsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OverviewViewModel(findProjectsUseCase) as T
        }
    }
}
