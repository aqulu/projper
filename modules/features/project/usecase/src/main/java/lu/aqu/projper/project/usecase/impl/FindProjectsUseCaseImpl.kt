package lu.aqu.projper.project.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

internal class FindProjectsUseCaseImpl @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : FindProjectsUseCase {

    override fun execute(
        onLoading: () -> Unit,
        onResult: (List<Project>) -> Unit,
        onError: (Throwable) -> Unit,
        onFinished: () -> Unit
    ) {
        CoroutineScope(mainDispatcher).launch {
            onLoading()

            try {
                val result = withContext(ioDispatcher) {
                    projectRepository.findAll()
                }
                onResult(result)
            } catch (exception: Exception) {
                onError(exception)
            }

            onFinished()
        }
    }
}
