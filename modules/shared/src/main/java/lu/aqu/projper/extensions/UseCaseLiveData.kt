package lu.aqu.projper.extensions

import androidx.lifecycle.LiveData
import lu.aqu.core.coroutine.CoroutineUseCase
import lu.aqu.core.coroutine.CoroutineUseCaseWithParameter
import lu.aqu.core.coroutine.execute
import lu.aqu.core.support.Resource

/**
 * executes this UseCase when the LiveData is observed { #onActive()}
 * the LiveData will emit a {Resource} holding the data and status of the useCases current state

 * the UseCase execution will be canceled as soon as the returned LiveData is not observed anymore
 *
 * @return LiveData activating the UseCase as soon as it's observed
 */
fun <T> CoroutineUseCase<T>.asLiveData() =
    object : LiveData<Resource<T>>() {

        override fun onActive() =
            this@asLiveData
                .onLoading { value = Resource.Loading }
                .onResult { value = Resource.Success(it) }
                .onError { value = Resource.Error(it) }
                .execute()

        override fun onInactive() =
            this@asLiveData.unsubscribe()
    }

/**
 * executes this UseCase when the LiveData is observed { #onActive()} with the supplied ParamT argument
 * the LiveData will emit a {Resource} holding the data and status of the useCases current state

 * the UseCase execution will be canceled as soon as the returned LiveData is not observed anymore
 *
 * @return LiveData activating the UseCase as soon as it's observed
 */
fun <ParamT, ResultT> CoroutineUseCaseWithParameter<ParamT, ResultT>.asLiveData(param: ParamT) =
    object : LiveData<Resource<ResultT>>() {
        override fun onActive() =
            this@asLiveData
                .onLoading { value = Resource.Loading }
                .onResult { value = Resource.Success(it) }
                .onError { value = Resource.Error(it) }
                .execute(param)

        override fun onInactive() =
            this@asLiveData.unsubscribe()
    }
