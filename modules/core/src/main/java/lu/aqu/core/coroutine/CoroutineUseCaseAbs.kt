package lu.aqu.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class CoroutineUseCaseAbs<T>(
    private val mainDispatcher: CoroutineDispatcher,
    private val workDispatcher: CoroutineDispatcher
) : CoroutineUseCase<T> {

    override fun execute(
        onLoading: () -> Unit,
        onResult: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onFinished: () -> Unit
    ) {
        // TODO handle cancellation; create Job for better handling
        CoroutineScope(mainDispatcher).launch {
            onLoading()

            try {
                val result = withContext(workDispatcher) {
                    executeAsync()
                }
                onResult(result)
            } catch (exception: Exception) {
                onError(exception)
            }

            onFinished()
        }
    }

    protected abstract suspend fun executeAsync(): T
}