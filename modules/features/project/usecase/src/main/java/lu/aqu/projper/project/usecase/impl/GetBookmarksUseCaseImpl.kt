package lu.aqu.projper.project.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.CoroutineUseCaseAbs
import lu.aqu.projper.project.domain.BookmarksRepository
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.GetBookmarksUseCase
import javax.inject.Inject

internal class GetBookmarksUseCaseImpl @Inject constructor(
    private val bookmarksRepository: BookmarksRepository,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCaseAbs<List<Project>>(mainDispatcher, ioDispatcher), GetBookmarksUseCase {

    override suspend fun doInBackground(): List<Project> =
        bookmarksRepository.findAll()
}
