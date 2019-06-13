package lu.aqu.core.coroutine

/**
 * contract of a usecase that takes a parameter of type [ParamT],
 * performs work inside a coroutine and returns a result of type [ResultT]
 */
interface CoroutineUseCaseWithParameter<ParamT, ResultT> {

    /**
     * starts processing this usecases business logic
     *
     * if set, the following events will be processed as follows:
     * - [onLoading]: when invoke has been called, before actual processing start
     * - [onResult]: after background processing finished successfully
     * - [onError]: if an Exception during processing occurred
     * - [onFinished]: after processing finished, regardless of success / error state
     */
    suspend fun invoke(param: ParamT)

    /**
     * register a callback to be invoked when processing start
     */
    fun onLoading(onLoading: () -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    /**
     * register a callback to be invoked with the result of background processing
     */
    fun onResult(onResult: (ResultT) -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    /**
     * register a callback that will be invoked if an Exception occurred during processing
     */
    fun onError(onError: (Throwable) -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    /**
     * register a callback after processing finished
     */
    fun onFinished(onFinished: () -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>
}
