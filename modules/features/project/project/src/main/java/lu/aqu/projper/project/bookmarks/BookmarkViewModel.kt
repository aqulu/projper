package lu.aqu.projper.project.bookmarks

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import lu.aqu.core.coroutine.ControlledRunner
import lu.aqu.core.coroutine.invoke
import lu.aqu.core.support.Resource
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.GetBookmarksUseCase
import javax.inject.Inject

class BookmarkViewModel private constructor(
    getBookmarksUseCase: GetBookmarksUseCase
) : ViewModel() {

    /**
     * getBookmarksUseCase emitting data to [mutableBookmarks]
     */
    private val loadBookmarksAction = getBookmarksUseCase
        .onLoading { mutableBookmarks.value = Resource.Loading }
        .onError { mutableBookmarks.value = Resource.Error(it) }
        .onResult { mutableBookmarks.value = Resource.Success(it) }

    private val controlledRunner = ControlledRunner<Unit>()

    private val mutableBookmarks = MutableLiveData<Resource<List<Project>>>()
    val bookmarks: LiveData<Resource<List<Project>>> = mutableBookmarks

    /**
     * triggers load of bookmarks for the current user, the result of which is emitted
     * to [bookmarks] and returned as the value of this function
     * @return bookmarks LiveData
     */
    fun load(): LiveData<Resource<List<Project>>> {
        mutableBookmarks.value = Resource.Loading

        viewModelScope.launch {
            controlledRunner.joinPreviousOrRun {
                loadBookmarksAction.invoke()
            }
        }
        return bookmarks
    }

    class Factory @Inject constructor(
        private val getBookmarksUseCase: GetBookmarksUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BookmarkViewModel(getBookmarksUseCase) as T
        }
    }
}
