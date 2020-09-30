package com.ht117.sofossill.app.base

abstract class BaseUseCase<Param, Result> {

    abstract suspend fun execute(param: Param): Result

    suspend operator fun invoke(param: Param): Result {
        return execute(param)
    }
}

abstract class BaseUseCaseNoParam<Result> {
    abstract suspend fun execute(): Result

    suspend operator fun invoke(): Result {
        return execute()
    }
}
