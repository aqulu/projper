package lu.aqu.core.coroutine

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class CoroutineUseCaseWithParameterAbs<ParamT, ResultT>(
    private val mainDispatcher: CoroutineDispatcher,
    private val workDispatcher: CoroutineDispatcher
) : CoroutineUseCaseWithParameter<ParamT, ResultT> {

    private var job: Job? = null

    private var onLoading: (() -> Unit)? = null
    private var onResult: ((ResultT) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null
    private var onFinished: (() -> Unit)? = null

    override fun execute(param: ParamT) {
        unsubscribe()

        job = CoroutineScope(mainDispatcher).launch {
            onLoading?.invoke()

            try {
                val result = withContext(workDispatcher) {
                    executeAsync(param)
                }
                onResult?.invoke(result)
            } catch (exception: Exception) {
                onError?.invoke(exception)
            }

            onFinished?.invoke()
        }
    }

    abstract suspend fun executeAsync(param: ParamT): ResultT

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

    override fun onResult(onResult: (ResultT) -> Unit) = apply {
        this.onResult = onResult
    }

    override fun onError(onError: (Throwable) -> Unit) = apply {
        this.onError = onError
    }

    override fun onFinished(onFinished: () -> Unit) = apply {
        this.onFinished = onFinished
    }
}