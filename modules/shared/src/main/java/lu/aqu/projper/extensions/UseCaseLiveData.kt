package lu.aqu.projper.extensions

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import lu.aqu.core.coroutine.CoroutineUseCase
import lu.aqu.core.coroutine.CoroutineUseCaseWithParameter
import lu.aqu.core.coroutine.invoke
import lu.aqu.core.support.Resource

/**
 * executes this UseCase when the LiveData is observed { #onActive()} in the supplied [CoroutineScope]
 * the LiveData will emit a {Resource} holding the data and status of the useCases current state

 * the UseCase execution will be canceled as soon as the returned LiveData is not observed anymore
 *
 * @return LiveData activating the UseCase as soon as it's observed
 */
fun <T> CoroutineUseCase<T>.asLiveData(scope: CoroutineScope) =
    object : LiveData<Resource<T>>() {
        private var job: Job? = null

        override fun onActive() {
            job = scope.launch {
                this@asLiveData
                    .onLoading { value = Resource.Loading }
                    .onResult { value = Resource.Success(it) }
                    .onError { value = Resource.Error(it) }
                    .invoke()
            }
        }

        override fun onInactive() {
            job?.run {
                cancelChildren()
                cancel()
            }
            job = null
        }
    }

/**
 * executes this UseCase when the LiveData is observed { #onActive()} with the supplied ParamT argument in
 * the supplied [CoroutineScope]. the LiveData will emit a {Resource} holding the data and status of the useCases
 * current state

 * the UseCase execution will be canceled as soon as the returned LiveData is not observed anymore
 *
 * @return LiveData activating the UseCase as soon as it's observed
 */
fun <ParamT, ResultT> CoroutineUseCaseWithParameter<ParamT, ResultT>.asLiveData(param: ParamT, scope: CoroutineScope) =
    object : LiveData<Resource<ResultT>>() {
        private var job: Job? = null

        override fun onActive() {
            job = scope.launch {
                this@asLiveData
                    .onLoading { value = Resource.Loading }
                    .onResult { value = Resource.Success(it) }
                    .onError { value = Resource.Error(it) }
                    .invoke(param)
            }
        }

        override fun onInactive() {
            job?.run {
                cancelChildren()
                cancel()
            }
            job = null
        }
    }
