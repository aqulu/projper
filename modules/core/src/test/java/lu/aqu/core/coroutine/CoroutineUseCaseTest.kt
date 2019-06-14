package lu.aqu.core.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutineUseCaseTest {

    @Test
    fun testOnLoadingCallback() = runBlocking {
        var hasBeenCalled = false
        val useCase = object : CoroutineUseCaseWithParameterAbs<Unit, String>(
            TestCoroutineDispatcher(), Dispatchers.Default
        ) {
            override suspend fun doInBackground(param: Unit): String {
                return Results.SUCCESS
            }
        }.onLoading {
            hasBeenCalled = true
        }

        useCase.invoke()
        Assert.assertTrue(hasBeenCalled)
    }

    @Test
    fun testOnResultCallback() = runBlocking {
        var result: String? = null
        val useCase = object : CoroutineUseCaseWithParameterAbs<Unit, String>(
            TestCoroutineDispatcher(), Dispatchers.Default
        ) {
            override suspend fun doInBackground(param: Unit): String {
                return Results.SUCCESS
            }
        }.onResult {
            result = it
        }

        useCase.invoke()
        Assert.assertEquals(Results.SUCCESS, result)
    }

    @Test
    fun testOnErrorCallback() = runBlocking {
        var throwable: Throwable? = null
        val useCase = object : CoroutineUseCaseWithParameterAbs<Unit, String>(
            TestCoroutineDispatcher(), Dispatchers.Default
        ) {
            override suspend fun doInBackground(param: Unit): String {
                throw Results.EXCEPTION
            }
        }.onError {
            throwable = it
        }

        useCase.invoke()
        Assert.assertEquals(Results.EXCEPTION.message, throwable?.message)
    }

    @Test
    fun testOnFinishedCallback() = runBlocking {
        var hasBeenCalledAfterSuccess = false
        var hasBeenCalledAfterError = false

        val useCaseSuccess = object : CoroutineUseCaseWithParameterAbs<Unit, String>(
            TestCoroutineDispatcher(), Dispatchers.Default
        ) {
            override suspend fun doInBackground(param: Unit): String {
                return Results.SUCCESS
            }
        }.onFinished {
            hasBeenCalledAfterSuccess = true
        }

        val useCaseError = object : CoroutineUseCaseWithParameterAbs<Unit, String>(
            TestCoroutineDispatcher(), Dispatchers.Default
        ) {
            override suspend fun doInBackground(param: Unit): String {
                throw Results.EXCEPTION
            }
        }.onFinished {
            hasBeenCalledAfterError = true
        }

        useCaseSuccess.invoke()
        useCaseError.invoke()

        Assert.assertTrue(
            "onFinished must be called after useCase finishes successfully",
            hasBeenCalledAfterSuccess
        )
        Assert.assertTrue(
            "onFinished must be called after useCase finishes with exception",
            hasBeenCalledAfterError
        )
    }

    private object Results {
        const val SUCCESS = "hi!"
        val EXCEPTION = Exception("test exception")
    }
}
