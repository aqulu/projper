package lu.aqu.core.support

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()

    object Loading : Resource<Nothing>()

    class Error(val throwable: Throwable) : Resource<Nothing>()
}
