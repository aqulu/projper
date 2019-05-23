package lu.aqu.core.coroutine

interface ParametrizedCoroutineUseCase<ParamT, ResultT> {

    fun execute(param: ParamT)

    fun unsubscribe()

    fun onLoading(onLoading: () -> Unit): ParametrizedCoroutineUseCase<ParamT, ResultT>

    fun onResult(onResult: (ResultT) -> Unit): ParametrizedCoroutineUseCase<ParamT, ResultT>

    fun onError(onError: (Throwable) -> Unit): ParametrizedCoroutineUseCase<ParamT, ResultT>

    fun onFinished(onFinished: () -> Unit): ParametrizedCoroutineUseCase<ParamT, ResultT>
}
