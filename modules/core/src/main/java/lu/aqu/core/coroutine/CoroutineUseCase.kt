package lu.aqu.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher

typealias CoroutineUseCase<T> = CoroutineUseCaseWithParameter<Unit, T>

suspend fun <T> CoroutineUseCase<T>.invoke() = invoke(Unit)

/**
 * adaption of [CoroutineUseCaseWithParameterAbs] for parameter-less Coroutine UseCases
 */
abstract class CoroutineUseCaseAbs<T>(
    mainDispatcher: CoroutineDispatcher,
    workDispatcher: CoroutineDispatcher
) : CoroutineUseCaseWithParameterAbs<Unit, T>(mainDispatcher, workDispatcher), CoroutineUseCase<T> {

    override suspend fun doInBackground(param: Unit): T =
        doInBackground()

    abstract suspend fun doInBackground(): T
}
