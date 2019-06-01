package lu.aqu.projper.project.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.FindProjectByIdUseCase
import javax.inject.Inject

class DetailsViewModelFactory private constructor(
    private val projectId: Project.Id,
    private val findProjectByIdUseCase: FindProjectByIdUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            projectId,
            findProjectByIdUseCase
        ) as T
    }

    class Provider @Inject constructor(
        private val findProjectByIdUseCase: FindProjectByIdUseCase
    ) {
        fun provide(projectId: Project.Id) =
            DetailsViewModelFactory(projectId, findProjectByIdUseCase)
    }
}
