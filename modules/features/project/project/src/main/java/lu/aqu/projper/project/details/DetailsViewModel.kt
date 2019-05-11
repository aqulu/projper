package lu.aqu.projper.project.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lu.aqu.projper.project.domain.Project
import javax.inject.Inject

class DetailsViewModel private constructor(
    val projectId: Project.Id
) : ViewModel() {

    class Factory @Inject constructor(
        private val projectId: Project.Id
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(projectId) as T
        }
    }
}
