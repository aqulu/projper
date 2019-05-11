package lu.aqu.core.coroutine

interface CoroutineUseCase<T> {

    fun execute()

    fun unsubscribe()

    fun onLoading(onLoading: () -> Unit): CoroutineUseCase<T>

    fun onResult(onResult: (T) -> Unit): CoroutineUseCase<T>

    fun onError(onError: (Throwable) -> Unit): CoroutineUseCase<T>

    fun onFinished(onFinished: () -> Unit): CoroutineUseCase<T>
}
