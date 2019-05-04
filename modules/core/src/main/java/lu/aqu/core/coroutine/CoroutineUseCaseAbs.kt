package lu.aqu.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.cancelChildren

abstract class CoroutineUseCaseAbs<T>(
    private val mainDispatcher: CoroutineDispatcher,
    private val workDispatcher: CoroutineDispatcher
) : CoroutineUseCase<T> {

    private var job: Job? = null

    override fun execute(
        onLoading: () -> Unit,
        onResult: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onFinished: () -> Unit
    ) {
        unsubscribe()

        job = CoroutineScope(mainDispatcher).launch {
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

    override fun unsubscribe() {
        job?.run {
            cancelChildren()
            cancel()
        }
        job = null
    }
}
