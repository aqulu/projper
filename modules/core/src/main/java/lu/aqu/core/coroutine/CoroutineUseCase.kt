package lu.aqu.core.coroutine

interface CoroutineUseCase<T> {

    fun execute(
        onLoading: () -> Unit = {},
        onResult: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
        onFinished: () -> Unit = {}
    )
}
