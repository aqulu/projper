package lu.aqu.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCaseWithParameterAbs<ParamT, ResultT>(
    private val mainDispatcher: CoroutineDispatcher,
    private val workDispatcher: CoroutineDispatcher
) : CoroutineUseCaseWithParameter<ParamT, ResultT> {

    private var onLoading: (() -> Unit)? = null
    private var onResult: ((ResultT) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null
    private var onFinished: (() -> Unit)? = null

    override suspend fun invoke(param: ParamT) {
        withContext(mainDispatcher) {
            onLoading?.invoke()

            try {
                val result = withContext(workDispatcher) {
                    doInBackground(param)
                }
                onResult?.invoke(result)
            } catch (exception: Exception) {
                onError?.invoke(exception)
            }

            onFinished?.invoke()
        }
    }

    abstract suspend fun doInBackground(param: ParamT): ResultT

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
