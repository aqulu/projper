package lu.aqu.projper.project.usecase

import lu.aqu.projper.project.domain.Project

interface FindProjectsUseCase {

    fun execute(
        onLoading: () -> Unit = {},
        onResult: (List<Project>) -> Unit,
        onError: (Throwable) -> Unit = {},
        onFinished: () -> Unit = {}
    )
}