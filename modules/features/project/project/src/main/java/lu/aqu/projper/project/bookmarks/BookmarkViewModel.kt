package lu.aqu.projper.project.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import lu.aqu.core.support.Resource
import lu.aqu.projper.extensions.asLiveData
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.GetBookmarksUseCase
import javax.inject.Inject

class BookmarkViewModel private constructor(
    getBookmarksUseCase: GetBookmarksUseCase
) : ViewModel() {

    val bookmarks: LiveData<Resource<List<Project>>> =
        getBookmarksUseCase.asLiveData(viewModelScope)

    class Factory @Inject constructor(
        private val getBookmarksUseCase: GetBookmarksUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BookmarkViewModel(getBookmarksUseCase) as T
        }
    }
}
