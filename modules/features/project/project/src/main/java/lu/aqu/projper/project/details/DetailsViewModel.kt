package lu.aqu.projper.project.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import lu.aqu.core.support.Resource
import lu.aqu.projper.extensions.asLiveData
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.FindProjectByIdUseCase

class DetailsViewModel(
    projectId: Project.Id,
    findProjectByIdUseCase: FindProjectByIdUseCase
) : ViewModel() {

    val project: LiveData<Resource<Project>> =
        findProjectByIdUseCase.asLiveData(projectId, viewModelScope)
}
