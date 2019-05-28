package lu.aqu.core.coroutine

interface CoroutineUseCaseWithParameter<ParamT, ResultT> {

    suspend fun invoke(param: ParamT)

    fun onLoading(onLoading: () -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    fun onResult(onResult: (ResultT) -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    fun onError(onError: (Throwable) -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>

    fun onFinished(onFinished: () -> Unit): CoroutineUseCaseWithParameter<ParamT, ResultT>
}
