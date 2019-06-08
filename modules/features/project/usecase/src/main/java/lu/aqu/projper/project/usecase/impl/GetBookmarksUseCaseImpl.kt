package lu.aqu.projper.project.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.CoroutineUseCaseAbs
import lu.aqu.projper.project.domain.BookmarkRepository
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.GetBookmarksUseCase
import javax.inject.Inject

internal class GetBookmarksUseCaseImpl @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCaseAbs<List<Project>>(mainDispatcher, ioDispatcher), GetBookmarksUseCase {

    override suspend fun doInBackground(): List<Project> =
        bookmarkRepository.findAll()
}
