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

    private var onLoading: (() -> Unit)? = null
    private var onResult: ((T) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null
    private var onFinished: (() -> Unit)? = null

    override fun execute() {
        unsubscribe()

        job = CoroutineScope(mainDispatcher).launch {
            onLoading?.invoke()

            try {
                val result = withContext(workDispatcher) {
                    executeAsync()
                }
                onResult?.invoke(result)
            } catch (exception: Exception) {
                onError?.invoke(exception)
            }

            onFinished?.invoke()
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

    override fun onLoading(onLoading: () -> Unit) = apply {
        this.onLoading = onLoading
    }

    override fun onResult(onResult: (T) -> Unit) = apply {
        this.onResult = onResult
    }

    override fun onError(onError: (Throwable) -> Unit) = apply {
        this.onError = onError
    }

    override fun onFinished(onFinished: () -> Unit) = apply {
        this.onFinished = onFinished
    }
}
