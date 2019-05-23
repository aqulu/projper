package lu.aqu.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher

typealias CoroutineUseCase<T> = CoroutineUseCaseWithParameter<Unit, T>

fun <T> CoroutineUseCase<T>.execute() = execute(Unit)

abstract class CoroutineUseCaseAbs<T>(
    mainDispatcher: CoroutineDispatcher,
    workDispatcher: CoroutineDispatcher
) : CoroutineUseCaseWithParameterAbs<Unit, T>(mainDispatcher, workDispatcher), CoroutineUseCase<T> {

    override suspend fun executeAsync(param: Unit): T =
        executeAsync()

    abstract suspend fun executeAsync(): T
}
