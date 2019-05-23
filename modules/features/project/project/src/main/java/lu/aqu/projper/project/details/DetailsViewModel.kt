package lu.aqu.projper.project.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lu.aqu.core.support.Resource
import lu.aqu.projper.extensions.asLiveData
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.FindProjectByIdUseCase
import javax.inject.Inject

class DetailsViewModel private constructor(
    val project: LiveData<Resource<Project>>
) : ViewModel() {

    class Factory @Inject constructor(
        private val projectId: Project.Id,
        private val findProjectByIdUseCase: FindProjectByIdUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(
                findProjectByIdUseCase.asLiveData(projectId)
            ) as T
        }
    }
}
